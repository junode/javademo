package org.example.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author junode
 * @Date 2022/1/23
 */
public class CocurrentUnSafe {

    static  int a = 0;

    public synchronized static void  inc() {
        a++;
    }

    public static void main(String[] args) throws InterruptedException {
        // 一般我们通过new操作创建多个线程来执行
       /* for (int i = 0; i < 10000; i++) {
            new Thread(()->a++).start();
        }
        Thread.sleep(1000);
        System.out.println(a);*/

        // 而若是用线程池来完成上面功能，则速度会加快很多，已经线程已经有了，无需new
        /*ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10000; i++) {
            executorService.submit(()->a++);
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println(a);*/

        // 以上两种情况是不能全的，则将a++操作用锁包裹这，让只有一个线程执行a++操作。
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10000; i++) {
            executorService.submit(()->inc());
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println(a);

        // 采用cas的方式来实现线程安全

    }
}
