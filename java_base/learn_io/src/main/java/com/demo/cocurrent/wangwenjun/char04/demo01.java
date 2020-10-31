package com.demo.cocurrent.wangwenjun.char04;

import org.junit.Test;

/**
 * @Auther: zwy
 * @Date: 2020/10/28
 * @Description: 第四章 线程安全与数据同步
 * 多线程中最复杂的内容：数据同步、线程安全、锁等概念
 * 共享资源：多个线程同时对同一份资源进行读写操作，被多个线程访问的资源就是共享资源
 * 如何保证多个线程访问到的数据是一致的，则被称为数据同步或资源同步。
 */
public class demo01 implements Runnable{
    private int index = 1;
    private final static int MAX = 100;


    @Override
    public void run() {
        while (index < MAX){
            System.out.println(Thread.currentThread()+" 的号码是： "+(index++));
        }
    }

    /**
     * 功能描述: 4.1 数据不一致问题
     * 数据不一致出现的现象有：
     *  1 某个数字被忽略打印：线程B为index赋值后，时间片到了，就一直给了线程A执行，此时线程A的index就跳过了线程B的数值
     *  2 号码重复出现
     *  3 号码超过最大值
     * @auther: zwy
     */
    @Test
    public void test1(){
        final demo01 task = new demo01();
        Thread t1 = new Thread(task,"一号窗口");
        Thread t2 = new Thread(task,"二号窗口");
        Thread t3 = new Thread(task,"三号窗口");
        Thread t4 = new Thread(task,"四号窗口");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    /**
    * 功能描述: 初识synchronized关键字
     * synchronized关键字提供了一种锁机制，能够确保共享变量的互斥访问，从而防止数据不一致问题的出现。
     * synchronized关键字包括monitor enter和monitor exit两个JVM指令，它能够保证在任何时候任何线程
     * 执行到monitor enter成功之前都必须从主内村中获取数据，而不是从缓存中，在monitor exit运行成功后，
     * 共享变量被更新后的值必须刷入主内存。
     * synchronized的指令规则遵守java happens-before规则，一个monitor exit指令之前必定要有一个Monitor
     * enter。
     *
     * synchronized可以用于修改代码块或者方法
    */
    public synchronized void sync(){

    }

}
