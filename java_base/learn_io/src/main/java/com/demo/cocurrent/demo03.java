package com.demo.cocurrent;

/**
 * synchronized关键字
 * 同步方法：原子性
 * 加锁的目的：就是为了保证操作的原子性。
 */
public class demo03 implements Runnable {
    private int count = 0;

    @Override
    public /*synchronized*/ void run() {
        System.out.println(Thread.currentThread().getName()
                + " count = " + count++);
    }

    public static void main(String[] args) {
        demo03 t = new demo03();
        for (int i = 0; i < 5; i++) {
            new Thread(t, "Thread - " + i).start();
        }
    }
}
