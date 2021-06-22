package com.demo.cocurrent.wangwenjun.char04;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: zwy
 * @Date: 2020/10/28
 * @Description: This Monitor 和 Class monitor介绍
 * @version:
 */
public class ThisMonitor {

    public synchronized void test1(){
        System.out.println(Thread.currentThread().getName()+" is running");
        try {
            TimeUnit.MINUTES.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void test2(){
        System.out.println(Thread.currentThread().getName()+" is worked???");
        try {
            TimeUnit.MINUTES.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThisMonitor thisMonitor = new ThisMonitor();
        new Thread(thisMonitor::test1,"T1").start();
        new Thread(thisMonitor::test2,"T2").start();
    }
}
