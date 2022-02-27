package com.junode.concurrent;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步线程未执行完毕，主线程提前执行完毕，则需要主线程开启等待。
 */
public class ForthTopicTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setDaemon(true);
                        return thread;
                    }
                });
        threadPoolExecutor.submit(()->{
            System.out.println("hello");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("exit");
        });
        // 屏蔽这两行，看输出结果。
        // threadPoolExecutor.shutdown(); // or threadPoolExecutor.shutdownNow();
        // threadPoolExecutor.awaitTermination(1,TimeUnit.MINUTES);

    }
}
