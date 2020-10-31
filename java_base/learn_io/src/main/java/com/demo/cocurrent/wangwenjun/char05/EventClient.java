package com.demo.cocurrent.wangwenjun.char05;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: zwy
 * @Date: 2020/10/28
 * @Description: com.demo.cocurrent.wangwenjun.char05
 * @version:
 */
public class EventClient {
    public static void main(String[] args) {

        final EventQueue eventQueue = new EventQueue();
        new Thread(()->{
            for(;;){
                eventQueue.offer(new EventQueue.Event());
            }

        },"Producer").start();
        new Thread(()->{
            for(;;){
                eventQueue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Consumer").start();
    }
}
