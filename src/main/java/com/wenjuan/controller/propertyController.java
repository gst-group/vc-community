package com.wenjuan.controller;

import com.wenjuan.dao.extendsProperty;
import com.wenjuan.model.OrganizationUser;
import com.wenjuan.model.User;
import com.wenjuan.service.OrganizationGroupService;
import com.wenjuan.service.OrganizationUserService;
import com.wenjuan.service.UserService;
import com.wenjuan.utils.ApplicationContextUtil;
import com.wenjuan.utils.HxUtil;
import com.wenjuan.utils.poiUtil;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class propertyController {
    @Autowired
    @Qualifier("organizationUserService")
    private OrganizationUserService organizationUserService;
    @Autowired
    @Qualifier("organizationGroupService")
    private OrganizationGroupService organizationGroupService;
    Logger logger = Logger.getLogger(propertyController.class);
    //导入excel

    /**
     * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
     *
     * @param file       读取数据的源Excel
     * @param ignoreRows 读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
     * @return 读出的Excel中数据的内容
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String[][] getData(File file, int ignoreRows)
            throws FileNotFoundException, IOException {
        List<String[]> result = new ArrayList<String[]>();
        int rowSize = 0;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(
                file));
        // 打开HSSFWorkbook
        POIFSFileSystem fs = new POIFSFileSystem(in);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFCell cell = null;
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            HSSFSheet st = wb.getSheetAt(sheetIndex);
            // 第一行为标题，不取
            for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
                HSSFRow row = st.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                int tempRowSize = row.getLastCellNum() + 1;
                if (tempRowSize > rowSize) {
                    rowSize = tempRowSize;
                }
                String[] values = new String[rowSize];
                Arrays.fill(values, "");
                boolean hasValue = false;
                for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                    String value = "";
                    cell = row.getCell(columnIndex);
                    if (cell != null) {
                        // 注意：一定要设成这个，否则可能会出现乱码
                        // cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                        switch (cell.getCellType()) {
                            case HSSFCell.CELL_TYPE_STRING:
                                value = cell.getStringCellValue();
                                break;
                            case HSSFCell.CELL_TYPE_NUMERIC:
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    Date date = cell.getDateCellValue();
                                    if (date != null) {
                                        value = new SimpleDateFormat("yyyy-MM-dd")
                                                .format(date);
                                    } else {
                                        value = "";
                                    }
                                } else {
                                    value = new DecimalFormat("0").format(cell
                                            .getNumericCellValue());
                                }
                                break;
                            case HSSFCell.CELL_TYPE_FORMULA:
                                // 导入时如果为公式生成的数据则无值
                                if (!cell.getStringCellValue().equals("")) {
                                    value = cell.getStringCellValue();
                                } else {
                                    value = cell.getNumericCellValue() + "";
                                }
                                break;
                            case HSSFCell.CELL_TYPE_BLANK:
                                break;
                            case HSSFCell.CELL_TYPE_ERROR:
                                value = "";
                                break;
                            case HSSFCell.CELL_TYPE_BOOLEAN:
                                value = (cell.getBooleanCellValue() == true ? "Y"
                                        : "N");
                                break;
                            default:
                                value = "";
                        }
                    }
                    if (columnIndex == 0 && value.trim().equals("")) {
                        break;
                    }
                    values[columnIndex] = rightTrim(value);
                    hasValue = true;
                }

                if (hasValue) {
                    result.add(values);
                }
            }
        }
        in.close();
        String[][] returnArray = new String[result.size()][rowSize];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) result.get(i);
        }
        return returnArray;
    }

    /**
     * 去掉字符串右边的空格
     *
     * @param str 要处理的字符串
     * @return 处理后的字符串
     */
    public static String rightTrim(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        for (int i = length - 1; i >= 0; i--) {
            if (str.charAt(i) != 0x20) {
                break;
            }
            length--;
        }
        return str.substring(0, length);
    }

    public JSONObject insert(String path, HttpServletResponse res, HttpServletRequest req,int ogid) throws FileNotFoundException, IOException, SQLException {
        poiUtil poi = new poiUtil();
        List UidAndCvalue=null;
        List<List<String>> list = poi.read(path);
    //列名
        extendsProperty ep = new extendsProperty();
        List lc=new ArrayList();
        if (list != null) {
            int size=ep.size();
            List<String>  list1=list.get(0);
            for(int i=size;i<list1.size();i++){
                lc.add(list1.get(i));
            }
            //删除不需要的列
            ep.delete(lc,ogid);

            //id和值列
             UidAndCvalue=new ArrayList();
            for (int i = 1; i < list.size(); i++) {
                List cvalue=new ArrayList();
                JSONObject jo=new JSONObject();
                jo.put("uid",list.get(i).get(0));

                List<String>  list2=list.get(i);

                for(int f=size;f<list2.size();f++){
                    try {
                        if(list2.get(f).length()==0){
                            cvalue.add(" ");

                        }else{
                            cvalue.add(list2.get(f));

                        }
                    }catch (Exception e){
                        cvalue.add(" ");

                    }


                }
                jo.put("cvalue",cvalue);
                UidAndCvalue.add(jo);
            }
        }

       JSONObject json = ep.insertcolumn(lc, UidAndCvalue,ogid);
        return json;
    }

    public HSSFWorkbook export(int page, int type,int ogid) throws SQLException {
        //String path="C://Users//Administrator//Desktop";
        extendsProperty ep = new extendsProperty();
        HSSFWorkbook wb = ep.selectAll(page, type,ogid);
        return wb;
    }

    public JSONObject info(int begin,int ogid) throws SQLException {
        extendsProperty ep = new extendsProperty();
        JSONObject js = ep.AllUser(begin,ogid);
        return js;
    }

    /**
     * 获取指定用户的所有信息
     *
     * @throws SQLException
     */
    public JSONObject selectUesrProperty(int id) throws SQLException {
        extendsProperty ep = new extendsProperty();
        JSONObject js = ep.SelectUserProperty(id);
        return js;
    }


    /**
     * 修改附属值
     *
     * @throws SQLException
     */
    public void changProperty(String cname, int uid, String value) throws SQLException {
        extendsProperty ep = new extendsProperty();
        ep.changColumn(cname, uid, value);
    }

    /**
     * 给新用户添加默认附属属性值
     *
     * @throws SQLException
     */
    public void insertUser(String uid,int ogid) throws SQLException {
        extendsProperty ep = new extendsProperty();
        ep.insertCloumn(uid,ogid);
    }

    //批量添加用户todoUser
    public List inserUserS(String path) throws IOException, SQLException, ParseException, InterruptedException {
        List li = new ArrayList();
        poiUtil poi = new poiUtil();
        List<List<String>> list = poi.read(path);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if ((i + 1) % 20 == 0) {
                    Thread.sleep(1000);
                }
                User user = new User();
                user.setAddBackground(true);
                //检查账号密码是否合法
                String tel="";
                try{
                    tel=list.get(i).get(0);
                }catch (Exception e){

                }
                String pwd="";
                try{
                    pwd=list.get(i).get(1);
                }catch (Exception e){
                }
                    if(tel.isEmpty()||null==tel||pwd.isEmpty()||null==pwd||pwd.length()<6){
                     if(tel.isEmpty()||null==tel){
                         int f=i+1;
                            li.add("第"+f+"行用户名不能为空");
                     }else
                     if(pwd.isEmpty()||null==pwd||pwd.length()<6){
                         li.add(tel+"。信息:密码长度不能小于6位");
                     }
                    }else{
                        user.setName(tel);
                        user.setPassword(pwd);
                        try{
                        if(list.get(i).get(2).isEmpty()){
                            user.setNickname("");
                        }else{
                            user.setNickname(list.get(i).get(2));
                        }}catch (Exception e){
                            user.setNickname("");
                        }

//                        User users = ApplicationContextUtil.getBean("userService", UserService.class).selectByName(user.getName());
                               //添加用户(环信若插入失败则尝试直接更新环信及用户表)
                                ApplicationContextUtil.getBean("userService", UserService.class).insert(user);
                                //添加属性
                                User proUser = ApplicationContextUtil.getBean("userService", UserService.class).selectByName(user.getName());
                                propertyController pc = new propertyController();

                        //给用户插入指定组织
                        OrganizationUser o=new OrganizationUser();
                        o.setUid(proUser.getId());
                        User userAdmin=User.getCurrentUser();
                       o.setOgid(ApplicationContextUtil.getBean("organizationGroupService", OrganizationGroupService.class).selectByOAdmin(userAdmin.getId()));
                        pc.insertUser(proUser.getId().toString(),o.getOgid());

                        ApplicationContextUtil.getBean("organizationUserService",OrganizationUserService.class).insert(o);


                    }
           //确保用户名密码与环信同步
                if(pwd.length()>=6&&tel.length()>0){
                    HxUtil hu = new HxUtil();
                    User us=new User();
                    us.setName(tel);
                    us.setPassword(pwd);
                    hu.updateUserInfo(us);
                }


            }
        }
        return li;
    }
}
