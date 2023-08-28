package com.thread.ch01;

import org.junit.Test;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Auther: zwy
 * @Date: 2020/7/4
 * @Description: 线程的生命周期详解
 * NEW
 * RUNNABLE
 * RUNNING
 * BLOCKED
 * TERMINATED
 * 1 线程的NEW状态：
 * 用关键字new创建一个Thread对象，此时并不属于执行状态，需要调用start方法启动该线程，
 * 此时线程的状态变为RUNNABLE，否则该对象与普通java对象没有任何区别。
 * 2 线程的RUNNABLE状态
 * RUNNABLE状态只能转化为RUNNING或者是意外终止。
 * 3 线程的RUNNING状态
 * 通过时间钟的调度让线程从RUNNABLE进入RUNNING状态，此时执行该线程的逻辑代码。
 * in RUNNING state，it changes as fllowing:
 * 3.1 get into TERMINATED state directly or invoking stop function
 * into TERMIANTED state.
 * <p>
 * 3.2 get into BLOCKING state ,e.g. invoking sleep,wait function that
 * enjoys the waitSet.
 * <p>
 * running the blocking IO operation,e.g. read and write operation
 * in the internet that cause into BLOCKED state.
 * <p>
 * asking for the lock resourse. therefore enjoy the lock blocked
 * set, so state change to BLOCKED.
 * <p>
 * 3.3 get into RUNNABLE state for The CPU's scheduler polling
 * causes the thread to abort execution.
 * <p>
 * give up the cpu execute right by invoking yield function ,then
 * come into RUNNABLE state.
 * <p>
 * 4 线程的BLOCKING 状态
 * 线程在BLOCK状态中可以且行换至如下几个状态：
 * 4.1 come into TERMINATED state，e.g. invoking stop function or crash out of mind，
 * <p>
 * 4.2 come into RUNNABLE state.
 * e.g. thread blocking operation finish. e.g. IO finish.
 * <p>
 * e.g. thread finish the sleep time,then come into RUNNABLE state.
 * <p>
 * e.g. thread get the lock resource,then come into runnable state.
 * <p>
 * e.g. thread blocking was interrupted by other thread.
 * <p>
 * 5 线程的TERMINATED状态
 * 线程进入TERMINATED状态，意味着该线程生命周期结束。
 * here are some condition that thread come into TERMINATED state.
 * 1 thread running naturely finish ,then into TERMINATED state.
 * <p>
 * 2 thread running is terminated by accident
 * <p>
 * 3 JVM crash,all thread are terminated.
 */
public class demo03 {

    /**
     * 功能描述: 查看Thread 的start method source code。
     *
     * @auther: zwy
     */
    @Test
    public void test1() {
        new Thread() {
            @Override
            public void run() {
                System.out.println("dddd...");
            }
        }.start();
    }

    /**
     * 功能描述: 报错为：java.lang.IllegalThreadStateException 与test3报错是不一样的。
     *
     * @auther: zwy
     */
    @Test
    public void tes2() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("hello");
                try {
                    TimeUnit.SECONDS.sleep(10); // 这个是为了同一调用thread方法。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        thread.start(); // 重复启动
    }

    /**
     * 功能描述: 此时报错为：java.lang.IllegalThreadStateException 线程状态错误，
     * 因为当前线程状态已经为TERMINATED，线程生命已经终止，不能够再转为RUNABLE或者是其他状态。
     *
     * @auther: zwy
     */
    @Test
    public void test3() throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        };
        thread.start();
        TimeUnit.SECONDS.sleep(5); // 这个是为了同一调用thread方法。
        thread.start(); // 重复启动
    }

    /**
     * 功能描述: 模板设计在Thread中的应用，模板设计模式实例
     *
     * @auther: zwy
     */
    @Test
    public void test4() {
        demo03 t1 = new demo03() {
            @Override
            protected void wrapPrint(String msg) {
                System.out.println("===" + msg + "===");
            }
        };
        t1.print("hello thread");
        demo03 t2 = new demo03() {
            @Override
            protected void wrapPrint(String msg) {
                System.out.println("+++" + msg + "+++");
            }
        };
        t2.print("ddddd thread");
    }

    public final void print(String msg) {
        System.out.println("=================");
        wrapPrint(msg);
        System.out.println("=================");
    }

    protected void wrapPrint(String msg) {
    }

    /**
     * 功能描述: 线程命名，默认名字为数字自增
     * @auther: zwy
     */
    @Test
    public void test5() {
        IntStream.range(0, 5).boxed().map(i -> new Thread(
                () -> System.out.println(Thread.currentThread().getName()))
        ).forEach(Thread::start);
    }
}
