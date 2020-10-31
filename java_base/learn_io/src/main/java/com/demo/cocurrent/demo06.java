package com.demo.cocurrent;

import org.w3c.dom.ls.LSOutput;

import java.util.concurrent.TimeUnit;

/**
 * synchronized关键字
 * 同步方法 -- 调用其他同步方法
 * 锁可重入。同一个线程，多次调用同步代码，锁定同一个锁对象，可重入。
 * <p>
 * 注意，锁定的是同一个对象 this。
 */
public class demo06 {

    synchronized void test1() {
        System.out.println("run test1 start");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test2();
        System.out.println("run test1 end");
    }

    synchronized void test2() {
        System.out.println("test2 start");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test 2 end");
    }

    public static void main(String[] args) {
        new demo06().test1();
    }
}
