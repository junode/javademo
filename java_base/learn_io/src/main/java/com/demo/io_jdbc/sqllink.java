package com.demo.io_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Auther: zwy
 * @Date: 2020/6/10
 * @Description: 使用mysql数据库
 * @version:
 */
public class sqllink {
    private static String DRIVERCLASS = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/demo?characterEncoding=utf8";
    private static String USERNAME = "root"; // 数据库用户名
    private static String PASSWORD = "123456"; // 数据库密码

    public sqllink() {

    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVERCLASS);
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }
}
