package com.demo.cocurrent.wangwenjun.char05;

import java.util.LinkedList;

/**
 * @Auther: zwy
 * @Date: 2020/10/28
 * @Description: 初识wait和notify
 * @version:
 */
public class EventQueue {
    private final int max;

    static class Event {

    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();
    private final static int DEFAULT_MAX_EVENT = 10;

    public EventQueue() {
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue(int max) {
        this.max = max;
    }

    public void offer(Event event) {
        synchronized (eventQueue) {
            while (eventQueue.size() >= max) {
                try {
                    console("this queue is full.");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console("the new event is submitted.");
            eventQueue.addLast(event);
            eventQueue.notifyAll();
        }
    }

    public Event take() {
        synchronized (eventQueue) {
            while (eventQueue.isEmpty()) {
                try {
                    console("the queue is empty ");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = eventQueue.removeFirst();
            this.eventQueue.notifyAll();
            console(" the event " + event + " is handled. ");
            return event;
        }
    }

    public void console(String print) {
        System.out.printf("%s : %s \n", Thread.currentThread().getName(), print);
    }

}
