package com.wenjuan.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    public Connection conn = null;
    public PreparedStatement pstmt = null;
    public ResultSet rs = null;

    public static void main(String[] args) {
        Connection con = new DBUtil().getConnection();
        if (con != null) {
            System.out.println("success");
        }
    }

    /**
     * 获得连接对象
     *
     * @return Connection
     */
    public Connection getConnection()  {

        Properties prop =  new  Properties();
        InputStream ins = this.getClass().getResourceAsStream("../properties/MysqlDB.properties");
        String userName ="";
        String passWord="";
        String url="";
        try  {
            prop.load(ins);
            userName = prop.getProperty( "userName" ).trim();
            passWord = prop.getProperty( "passWord" ).trim();
            url=prop.getProperty( "Dburl" ).trim();
        }  catch  (IOException e) {
            e.printStackTrace();
        }



        String sqlDriver = "com.mysql.jdbc.Driver";
        try {
            // 反射机制
            // 第一步：加载驱动类,导入驱动包
            Class.forName(sqlDriver);
            // 第二步：获得连接对象
            conn = DriverManager.getConnection(url,userName,passWord);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 执行增加，删除，修改操作
     *
     * @param sql 执行语句
     * @return 受影响的行数
     */
    public int executeUpdateSQL(String sql, Object[] obj) {
        int row = 0;
        getConnection();
        try {
            pstmt = conn.prepareStatement(sql);

            if (obj != null) {
                for (int i = 0; i < obj.length; i++) {
                    pstmt.setObject(i + 1, obj[i]);
                }
            }
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }
        return row;
    }

    public int executeUpdateSQL(String sql, Object[] obj, Connection conn) {
        int row = 0;
        try {
            pstmt = conn.prepareStatement(sql);

            if (obj != null) {
                for (int i = 0; i < obj.length; i++) {
                    pstmt.setObject(i + 1, obj[i]);
                }
            }
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    /**
     * 执行查询操作
     *
     * @return ResultSet
     */
    public ResultSet executeQuerySQL(String sql, Object[] obj) {
        try {
            // 反射机制
            conn = getConnection();
            // 第三步：获得执行对象
            pstmt = conn.prepareStatement(sql);
            if (obj != null) {
                for (int i = 0; i < obj.length; i++) {
                    pstmt.setObject(i + 1, obj[i]);
                }
            }
            // 第四步：获得结果集对象
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 关闭所有对象
     *
     * @param rs   结果集
     * @param stmt 执行
     * @param conn 连接
     */
    public void closeAll(ResultSet rs, Statement pstmt, Connection conn) {
        // 第六步：关闭对象 逆向关闭
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (pstmt != null) {
                pstmt.close();
                pstmt = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
