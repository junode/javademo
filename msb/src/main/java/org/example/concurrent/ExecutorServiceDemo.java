package org.example.concurrent;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * doAnyWay方法测试：当多个任务提交时，若有一个任务完成了，剩余任务没有完成，则会被cancel。
 * @Author junode
 * @Date 2022/1/22
 */
public class ExecutorServiceDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        ArrayList<Callable<Integer>> callables = new ArrayList<Callable<Integer>>() {{
            add(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    System.out.println("1");
                    return 1;
                }
            });
            add(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    try {
                        Thread.sleep(1000);
                        System.out.println("2");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 2;
                }
            });
        }};
        Integer integer = threadPool.invokeAny(callables);
        // 若不shutdown会发觉程序没有关闭。
        threadPool.shutdown();
        threadPool.awaitTermination(10, TimeUnit.MILLISECONDS);
        System.out.println("integer = " + integer);
    }
}
