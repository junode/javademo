package com.junode.concurrent;

import com.junode.concurrent.pojo.Product;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步计算结果
 */
@Slf4j
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        runAsyncDemo();
//        supplyAsyncDemo();
        // supplyAsync希望将blocking获取到的结果传入到回调函数，在Future结束时自动调用该回调函数，这样就不需要等待结果
        supplyAsyncAndThenApply();
        supplyAsyncAndThenAccept();
    }

    public static void supplyAsyncAndThenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> noResult = CompletableFuture.supplyAsync(
                () -> Product.builder().id("12342").name("颈椎按摩仪").build()
        ).thenAccept(product -> {
            log.info("get productName from API" + product.getName());
        });
        System.out.println(noResult.get());
    }

    /**
     * 流式处理 -- 带返回结果
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void supplyAsyncAndThenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> endResult = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalArgumentException(e);
            }
            log.info("begin test");
            return "return a result for fallback function";
        }).thenApply(para1 -> {
            log.info("get result from begin 【{}】", para1);
            return "again";
        }).thenApply(para2 -> {
            log.info("get result from first thenApply 【{}】", para2);
            return "finish";
        });
        log.info("has finish???");
        log.info(endResult.get());
    }

    /**
     * supplyAsync能够异步计算获取结果信息
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void supplyAsyncDemo() throws ExecutionException, InterruptedException {
        CompletableFuture<String> supplyAsyncResult = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalArgumentException(e);
            }
            return "get Result From supplyAsync function...";
        });
        log.info(supplyAsyncResult.get());
    }

    /**
     * 使用 runAsync进行异步计算。runAsync是没有返回结果的
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void runAsyncDemo() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> runAsyncResult = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalArgumentException(e);
            }
            log.info("运行在一个单独的线程当中");
        });
        System.out.println(runAsyncResult.get());
        ;
    }

}
