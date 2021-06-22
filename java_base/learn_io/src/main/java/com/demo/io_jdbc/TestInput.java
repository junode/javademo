package com.demo.io_jdbc;

import com.demo.io_jdbc.entity.UserInfo;

import java.io.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.List;

/**
 * @Auther: zwy
 * @Date: 2020/6/10
 * @Description: 测试上传
 * @version:
 */
public class TestInput {

    public static void main(String[] args) {
        String path = "D:/kayak/generateData.txt";
        String sql = "INSERT INTO `demo`.`user_info`(`name`, `age`) VALUES ( ?,?)";
        test(path, sql);
    }

    private static void test(String path, String sql) {
        long startTime = System.currentTimeMillis();
        int i = 0;
        int j = 0;
        String inputFile = path;
        List<UserInfo> ls = new ArrayList<>();

        Connection con = null;

        try {
            con = sqllink.getConnection();
            System.out.println("sql连接成功");
//            String sql = sql;
            con.setAutoCommit(false); //(重要)具体说明看文章的第4点注意事项
            java.sql.PreparedStatement ptatm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // 当逐行读写大于2G的文本文件时推荐使用以下代码
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(inputFile)));
//            BufferedReader in = new BufferedReader(new InputStreamReader(bis, "UTF-8"), 10 * 1024 * 1024);// 10M缓存
            BufferedReader in = new BufferedReader(new InputStreamReader(bis, "UTF-8"));
            while (in.ready()) {
                String line = in.readLine();
                String[] arr = line.split("\\|");        //将读取的每一行以 | 号分割成数组
                UserInfo userInfo = new UserInfo();
                userInfo.setName(arr[0]);    //名称
                userInfo.setAge(Integer.parseInt(arr[arr.length - 1]));
                ls.add(userInfo);            //把从文件中读取的数据存到内存里
                i++;                            //记录读取的条数
            }
            in.close();                        //关闭文件
            System.out.println("\n总共读取Txt文件中" + i + "条数据");
            i = 0;
            j = 0;
            try {
                //将内存中的数据读取出来，准备批量写入数据库
                for (UserInfo userInfo : ls) {
                    ptatm.setString(1, userInfo.getName());
                    ptatm.setInt(2, userInfo.getAge());
                    ptatm.addBatch();                            //批量记录到容器里
                    if (i == 100000) {    //当数据读取到10w条则把这部分数据先写入数据库
                        i = 0;        //重置 i 计数器
                        System.out.println("当前总写入条数:" + j + "=============================================================");
                        ptatm.executeBatch();    //执行批量SQL语句，该语句可能返回多个结果
                        ptatm.clearBatch();        //清除容器中已写入的数据,预备下次存入数据使用
                    }
                    i++;
                    j++;
                }
                System.out.println("最后一批数据写入:" + j + "=============================================================");
                ptatm.executeBatch();
                ptatm.clearBatch();
                ptatm.close();
                con.commit();
            } catch (Exception e) {
                ptatm.close();
                con.commit();
                e.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("总耗时：" + (end - startTime)); // 总耗时：144581
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("总耗时：" + dateformat.format(end - startTime)); // 总耗时：1970-01-01 08:02:24，总共2分24秒
    }
}