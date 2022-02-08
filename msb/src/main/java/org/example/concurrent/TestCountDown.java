package org.example.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch的使用demo
 * 源于：https://www.bilibili.com/video/BV1yJ411v7er?p=4&spm_id_from=pageDriver
 */
public class TestCountDown {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread t1 = new Thread(()->{
            try {
                System.out.println("t1 start...");
                countDownLatch.await();
                Thread.sleep(2000);
                System.out.println("t1 return...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(()->{
            try {
                System.out.println("t2 start...");
                Thread.sleep(2000);
                countDownLatch.countDown();
                System.out.println("t2 return...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(()->{
            try {
                System.out.println("t3 start...");
                Thread.sleep(2000);
                countDownLatch.countDown();
                System.out.println("t3 return");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
        System.out.println("finish");

    }

}
