package com.demo.cocurrent;

import java.util.concurrent.TimeUnit;

/**
 * synchronized关键字
 * 同步方法 - 多方法调用原子性问题（业务）
 * 同步方法只能保证当前方法的原子性，不能保证多个业务方法之间的互相访问的原子性。
 * 注意在商业开发中，多方法要求结果访问原子操作，需要多个方法都加锁，且锁定统一个资源。
 * <p>
 * 一般来说，商业项目中，不考虑业务逻辑上的脏读问题。
 */
public class demo05 {

    private double a = 0.0;

    public synchronized void m1(double val) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.a = val;
    }

    public double getA() {
        return this.a;
    }

    public static void main(String[] args) {
        demo05 de = new demo05();
        new Thread(new Runnable() {
            @Override
            public void run() {
                de.m1(100.0);
            }
        }).start();
        System.out.println(de.getA());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(de.getA());
    }
}
