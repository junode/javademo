package com.demo.cocurrent;


import java.util.concurrent.locks.ReentrantLock;

/**
 * 文章来源：https://blog.csdn.net/joker_apple/article/details/52790181
 *  *可重入锁
 *  *所谓重入锁，指的是以线程为单位，当一个线程获取对象锁之后，这个线程可以再次获取本对象上的锁，而其他的线程是不可以的
 *  *synchronized 和   ReentrantLock 都是可重入锁
 *  *可重入锁的意义在于防止死锁
 *  *实现原理实现是通过为每个锁关联一个请求计数和一个占有它的线程。
 *  *当计数为0时，认为锁是未被占有的。线程请求一个未被占有的锁时，jvm讲记录锁的占有者，并且讲请求计数器置为1 。
 *  *如果同一个线程再次请求这个锁，计数将递增；
 *  *每次占用线程退出同步块，计数器值将递减。直到计数器为0,锁被释放。
 *  *
 *  *关于父类和子类的锁的重入:
 *  *子类覆写了父类的synchonized方法，然后调用父类中的方法，
 *  *此时如果没有重入的锁，那么这段代码将产生死锁。
 *  *
 *  *以下关于子类和父类的到底是几个对象，也就是影响到父类和子类获取的是否是同一个锁（以下解释来自博客 http://blog.csdn.net/aitangyong/article/details/22695399）
 *  *使用A a = new A()这种方式创建对象的时候，JVM会在后台给我们分配内存空间，然后调用构造函数执行初始化操作，
 *  *最后返回内存空间的引用。
 *  *即构造函数只是进行初始化，并不负责分配内存空间（创建对象）。
 *  *所以呢其实创建子类对象的时候，JVM会为子类对象分配内存空间，
 *  *并调用父类的构造函数。
 * <p>
 *  *我们可以这样理解：创建了一个子类对象的时候，在子类对象内存中，
 *  *有两份数据，一份继承自父类，一份来自子类，但是他们属于同一个对象（子类对象），
 *  *只不过是java语法提供了this和super关键字来让我们能够按照需要访问这2份数据而已。
 *  *这样就产生了子类和父类的概念，但实际上只有子类对象，没有父类对象。
 *  *
 *  
 */
public class ReentrantLockDemo01 extends FLockTest1 implements Runnable {

    ReentrantLock lock = new ReentrantLock();

    public synchronized void methodA() {
        System.out.println(Thread.currentThread().getName() + "this is Method A start");
        //此时访问 对于方法B上拿锁，必须先拿到方法methodA()上的锁
        methodB();
        System.out.println(Thread.currentThread().getName() + "this is Method A end");
    }

    public synchronized void methodB() {
        System.out.println(Thread.currentThread().getName() + "this is Method B start");
        System.out.println(Thread.currentThread().getName() + "this is Method B end");
    }

    public synchronized void test() {
        System.out.println("this is childrenTest started");
        super.test();
        System.out.println("this is childrenTest end");
    }

    @Override
    public void run() {
        //测试本类中的同步方法
        methodA();
        //测试父类和子类的重入
        test();
        //测试ReentrantLock
        reentrantLockTest1();
    }

    public static void main(String[] args) {
        ReentrantLockDemo01 t1 = new ReentrantLockDemo01();
        Thread t = new Thread(t1);
        t.start();
        t1.methodB();
    }

    public void reentrantLockTest1() {
        lock.lock();
        System.out.println("this is reentrantLockTest1");
        reentrantLockTest2();
        lock.unlock();
    }

    public void reentrantLockTest2() {
        lock.lock();
        System.out.println("this is reentrantLockTest2");
        lock.unlock();
    }
}

class FLockTest1 {
    public synchronized void test() {
        System.out.println("this is Father Class test started");
        System.out.println("this is Father Class test end");
    }
}