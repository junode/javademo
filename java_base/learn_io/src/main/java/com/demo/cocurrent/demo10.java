package com.demo.cocurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile关键字
 * volatile的非原子性问题
 * volatile， 只能保证可见性，不能保证原子性。
 * 不是枷锁问题，只是内存数据可见。
 */
public class demo10 {

    volatile int val = 0;

    // 将synchronized屏蔽后，就不能保证原子性了。
    /*synchronized*/ void countAdd() {
        for (int i = 0; i < 10000; i++) {
            val++;
        }
    }

    public static void main(String[] args) {
        final demo10 t = new demo10();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    t.countAdd();
                }
            }));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(t.val);
    }
}
