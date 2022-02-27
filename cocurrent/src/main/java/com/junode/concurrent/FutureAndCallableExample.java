package com.junode.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Author junode
 * @Date 2022/2/6
 */
@Slf4j
public class FutureAndCallableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // 使用Callable，可以获取返回值
        Callable<String> callable = ()->{
            log.info("进入Callable的call方法");
            // 模拟子线程任务，在此睡眠2s
            // 小细节，由于call方法回抛出Exception，这里不用像使用Runnable的run方法那样try/cache了
            Thread.sleep(3000);
            return "hello world test callable";
        };
        log.info("submit Callable到线程池");
        Future<String> futureResult = executorService.submit(callable);

        log.info("main线程继续执行");
        // TODO 主线程干一些活
        Thread.sleep(1000);
        double startTime = System.nanoTime();

        // 在阻塞获取结果前，我们可以先检查任务是否已经完成了
        while(!futureResult.isDone()) {
            System.out.println("task is still not done...");
            Thread.sleep(1000);

            // 若子程序运行时间过长，获其他原因，我们像cancel子程序的运行，我们可以使用Future方法提供的
            // cancel方法，继续对程序做一些修改
            double elapsedTimeInSec = (System.nanoTime() - startTime) / 1000000000.00;
            // 如果程序运⾏时间⼤于 1s，则取消⼦线程的运⾏
            if(elapsedTimeInSec > 1) {
                futureResult.cancel(true);
                executorService.shutdown();
                executorService.awaitTermination(1,TimeUnit.MINUTES);
            }
        }

        log.info("main线程等待Future获取结果阻塞");
        // Future.get() blocks until the result is avaiable
        String result = futureResult.get();
        log.info("main线程获取到Future结果：{}",result);
        executorService.shutdown();
        executorService.awaitTermination(1,TimeUnit.MINUTES);
    }
}
