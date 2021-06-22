package com.demo.cocurrent;

import java.util.concurrent.TimeUnit;

public class demo09 {
    /*volatile*/ boolean b = true;

    void m() {
        System.out.println("start");
        // 如果b的值发生了改变，表明是进行了修改操作
        // 将volatile打开，就会更新内存存储的值。
        while (b) {
        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        final demo09 t = new demo09();
        new Thread(new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        t.b = false;
    }
}
