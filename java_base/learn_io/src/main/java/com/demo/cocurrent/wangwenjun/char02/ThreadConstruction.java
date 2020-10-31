package com.demo.cocurrent.wangwenjun.char02;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.stream.IntStream;

/**
 * @Auther: zwy
 * @Date: 2020/10/26
 * @Description: 2.1.2 命名线程
 * @version:
 */
public class ThreadConstruction {

    private final static String prefix = "alex-";


    /**
     * 功能描述: 线程命名
     *
     * @auther: zwy
     */

    public static void main(String[] args) {
        IntStream.range(0, 5).mapToObj(ThreadConstruction::createThread).forEach(Thread::start);

        // 查询子线程与父线程所属的线程组
        Thread t1 = new Thread("t1");
        ThreadGroup threadGroup = new ThreadGroup("testgro");
        Thread t2 = new Thread(threadGroup, "t2");
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        System.out.println("main thread belong group: " + mainThreadGroup.getName());
        System.out.println("t1 thread belong group :" + t1.getThreadGroup().getName());
        System.out.println("t2 thread belong group : " + t2.getThreadGroup().getName());
        // 得出结论：
        // 1 main线程所在的ThreadGroup称为main
        // 2 构造一个线程的时候如果没有显示指定ThreadGroup，那么它将会和父线程同属于一个ThreadGroup。

        /**
         * 功能描述: 2.5 Thread与JVM虚拟机栈：测试stacksize对线程带来的影响
         * stacksize越大，表明该线程内可调用方法的递归深度越深，stacksize越小则代表创建的线程数量越多
         */
        if (args.length < 1){
            System.out.println("please enter the stack size");
            System.exit(1);
        }

        ThreadGroup group = new ThreadGroup("test group");
        Runnable runnable = new Runnable(){
            final int MAX = Integer.MAX_VALUE;
            @Override
            public void run() {
                int i = 0;
                recurse(i);
            }
            private void recurse(int i)
            {
                System.out.println(i);
                if (i < MAX)
                {
                    recurse(i + 1);
                }
            }
        };
        Thread thread = new Thread(group,runnable,"Test",Integer.parseInt(args[0]));
        thread.start();
    }

    // 创建线程
    private static Thread createThread(final int intName) {
        return new Thread(() -> System.out.println(Thread.currentThread().getName()), prefix + intName);
    }


}
