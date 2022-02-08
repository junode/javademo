package com.thread.ch01;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
/**
 * @Auther: zwy
 * @Date: 2020/7/4
 * @Description: 该示例来源于《汪文君》课程
 * 该示例的目的是想着一遍看新闻，一遍能够听音乐，但是发觉只能够看新闻，音乐一直未被播放。
 * 这是因为browseNews() and listenMusic() function is contained in the same thread.
 * if you want to interactively run browseNes() and listenMusic() function.you should
 * create two threads. lets change the demo01 in demo02.
 * @version:
 */
public class demo01 {

    public static void main(String[] args) {

    }

    @Test
    public void test1(){
        demo01.browseNews();
        demo01.enjoyMusic();
    }

    private static void browseNews() {
        for(;;){
            System.out.println("Uh-huh,the good news.");
            sleep(1);
        }
    }

    private static void enjoyMusic(){
        for(;;){
            System.out.println("uh-huh,the nice music.");
            sleep(1);
        }
    }

    private static void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
