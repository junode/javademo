package com.demo.cocurrent;

/**
 * synchronized的使用场景：
 * 1 在方法上使用synchronized的化，
 * 即对当前方法执行同步操作
 * 当多线程通过同一个对象对当前同步方法进行调用时，
 * 需要同步执行。
 * 2 同步代码块/synchronized在代码块上的使用
 * 同步代码块的同步粒度更加细致。
 * 这个细粒度应该是相对于synchronized用在方法上而言的把，效率应该更好。
 * <p>
 * 锁定代码块又分为两种：
 * 1 锁定临界对象 synchronized(object){}
 * 当多线程执行到同步代码块时，需要同步执行
 * 2 锁定当前对象 synchronized(this){}
 * 当多线程执行到这里时，需要同步执行当前方法，相当于1的使用。
 */
public class demo01 {

    private int count = 0;
    private Object o = new Object();


    public void testSync1() {
        synchronized (o) {
            System.out.println(Thread.currentThread().getName() + " count = " + count++);
        }
    }

    public void testSync2() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " count = " + count++);
        }
    }

    public synchronized void testSync3() {
        System.out.println(Thread.currentThread().getName() + " count = " + count++);
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final demo01 t = new demo01();
        new Thread(new Runnable() {
            @Override
            public void run() {

                t.testSync1();
//                t.testSync2();
//                t.testSync3();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                t.testSync1();
//                t.testSync2();
//                t.testSync3();
            }
        }).start();
    }

}
