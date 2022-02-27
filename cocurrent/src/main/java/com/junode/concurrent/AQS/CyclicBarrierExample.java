package com.junode.concurrent.AQS;

import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.util.concurrent.*;

/**
 * CyclicBarrier使用示例
 */
@Slf4j
public class CyclicBarrierExample {
    private static ExecutorService singleExecutorServicePool = Executors.newSingleThreadExecutor();
    // 创建 CyclicBarrier 实例，计数器的值设置为2，当await()方法被调用两次的时候，即冲破屏障，阻塞操作被取消
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2,()->{
        singleExecutorServicePool.submit(()->barrierActionTest());
    });

    public static void barrierActionTest() {
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("全部运⾏结束");
    }



    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        int breakCount = 0;

        // 根据日志打印，线程执行顺序的是先进后出
        // 提交任务
        executorService.submit(() -> {
            try {

                log.info("第一回合");
                Thread.sleep(1000);
                cyclicBarrier.await();

                log.info("第二回合");
                Thread.sleep(2000);
                cyclicBarrier.await();

                log.info("第三回合");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        executorService.submit(() -> {
            try {
                log.info("第一回合");
                Thread.sleep(2000);
                cyclicBarrier.await();

                log.info("第二回合");
                Thread.sleep(1000);
                cyclicBarrier.await();

                log.info("第三回合");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        singleExecutorServicePool.shutdown();
        singleExecutorServicePool.awaitTermination(1, TimeUnit.MINUTES);

        log.info("main thread finish");
    }

}
