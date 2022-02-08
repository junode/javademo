package org.example.concurrent;

/**
 * 信号量demo：假唤醒
 */
public class SemaporeDemo01 {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);// 资源为0，没有
        new Thread(()->{
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("exit1");
        }).start();
        new Thread(()->{
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("exit2");
        }).start();
        Thread.sleep(1000);
        // 这里release操作，看着是只有一个线程被唤醒执行了，但实际上两个线程都被唤醒了。在Semapore中添加打印输出
        semaphore.release();
    }
}
