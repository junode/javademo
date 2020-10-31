package com.demo.cocurrent.wangwenjun.char04;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: zwy
 * @Date: 2020/10/28
 * @Description: 深入理解synchronized关键字
 * 将synchronized(mutex)称为锁，这种说法并不严谨，准确说是某线程获取了与mutex关联的monitor锁（）
 */
public class Mutex {

    private final static Object MUTEX = new Object();

    public void accessResource(){
        synchronized (MUTEX){
            try {
                TimeUnit.MINUTES.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final Mutex mutex = new Mutex();
        for (int i = 0; i < 5; i++) {
            new Thread(mutex::accessResource).start();
        }
    }
}
