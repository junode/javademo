package com.demo.book.first;

import java.io.IOException;

/**
 * 不限制Jvm大小时，打印的结果为： current n value is 8980
 * 限制Jvm大小时，打印的结果为：
 */
public class RecursionAlgorithmMain {

    private static volatile int value = 0;

    public static int sigma(int n) {
        System.out.println("current n value is " + n);
        return n+ sigma(n+1);
    }

    public static void main(String[] args) throws IOException {
        new Thread(() -> sigma(1)).start();
        System.in.read();
        System.out.println(value);
    }

}
