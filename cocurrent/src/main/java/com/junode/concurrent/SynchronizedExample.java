package com.junode.concurrent;

/**
 * @author junode
 * @version 1.0.0
 * @Description synchronized 关键字，通过javap -c -v SynchronizedExample 看同步块和同步⽅法被转换成汇编指令有何不同
 * @createTime 2024年05月20日 22:32:00
 */
public class SynchronizedExample {
    private int val = 0;

    public void synBlock() {
        synchronized (SynchronizedExample.class) {
            val++;
        }
    }

    public synchronized void synMethod() {
        val++;
    }
}
