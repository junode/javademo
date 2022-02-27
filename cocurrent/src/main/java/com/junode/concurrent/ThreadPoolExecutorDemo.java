package com.junode.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 在Java中，我们想了解一个类，切入点就是类的构造方法。
 * @Author junode
 * @Date 2022/1/23
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        int cps = 1;
        int mps = 2;
        int c = 5;
        int size = mps + c;
        /*ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2,
                60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(5),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());*/
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2,
                60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(5),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < size; i++) {
            threadPoolExecutor.execute(()->{
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        // 若活全排满了，则我自己做了。此时就编程了
        threadPoolExecutor.execute(()->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("test111");
    }


}
