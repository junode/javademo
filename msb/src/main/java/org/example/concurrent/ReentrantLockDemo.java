package org.example.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition使用的demo
 */
public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        Thread t1 = new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("t1 get lock and await...");
                Thread.sleep(2000);
                condition.await();// 让出锁，阻塞等待被唤醒；唤醒的时候将重新拿到锁，往下执行
                System.out.println("t1 continue...");
                Thread.sleep(2000);
                System.out.println("t1 return ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        });
        Thread t2 = new Thread(()->{
            reentrantLock.lock();
            try {
                System.out.println("t2 get lock and signal");
                Thread.sleep(2000);
                condition.signal(); // 不阻塞，唤醒其他阻塞线程
                System.out.println("t2 return ..");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        });
        t1.start();
        Thread.sleep(1000);
        t2.start();

        t1.join();
        t2.join();
    }
}
