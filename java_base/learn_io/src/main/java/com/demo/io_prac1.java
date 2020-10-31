package com.demo;

import org.junit.Test;

import java.io.*;

/**
 * @Auther: zwy
 * @Date: 2020/6/13
 * <p>
 * 关于IO的认识：
 * IO称为输入/输出流，根据传输数据大小的单位不同而分为字节流和字符流。
 * 从而有了：输入字节流，输入字符流；输出字节流，输出字符流。
 * 而对于字节与字符：这两者是没有关系的。一般我们在键盘中敲打出来的字母，符号，中文字都是字符，
 * 而对于字符而言，需要若干位的字节来进行存储；一般是2个字节存储一个字符。
 * 或者说，字符流 = 多字节流；
 * <p>
 * 字节是计算机的基本单位，1字节 = 8 比特。
 * <p>
 * 一般若是我们处理的是文本，则直接用字符流进行处理。
 * 而若是处理二进制文件，则用字节流进行处理。
 * <p>
 * 而IO流有根据是否与物理存储节点直接交互，分为底层节点流与上层处理流。
 * 因为 不同物理存储节点所存储的节点流 有所差异，从而底层节点流对 物理存储节点 交互时进行了封装(装饰器)，
 * 提供了统一接口的节点流给上层处理流。
 * <p>
 * 以上来源的认识：
 * 1 李刚老师《疯狂Java讲义》
 * 2 网上百度了 字节 与 字符的定义。
 */
public class io_prac1 {
    private static final String path = "E:\\idea\\java_base\\learn_io\\src\\main\\resources\\input.txt";

    /**
     * 功能描述: 指定字节长度的byte容器，用于盛装读取的数据，测试不同长度下数据读取方式。
     * 从中可以发现，若是超过了数据容器范围，则会截断数据，这样就可能导致数据不可理解。
     *
     * @auther: zwy
     */
    @Test
    public void test1() {
        InputStream is = null;
        try {
            is = new FileInputStream(path);
            byte[] bt = new byte[1024];
            int len = 0;
            while ((len = is.read(bt)) != -1) {
                System.out.println(len);
                System.out.println(new String(bt, 0, len));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 功能描述: 通过调用InputStreamReader将字节流转为字符流。
     * 注意，java中没有将字符流转为字符流的设计类，可能是考虑应用性比较小吧。
     *
     * @auther: zwy
     */
    @Test
    public void test2() {
        Long start = System.currentTimeMillis();
        InputStream is = null;
        Reader reader = null;
        try {
            is = new FileInputStream(path);
            reader = new InputStreamReader(is);
            char[] chars = new char[1024];
            int len = 0;
            while ((len = reader.read(chars)) != -1) {
                System.out.println(len);
                System.out.println(new String(chars, 0, len));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
