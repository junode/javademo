package com.demo.io_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Auther: zwy
 * @Date: 2020/6/14
 * @Description: 通过文件大数据插入MySQL数据库
 * @version:
 */
public class MysqlInsertByLoadData {
    private static String path = "D:/kayak/generateData.txt";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1 连接数据库
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/demo?characterEncoding=utf-8&useSSL=false";
        String user = "root";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url, user, password);

        Statement statement = conn.createStatement();

//        String sql = "load data infile 'D:/kayak/generateData.txt' into table user_info fields terminated by" +
////                "'\\|' enclose by '\\'' lines terminated by '\\r\\n'";
        String sql = "load data infile 'D:/kayak/generateData.txt' into table user_info fields terminated by" +
                "'\\|' lines terminated by '\\r\\n'";
        boolean res = statement.execute(sql);
        System.out.println("load执行结果为：" + res);

        conn.close();
    }
}
