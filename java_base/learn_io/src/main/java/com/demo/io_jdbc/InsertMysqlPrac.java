package com.demo.io_jdbc;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;

/**
 * @Auther: zwy
 * @Date: 2020/6/10
 * @Description: com.demo.jdbc
 * @version: jdbc练习
 */
public class InsertMysqlPrac {


    /**
     * 功能描述: jdbc访问数据库联系
     *
     * @auther: zwy
     */
    @Test
    public void test1() throws ClassNotFoundException, SQLException {
        String name = "junode";
        int age = 23;
        // 1 连接数据库
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/demo";
        String user = "root";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url, user, password);


        // 编写带？的sql

        String sql = "insert into user_info(name,age) values(?,?)";

        /*
         * 准备一个PrepareStatement:预编译sql
         * 执行添加语句，如果需要获取自增长的键值，那么在此处要告知mysql服务器，在创建
         * PrepareStatement对象时，增加一个参数
         * autoGeneratedKeys : 指示是否返回自动生成的键的标志，突出Statement RETURN_GENERATED_KEYS或
         * Statement,NO_GENERATED_KEYS之一
         * */
        PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        // 4 将 ? 用具体数值替代
        pst.setString(1, name);
        pst.setInt(2, age);

        // 5 执行sql
        int len = pst.executeUpdate();
        System.out.println(len > 0 ? "添加成功" : "添加失败");
        ResultSet rst = pst.getGeneratedKeys();
        if (rst.next()) {
            System.out.println("新员工编号是：" + rst.getObject(1));
        }

        // 6 释放资源
        pst.close();
        conn.close();

    }

    /**
     * 功能描述: 测试IO写入操作
     *
     * @auther: zwy
     */
    @Test
    public void test6() {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter("D:/demo/generateData.txt", true); // 只能写默认编码的文本文件, 以追加的方式写文件
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("我是字符串");
            bufferedWriter.newLine();

            bufferedWriter.write("abcdef");
            bufferedWriter.newLine();

            bufferedWriter.write("134234234");
            bufferedWriter.newLine();

            bufferedWriter.write("汉字来啦");
            bufferedWriter.newLine();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (Exception e2) {
                }
            }
        }
    }

}
