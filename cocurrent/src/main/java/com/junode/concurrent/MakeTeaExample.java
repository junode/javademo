package com.junode.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * demo来源：Java并发编程图册2022-MAZE.pdf
 * 采用FutureTask来演练烧水泡茶经典程序
 * 1、洗水壶 1分钟
 * 2、烧开水 15分钟
 * 3、洗茶壶 1分钟
 * 4、洗茶杯 1分钟
 * 5、拿茶叶 2分钟
 * 若采用一个线程串行执行，则需要20分钟。但很显然，我们可以在烧开水期间，可以洗茶壶/洗茶杯/拿茶叶
 * 线程1：
 * 洗水壶 1分钟
 * 烧开水 15分钟
 * 线程2：
 * 洗茶壶、洗茶杯、拿茶叶 4分钟
 */
@Slf4j
public class MakeTeaExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 创建线程2的FutureTask
        FutureTask<String> ft2 = new FutureTask<>(new T2Task());

        // 创建线程1的FutureFask，这里采用匿名表达式创建了
        Future<String> t1FutureResult = t1TaskExecute(executorService, ft2);
        executorService.submit(ft2);
        log.info("烧茶结果：{}",t1FutureResult.get());
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }

    /**
     * @param executorService 执行结果
     * @param task2           洗茶壶、洗茶杯、拿茶叶的t2线程任务结果放进来
     * @return
     */
    private static Future<String> t1TaskExecute(ExecutorService executorService, Future<String> task2) {
        return executorService.submit(() -> {
            log.info("T1:洗水壶...");
            TimeUnit.SECONDS.sleep(1);

            log.info("T1:烧开水");
            TimeUnit.SECONDS.sleep(15);

            // 获取到茶叶2的名称
            String getTeaName = task2.get();
            log.info("{}已经准备好了，开始泡茶", getTeaName);

            return "T1:茶烧好了";
        });
    }

    static class T2Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("T2:洗茶壶...");
            TimeUnit.SECONDS.sleep(1);

            log.info("T2:洗茶杯...");
            TimeUnit.SECONDS.sleep(2);

            log.info("T2:拿茶叶...");
            TimeUnit.SECONDS.sleep(1);
            return "茶具和细叶茶";
        }
    }

}

