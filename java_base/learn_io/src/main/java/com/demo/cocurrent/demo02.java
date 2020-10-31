package com.demo.cocurrent;


import java.util.concurrent.TimeUnit;

public class demo02 {
    private static int staticint = 0;

    public static synchronized void testSync4() {
        System.out.println(Thread.currentThread().getName()
                + " staticCount = " + staticint++);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void testSync5() {
        synchronized (demo02.class) {
            System.out.println(Thread.currentThread().getName()
                    + " staticCount = " + staticint++);
        }
    }
}
