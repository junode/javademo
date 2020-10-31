package com.thread.ch01;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: zwy
 * @Date: 2020/7/4
 * @Description: listen music and watch news concurrent execution
 *
 * start a thread must before a task,otherwise the thread would not be start forever,
 * because of the previous task would never stop.
 */
public class demo02 {

    /**
    * 功能描述: anonymous inner class 匿名内部类
    * @auther: zwy
    */
    @Test
    public void test1(){
        // create thred by anonymous inner class,and overwrite run function
        new Thread(){
            @Override
            public void run() {
                enjoyMusic();
            }
        }.start();
        browseNews();
    }

    private void browseNews(){
        for (;;){
            System.out.println("hello browse news");
            sleep(1);
        }
    }

    private void enjoyMusic(){
        for(;;){
            System.out.println("hello enjoy the music");
            sleep(1);
        }
    }

    private void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
