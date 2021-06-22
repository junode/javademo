package com.demo.cocurrent.wangwenjun.char05;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Stream;

/**
 * @Auther: zwy
 * @Description: 第二个版本
 * @Date: 2020/10/30
 * 多producer和多consumer之间的通讯导致出现程序假死的原因分析：https://www.bilibili.com/video/BV1hJ411D7k2?p=25
 */
public class EventQueueVersion2 {
    private final int max;

    private static int DEFAULT_VALUE_MAX = 10;

    public EventQueueVersion2(int max){
        this.max = max;
    }

    public EventQueueVersion2(){
        this(DEFAULT_VALUE_MAX);
    }

    static class Event{

    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();

    public void offer(Event event){
        synchronized (eventQueue){
            if(eventQueue.size()>=max){
                try {
                    System.out.println(Thread.currentThread().getName()+" queue is full");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"  queue add 1 event");
            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }

    public void take(){
        synchronized (eventQueue) {
            if(eventQueue.isEmpty()){
                try {
                    System.out.println(Thread.currentThread().getName()+" queue is empty");
                    eventQueue.wait();
                    ;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+" am consuming the event");
            eventQueue.removeFirst();
            eventQueue.notify();
        }
    }

    public static void main(String[] args) {
        EventQueueVersion2 version2 = new EventQueueVersion2();
        Stream.of("P1","P2").forEach(n->{
            new Thread(){
                @Override
                public void run() {
                    for (;;){
                        version2.offer(new Event());
                    }
                }
            }.start();
        });

        Stream.of("C1","C2").forEach(n->{
            new Thread(){
                @Override
                public void run() {
                    for(;;){
                        version2.take();
                    }
                }
            }.start();
        });
    }


}
