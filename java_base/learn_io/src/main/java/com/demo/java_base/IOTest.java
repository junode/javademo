package com.demo.java_base;


import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class IOTest {

    @Test
    public void test1() {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            fileInputStream = new FileInputStream("ArrayList研究.txt");
            fileOutputStream = new FileOutputStream("ArrayList研究3.txt");
            byte[] buf = new byte[1000];
            int realCount = fileInputStream.read(buf);
            while (realCount != -1) {
                // 处理已经读到的数据
                fileOutputStream.write(buf, 0, realCount);
                // 继续读后面的数据
                realCount = fileInputStream.read(buf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
