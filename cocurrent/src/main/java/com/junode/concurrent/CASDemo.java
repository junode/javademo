package com.junode.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 自旋锁理解
 */
public class CASDemo {
    static Unsafe UNSAFE;
    static long A_OFFSET;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
            Class<CASDemo> casDemoClazz = CASDemo.class;
            A_OFFSET = UNSAFE.staticFieldOffset(casDemoClazz.getDeclaredField("a"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static int a = 0;

    public static void inc() {
        for (; ; ) {
            int temp = a;
            // 若取地址取错了，此时需要配置基地址
            /*if (UNSAFE.compareAndSwapInt(null, A_OFFSET, temp, temp++)) {
                break;
            }*/
            // 正确如下：当前类的基地址，再加上变量a的偏移地址
            // JDK1.8 静态属性保存在Class 对象的后面。也即堆内存中Class对象后面，所以需要获取到CASDemo.class的首地址
            // 对于 compareAndSwapInt 方法入参的理解，就需要看JVM源码了。
            if (UNSAFE.compareAndSwapInt(CASDemo.class, A_OFFSET, temp, ++temp)) {
                break;
            }
            // 优化，加上yeild操作，主动让出CPU,见少竞争
            Thread.yield();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            executorService.execute(() -> inc());
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        long end = System.currentTimeMillis();
        System.out.println(end -start);
        System.out.println(a);
    }

}
