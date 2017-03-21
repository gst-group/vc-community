package com.wenjuan.dao;

import com.wenjuan.model.User;
import com.wenjuan.utils.DBUtil;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.xmlbeans.impl.inst2xsd.SalamiSliceStrategy;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class extendsProperty extends DBUtil {
    Logger logger = Logger.getLogger(extendsProperty.class);


    public List select() {
        //String sql=this.read("D://all.txt");
//	System.out.println(sql);
//	String sql="select a.id AS id,a.name AS name,"
//"max((case a.cname when age then a.value else 0 end)) AS age,"
//"max((case a.cname when sex then a.value else 0 end)) AS sex,"
//"max((case a.cname when address then a.value else 0 end)) AS address"
// "from all a group by a.id";
//	sql.replaceAll("'", "'");
//	sql.replace("'", "'");


        //System.out.println(sql);
        String sql = "select m.id AS id,m.name AS name," +
                "max((case m.cname when \"age\" then m.value else 0 end)) AS age," +
                "	max((case m.cname when \"sex\" then m.value else 0 end)) AS sex," +
                "	max((case m.cname when \"address\" then m.value else 0 end)) AS address" +
                "	 from " +
                "(select a.id AS id, " +
                "a.name AS name," +
                "a.pwd AS pwd," +
                "b.cid AS cid," +
                "b.cname AS cname," +
                "c.value AS value " +
                "from (((users a join extendc b) join cvalue c) join tab d)  " +
                "where (" +
                "(a.id = c.uid) " +
                "and (c.cid = b.cid) " +
                "and (b.tid = d.tid) " +
                " and (d.tname = \"users\")) ) m " +
                "	  group by m.id";
        List list = null;
        rs = executeQuerySQL(sql, null);
        try {
            while (rs.next()) {
                List li = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return list;

    }


    public JSONObject insertcolumn(List li, List livalue,int ogid) throws SQLException {
        //li列名  livalue{JSONOBJECT【uid,cvalue....】}
        JSONObject json = new JSONObject();
        Connection conn = getConnection();
        //默认表id为1wj_user
        int tableid = 1;
        //指定扩展表
        String tablename = "  wj_column";
        //原表
        String tablename2 = "  wj_user";
        List lname=new ArrayList();
        List ls=new ArrayList();
        //更新扩展表的记录
        for (int i = 0; i < li.size(); i++) {
            //判断扩展表是否存在该列
            String selectColumn = "select id,name from wj_column where ogid="+ogid;
           try{
            rs = executeQuerySQL(selectColumn, null);

            while (rs.next()) {
                String cname = rs.getString("name");
                String id=rs.getString("id");
                if (li.get(i).equals(cname)) {
                    lname.add(id);
                    ls.add(cname);
                }

            }
           } catch (SQLException e) {
               e.printStackTrace();
           } finally {
               closeAll(rs, pstmt, conn);
           }
//            //更新原有的列id
//            for(int k=0;k<lname.size();k++){
//                //更新
//                String sql ="update wj_column_value set cvalue=? where cid=? and uid=?";
//                        String value="";
//                        for(int j=0;j<li.size();j++) {
//                            if(li.get(j).equals(li.get(k))) {
//                                for (int f = 0; f < livalue.size(); f++) {
//                                    JSONObject jo = (JSONObject) livalue.get(i);
//                                    int uid = jo.getInt("uid");
//                                    List cvalueList = (List) jo.get("cvalue");
//                                    System.out.println(cvalueList.get(j)+"**"+uid+"**"+li.get(k));
//
//                            }
//                        }
//
//
//
//
////                        Object[] o1 = {cv, lname.get(k), uid};
////                        int rss=executeUpdateSQL(sql,o1);
////                        conn.commit();
//
//
//
//                }
//            }
            //判断原表是否存在
            String selectColumn2 = "SELECT COLUMN_NAME FROM  " +
                    "INFORMATION_SCHEMA.COLUMNS WHERE table_name = \"wj_user\"" +
                    "  AND table_schema = \"organization\"";
          try {


              rs = executeQuerySQL(selectColumn2, null);
              while (rs.next()) {
                  String cname = rs.getString("COLUMN_NAME");
                  if (li.get(i).equals(cname)) {
                      json.put("message", "添加失败原表已存在:" + li.get(i));
                      conn.close();
                      return json;
                  }
              }
           } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }

        }
//获取所有列并插入

        for(int j=0;j<li.size();j++) {
            String name = li.get(j).toString();
            //获取列的类型
            int type = 0;
            for (int i = 0; i < livalue.size(); i++) {
                JSONObject jo = (JSONObject) livalue.get(i);
                List cvalueList = (List) jo.get("cvalue");
//                for (int f = 0; f <cvalueList.size(); f++) {
                if (type == 1) {
                    break;
                }
                if (cvalueList.get(j) != null) {
                    boolean flag = cvalueList.get(j).toString().matches("^[0-9]*$");
                    if (flag == true) {
                        type = 0;
                    } else {
                        type = 1;
                    }

                }
//                }
            }
            int st=0;
            for(int t=0;t<ls.size();t++){
                if(ls.get(t).equals(name)) {
                 st=1;
                }
                }
                if(st==1){
                    continue;
                }
                conn.setAutoCommit(false);
                String insertColumn = "insert into wj_column(name,tableid,type,ogid) VALUE(?,?,?,?)";
                if(name.isEmpty())continue;
                Object[] o1 = {name, tableid, type,ogid};
               int r2 = executeUpdateSQL(insertColumn, o1, conn);
                conn.commit();



        }
        List<String> list9=new ArrayList<String>();
        String selectUsers = "select id from wj_user where EXISTS(" +
                "select * from organizationUser where ogid="+ogid+" and wj_user.id =organizationUser.uid" +
                ")";
      try{
        rs = executeQuerySQL(selectUsers, null);
        while (rs.next()) {
            String id = rs.getString("id");
            list9.add(id);
        } } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeAll(rs, pstmt, conn);
    }



            String selectCid = "SELECT id,name from wj_column where tableid=1 and ogid="+ogid;
          try{
            rs = executeQuerySQL(selectCid, null);

            while (rs.next()) {
                //获取所有扩展列id  和name
                int id=rs.getInt("id");
                String c=rs.getString("name");
                for(int j=0;j<li.size();j++) {
                    //如果和li中的列名匹配拿到id
                   if(li.get(j).equals(c)){
                       for (int l = 0; l < livalue.size(); l++) {
                           JSONObject jo = (JSONObject) livalue.get(l);
                           //拿到用户id
                           String uid =jo.get("uid").toString();
                           list9.remove(uid);
                           //拿到值得list
                           List cvalueList= (List) jo.get("cvalue");
//                           for (int f = 0; f <cvalueList.size(); f++) {
                               //扩展列值
                               //根据li 中列名的下标获取对应下标的值
                              String cvalue= cvalueList.get(j).toString();
                               //扩展列id  用户id  扩展列值
                               //插入
                           conn.setAutoCommit(false);
                           int stu=0;
                           for(int t=0;t<lname.size();t++){
                               if(Integer.parseInt(lname.get(t).toString())==(Integer)id){
                                   //插入列 如果该用户没有改列则插入该列
                               String  sqli="select count(*) count from wj_column_value where uid="+uid+" and cid="+id+"and ogid="+ogid;
//                               rs = executeQuerySQL(sqli, null);
                               int countRow=0;
//                               while (rs.next()) {
//                                   countRow = rs.getInt("count");
//                               }
                               if(countRow==0){
                                   String insertC="insert into wj_column_value(cid,uid,cvalue,ogid) value(?,?,?,?)";
                                   Object[] o5 = {id,uid,cvalue};
                                   int r3 = executeUpdateSQL(insertC, o5, conn);
                                   conn.commit();
                               }
                                   //更新用户信息
                                   String sql ="update wj_column_value set cvalue=? where cid=? and uid=? and ogid=?";
                                   Object[] o3 = {cvalue,id,uid,ogid};
                                   int r3 = executeUpdateSQL(sql, o3, conn);
                                   conn.commit();
                                stu=1;
                               }
                           }

if(stu==1){
    continue;
}

                               String insertvalue = "insert into wj_column_value(uid,cid,cvalue,ogid) values(?,?,?,?)";
                        Object[] o3 = {uid,id,cvalue,ogid};
                        int r3 = executeUpdateSQL(insertvalue, o3, conn);
                        conn.commit();
//                           }
                           }
//剩余用户插入属性为空
                       for (int l = 0; l < list9.size(); l++) {
                           String uid =list9.get(l);
                           String cvalue=null;
                           //插入
                           conn.setAutoCommit(false);
                           String insertvalue = "insert into wj_column_value(uid,cid,cvalue,ogid) values(?,?,?,?)";
                           Object[] o3 = {uid,id,cvalue,ogid};
                           int r3 = executeUpdateSQL(insertvalue, o3, conn);
                           conn.commit();
                       }
                 /*       break;*/
                   }
                }
            }
          } catch (SQLException e) {
              e.printStackTrace();
          } finally {
              closeAll(rs, pstmt, conn);
          }
        json.put("message","success");
        return json;

    }

    //查询所有数据
    public HSSFWorkbook selectAll(int page, int type,int ogid) throws SQLException {
        int begin = (page - 1) * 10;
        //查询原表所有列
        String tablename = "  wj_column";
        List l = new ArrayList();
        List li = new ArrayList();
        List li2 = new ArrayList();
        String selectColumn2 = "SELECT COLUMN_NAME FROM  " +
                "INFORMATION_SCHEMA.COLUMNS WHERE table_name = \"wj_user\"" +
                "  AND table_schema = \"organization\"";
       try {
           rs = executeQuerySQL(selectColumn2, null);
           while (rs.next()) {
               String cname = rs.getString("COLUMN_NAME");
               l.add(cname);
               li.add(cname);
           }
       } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeAll(rs, pstmt, conn);
    }
        //查询扩展表所有列
        String selectColumn = "select name from" + tablename+" where ogid="+ogid;
       try{
        rs = executeQuerySQL(selectColumn, null);
        while (rs.next()) {
            String kname = rs.getString("name");
            l.add(kname);
            li2.add(kname);
        }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           closeAll(rs, pstmt, conn);
       }
        String sql = "select";
        for (int i = 0; i < li.size(); i++) {
            if (i != 0) {
                sql += ",";
            }
            sql = sql + " m.`" + li.get(i) + "` AS `" + li.get(i) + "`";
        }
        for (int i = 0; i < li2.size(); i++) {

                sql = sql + ",max(case m.sname when '" + li2.get(i) + "' then " +
                        "(select cvalue from wj_column_value v where v.cid=(select id from wj_column c where c.name='"+li2.get(i)+"' LIMIT 1) and v.uid=m.id LIMIT 1)" +
                        " end ) AS '" + li2.get(i) + "'";
            }

        sql = sql + "from";
        String sql2 = "(select";
        for (int i = 0; i < li.size(); i++) {
            sql2 = sql2 + " a.`" + li.get(i) + "` AS `" + li.get(i) + "`,";
        }
        sql2 = sql + sql2 + " b.id AS coumnid,b.name AS sname,c.cvalue AS cvalue from wj_user a left JOIN wj_column_value c ON a.id = c.uid  " +
                "left JOIN wj_column b ON c.cid = b.id ) m where role=1  "+
                "and id in(SELECT uid from organizationUser where ogid="+ogid+")"
                +"group by m.id ORDER BY m.register_time desc";
        //导出当前页
        if (type == 1) {
            sql2 = sql2 + " LIMIT " + begin + ",10";
        }
        HSSFWorkbook wb=null;
       try {
           rs = executeQuerySQL(sql2, null);

           extendsProperty ep = new extendsProperty();
           wb = ep.rsread(rs, l.size(), l);
        } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeAll(rs, pstmt, conn);
    }
        return wb;
    }

    public HSSFWorkbook rsread(ResultSet r, int lsize, List l) throws SQLException {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("用户信息");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        int f=0;
        int x=0;
        for (int i = 0; i < l.size(); i++) {
            String title =l.get(i).toString();
            if ("id".equals(l.get(i))) {
                title = "所属编号 ";
            } else if ("name".equals(l.get(i))) {
                title = "账号 ";
            } else if ("password".equals(l.get(i))) {
                title = "密码 ";
            } else if ("sex".equals(l.get(i))) {
                title = "性别 ";
            } else if ("birthday".equals(l.get(i))) {
                title = "生日 ";
            } else if ("email".equals(l.get(i))) {
                title = "邮箱 ";
            } else if ("degree".equals(l.get(i))) {
                title = "学历 ";
            } else if ("role".equals(l.get(i))) {
                title = "权限 ";
            } else if ("marriage".equals(l.get(i))) {
                title = "婚否 ";
            } else if ("nickname".equals(l.get(i))) {
                title = "昵称 ";
            } else if ("graduated_from".equals(l.get(i))) {
                title = "毕业院校 ";
            } else if ("personalized_signature".equals(l.get(i))) {
                title = "签名 ";
            } else if ("major".equals(l.get(i))) {
                title = "专业 ";
            } else if ("location".equals(l.get(i))) {
                title = "居住地 ";
            } else if ("interest".equals(l.get(i))) {
                title = "兴趣 ";
            } else if ("score".equals(l.get(i))) {
                title = "积分 ";
            } else if ("realname".equals(l.get(i))) {
                title = "姓名 ";
            } else if ("subscribe".equals(l.get(i))) {
                title = "是否订阅新商品 ";
            } else if ("allow_article".equals(l.get(i))) {
                title = "是否允许发布话题 ";
            } else if ("query_last_diary_comment".equals(l.get(i))) {
                title = "最后日记评论时间 ";
            } else if ("query_last_article_comment".equals(l.get(i))) {
                title = "最后话题评论时间 ";
            } else if ("query_last_diary_like".equals(l.get(i))) {
                title = "最后日记点赞时间 ";
            } else if ("query_last_feedback".equals(l.get(i))) {
               title="";

            } else if ("login_count".equals(l.get(i))) {
                title = "登录次数 ";
            } else if ("register_time".equals(l.get(i))) {
                title = "注册时间 ";
            }

            if (i==3 || i==24|| i==25 || i==26 || i==28 ||i==29||i==30||i==31||i==32) {
                i++;
                continue;
            }
            row.createCell((short)f).setCellValue(title);
            f++;
        }
        List li = new ArrayList();

        int j=1;

        while (r.next()) {
            int h=0;
            row = sheet.createRow((int) j);
            for (int i = 1; i <= lsize; i++) {
            if(h>f){
                break;
            }
                if (i==3 || i==24|| i==25 || i==26 || i==28 ||i==29||i==30||i==31||i==32) {

                    i++;
                    continue;
                }
                Object value = r.getObject(i);
                if (i==5) {
                    if ("false".equals(r.getObject(i))) {
                        value = "男";
                    } else if ("true".equals(r.getObject(i))) {
                        value = "女";
                    } else {
                        value = "保密";
                    }
                }
                if (i==9) {
                    value = "普通用户";
                }
                if (i==19) {
                    if ("0".equals(r.getObject(i))) {
                        value = "未订阅";
                    } else {
                        value = "已订阅";
                    }
                }
                if (i==20) {
                    if ("1".equals(r.getObject(i))) {
                        value = "禁止";
                    } else {
                        value = "允许";
                    }
                }
                String a="";
                try{
                    a=value.toString();

                }catch(Exception e){

                }

                row.createCell((short)h).setCellValue(a);
                h++;
            }
            j++;
        }



        return wb;

    }

    public JSONObject AllUser(int begin,int ogid) throws SQLException {
        //查询原表所有列
        String tablename = "  wj_column";
        List l = new ArrayList();
        List li = new ArrayList();
        List li2 = new ArrayList();
        String selectColumn2 = "SELECT COLUMN_NAME FROM  " +
                "INFORMATION_SCHEMA.COLUMNS WHERE table_name = \"wj_user\"" +
                "  AND table_schema = \"organization\"";
      try{
        rs = executeQuerySQL(selectColumn2, null);
        while (rs.next()) {
            String cname = rs.getString("COLUMN_NAME");
            l.add(cname);
            li.add(cname);
        } } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeAll(rs, pstmt, conn);
    }

        //查询扩展表所有列
        String selectColumn = "select name from" + tablename+" where ogid="+ogid;
try{
        rs = executeQuerySQL(selectColumn,null);

        while (rs.next()) {
            String kname = rs.getString("name");
            l.add(kname);
            li2.add(kname);
        }
} catch (SQLException e) {
    e.printStackTrace();
} finally {
    closeAll(rs, pstmt, conn);
}
        String sql = "select";
        for (int i = 0; i < li.size(); i++) {
            if (i != 0) {
                sql += ",";
            }
            sql = sql + " m.`" + li.get(i) + "` AS `" + li.get(i) + "`";
        }
        for (int i = 0; i < li2.size(); i++) {
                sql = sql + ",max(case m.sname when '" + li2.get(i) + "' then " +
                        "(select cvalue from wj_column_value v where v.cid=(select id from wj_column c where c.name='"+li2.get(i)+"' LIMIT 1) and v.uid=m.id LIMIT 1)" +
                        " end ) AS '" + li2.get(i) + "' ";
        }

        sql = sql + "from";
        String sql2 = "(select";
        for (int i = 0; i < li.size(); i++) {
            sql2 = sql2 + " a.`" + li.get(i) + "` AS `" + li.get(i) + "`,";
        }
        User usreAdmin =User.getCurrentUser();
        sql2 = sql + sql2 + " b.id AS coumnid,b.name AS sname,c.cvalue AS cvalue from wj_user a left JOIN wj_column_value c ON a.id = c.uid  " +
                "left JOIN wj_column b ON c.cid = b.id ) m where role=1"+
                " AND EXISTS ("+
                "SELECT uid FROM organizationUser WHERE("+
                "EXISTS (SELECT id FROM organizationGroup WHERE OAdmin ="+usreAdmin.getId() +
                " and organizationUser.ogid=organizationGroup.id)"+
                "OR 1 = (SELECT extra FROM wj_user WHERE id ="+usreAdmin.getId()+"))"+
                "and organizationUser.uid=m.id)"+
                "  group by m.id ORDER BY m.register_time desc " +
                "  LIMIT " + begin + ",10";
        List infoLi = new ArrayList();

        try{
        rs = executeQuerySQL(sql2, null);
        while (rs.next()) {
            for (int i = 1; i <= l.size(); i++) {
                infoLi.add(rs.getObject(i));
            }
        } } catch (SQLException e) {
         e.printStackTrace();
     } finally {
         closeAll(rs, pstmt, conn);
     }
        JSONObject js = new JSONObject();
        js.put("cinfo", l);
        js.put("cx", li2);
        js.put("info", infoLi);
        return js;

    }

    //查询指定用户id的附属列
    public JSONObject SelectUserProperty(int id) throws SQLException {
        String tablename = "  wj_column";
        List l = new ArrayList();
        List li = new ArrayList();
        List li2 = new ArrayList();
        String selectColumn2 = "SELECT COLUMN_NAME FROM  " +
                "INFORMATION_SCHEMA.COLUMNS WHERE table_name = \"wj_user\"" +
                "  AND table_schema = \"organization\"";
       try {
           rs = executeQuerySQL(selectColumn2, null);
           while (rs.next()) {
               String cname = rs.getString("COLUMN_NAME");
               l.add(cname);
               li.add(cname);
           }
       } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeAll(rs, pstmt, conn);
    }
        //查询扩展表所有列
        String selectColumn = "select name from" + tablename;
      try {


        rs = executeQuerySQL(selectColumn, null);
        while (rs.next()) {
            String kname = rs.getString("name");
            l.add(kname);
            li2.add(kname);
        }
      } catch (SQLException e) {
          e.printStackTrace();
      } finally {
          closeAll(rs, pstmt, conn);
      }
        String sql = "select";
        for (int i = 0; i < li.size(); i++) {
            if (i != 0) {
                sql += ",";
            }
            sql = sql + " m.`" + li.get(i) + "` AS `" + li.get(i) + "`";
        }
        for (int i = 0; i < li2.size(); i++) {
//            if (i == li2.size() - 1) {
                sql = sql + ",max(case m.sname when '" + li2.get(i) + "' then " +
                        "(select cvalue from wj_column_value v where v.cid=(select id from wj_column c where c.name='"+li2.get(i)+"' LIMIT 1) and v.uid=m.id LIMIT 1)" +
                        " end ) AS '" + li2.get(i) + "' ";
//            } else {
//                sql = sql + "max(case m.name when '" + li2.get(i) + "' then m.cvalue else m.cvalue end ) AS '" + li2.get(i) + "',";
//            }
        }
        sql = sql + "from";
        String sql2 = "(select";
        for (int i = 0; i < li.size(); i++) {
            sql2 = sql2 + " a.`" + li.get(i) + "` AS `" + li.get(i) + "`,";
        }
        sql2 = sql + sql2 + " b.id AS coumnid,b.name AS sname,c.cvalue AS cvalue from wj_user a left JOIN wj_column_value c ON a.id = c.uid  " +
                "left JOIN wj_column b ON c.cid = b.id) m where id=" + id + " group by m.id";
        List infoLi = new ArrayList();

        try{
        rs = executeQuerySQL(sql2, null);

        while (rs.next()) {
            for (int i = 1; i <= l.size(); i++) {
                infoLi.add(rs.getObject(i));
            }
        }} catch (SQLException e) {
          e.printStackTrace();
      } finally {
          closeAll(rs, pstmt, conn);
      }
        JSONObject js = new JSONObject();
        js.put("cinfo", l);
        js.put("cx", li2);
        js.put("info", infoLi);
        return js;
    }

    //修改附属列
    public void changColumn(String cname, int uid, String value) throws SQLException {

        String sql = "SELECT id from wj_column where name='" + cname + "'";
        int id = 0;
        try{
        rs = executeQuerySQL(sql, null);
        while (rs.next()) {
            id = rs.getInt("id");
        }} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }

        String sql2 = "update wj_column_value set cvalue='" + value + "' where uid=" + uid + " and cid=" + id;
        int row = executeUpdateSQL(sql2, null);
    }

    //给新用户添加附属属性默认值
    public void insertCloumn(String sid,int ogid) throws SQLException {
        //获取
        String sql = "SELECT id from wj_column where ogid="+ogid;
        List li = new ArrayList();
        int id = 0;
       try {
           rs = executeQuerySQL(sql, null);
           while (rs.next()) {
               id = rs.getInt("id");
               li.add(id);
           }
       } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeAll(rs, pstmt, conn);
    }
        for (int i = 0; i < li.size(); i++) {
            li.get(i);
            String sql2 = "insert into wj_column_value(uid,cid,cvalue,ogid) value(?,?,?,?)";
            Object[] o1 = {sid, li.get(i), "null",ogid};
            int row = executeUpdateSQL(sql2, o1);
        }
    }

    //查询扩展的列数
    public  int size() throws SQLException{
        List li=new ArrayList();
        String selectColumn = "select name from wj_column";
      try {


        rs = executeQuerySQL(selectColumn, null);
        while (rs.next()) {
            String kname = rs.getString("name");
            li.add(kname);
        }} catch (SQLException e) {
          e.printStackTrace();
      } finally {
          closeAll(rs, pstmt, conn);
      }
//        return li.size()+21;
    return 22;
    }
    //对比插入列与已存在扩展列（删除不存在扩展列）
    public  void delete(List listCname,int ogid) throws SQLException {
        List li = new ArrayList();
        String selectColumn = "select name from wj_column where ogid="+ogid;
       try {


        rs = executeQuerySQL(selectColumn, null);
        while (rs.next()) {
            String kname = rs.getString("name");
            li.add(kname);
        }} catch (SQLException e) {
           e.printStackTrace();
       } finally {
           closeAll(rs, pstmt, conn);
       }
        //需要删除的列
        List liNo=new ArrayList();
        //不变的列（当前用户更新，其他用户不变）

        for(int i=0;i<li.size();i++){
            int status = 0;
            for(int j=0;j<listCname.size();j++){

                if(listCname.get(j).toString().equals(li.get(i).toString())){
                    status=1;
                }
            }
            if(status==0){

                liNo.add(li.get(i).toString().trim());
            }
        }
        //删除新的不存在的   旧的扩展列
        List idlist=new ArrayList();
        for (int h=0;h<liNo.size();h++){
            String sc = "select id from wj_column where name='"+liNo.get(h)+"' and ogid="+ogid;
          try {


            rs = executeQuerySQL(sc, null);
            while (rs.next()) {
                String id = rs.getString("id");
            idlist.add(id);
            }} catch (SQLException e) {
              e.printStackTrace();
          } finally {
              closeAll(rs, pstmt, conn);
          }
        }
        for(int i=0;i<idlist.size();i++){
            //删除
            String sql1="delete from wj_column where id=? and ogid=?";
            String sql2="delete from wj_column_value where cid=? and ogid=?";
            Object[] o1 = {idlist.get(i),ogid};
            int rs2 = executeUpdateSQL(sql1, o1);
            int rs3 = executeUpdateSQL(sql2, o1);
        }
    }
}
