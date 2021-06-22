package com.demo.cocurrent.wangwenjun.char03;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: zwy
 * @Date: 2020/10/28
 * @Description: 3.9 关闭线程
 * 1 线程结束生命周期正常结束
 * 2 捕获中断信号关闭线程
 */
public class InterruptThreadExit {



    public static void main(String[] args) throws InterruptedException {
//        test1();

        // volatile关键字
        MyTask task = new MyTask();
        task.start();
        TimeUnit.MINUTES.sleep(1);
        System.out.println(Thread.currentThread().getName()+"will be shutdown");
        task.close();
    }

    // interrupt 关键字打断（叫醒） 中断线程
    public static void test1() throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" will start work! ");
                while(!isInterrupted()){
                    // working
                }
                System.out.println(Thread.currentThread().getName()+" will be exited");
            }
        };
        thread.start();
        TimeUnit.MINUTES.sleep(1);
        System.out.println(Thread.currentThread().getName()+" will be shutdown");
        thread.interrupt();
    }



    /*
    * 由于线程的interrupt标识很可能被擦除，或者逻辑单元中不会调用任何中断方法（sleep、wait、join）
    * 所以使用volatile修饰的开关flag关闭线程也是常用操作
    * */
    static class MyTask extends Thread{
        private volatile boolean closed = false;
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" will start work");
            while(!closed && !isInterrupted()){
                // 正在运行
            }
            System.out.println(Thread.currentThread().getName()+" will be exiting");
        }

        public void close(){
            this.closed = true;
            this.interrupt();
        }
    }

    public static void test2(){

    }

}
