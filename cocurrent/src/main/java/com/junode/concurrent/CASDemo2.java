package com.junode.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 实现自旋锁
 */
public class CASDemo2 {
    static Unsafe UNSAFE;
    static long COUNTER_OFFSET;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
            Class<CASDemo2> casDemoClazz = CASDemo2.class;
            COUNTER_OFFSET = UNSAFE.staticFieldOffset(casDemoClazz.getDeclaredField("counter"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static int a = 0;
    static int counter = 1;// 加一个条件做判断，注意初始值开锁

    public static void lock() {
        for (; ; ) {
            // JDK1.8 静态属性保存在Class 对象的后面。也即堆内存中Class对象后面，所以需要获取到CASDemo.class的首地址
            if (UNSAFE.compareAndSwapInt(CASDemo2.class, COUNTER_OFFSET, 1, 0)) {
                break;
            }
            Thread.yield();// 优化，关闭打开试试效果，可能没有效果
        }
    }

    public static void unlock() {
        counter = 1;
    }

    public static void inc() {
        lock();
        a++;
        unlock();
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
        System.out.println(end - start);
        System.out.println(a);
    }

}
