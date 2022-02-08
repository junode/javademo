package org.example.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger.get()操作获取的是快照值，后续新增的操作，对该值不会改变。
 */
public class SnapDemo {
    static AtomicInteger atomicIngeter = new AtomicInteger(1);

    /*public static void main(String[] args) throws InterruptedException {
        int snapVal = atomicIngeter.get();
        new Thread(()->{atomicIngeter.incrementAndGet();}).start();
        Thread.sleep(100); // 先睡眠一小会，再拿值
        System.out.println(snapVal);
        System.out.println(atomicIngeter.get());
    }*/
}
