package org.example.concurrent;


import java.util.concurrent.*;

/**
 * 背压demo：消费者线程抑制了生产者线程，这就是背压。
 */
public class ThreadPoolExecutor03 {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10,
                5, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(10), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                return thread;
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                if (!e.isShutdown()) {
                    r.run();
                    System.out.println("线程池满了，不能再放了");
                }
            }
        });
        /*for (int i = 0; i < 20; i++) {
            threadPoolExecutor.submit(()->{
                try {
                    Thread.sleep(2000);// 模拟耗时操作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }*/

        // 当往线程池中添加的任务数大于20【最大线程数+队列数】时，则会产生背压，抑制主线程生产任务
        for (int i = 0; i < 25; i++) {
            threadPoolExecutor.submit(()->{
                try {
                    Thread.sleep(2000);// 模拟耗时操作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(1,TimeUnit.MINUTES);
    }
}
