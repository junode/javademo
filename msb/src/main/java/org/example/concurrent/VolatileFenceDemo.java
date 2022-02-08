package org.example.concurrent;

/**
 * 关于volatile内存可见性和顺序性的理解：demo来源：《Java并发编程图册2022-MAZE.pdf》
 */
public class VolatileFenceDemo {
    private int a;
    private volatile int v1 = 1;
    private volatile int v2 = 2;

    public static void main(String[] args) throws InterruptedException {

    }

    public void readAndWrite() {
        int i = v1;// 第一个volatile读
        int j = v2;// 第二个volatile读
        a = j + i; // 普通写
        v1 = i + 1;// 第一个volatile写
        // 指令中会添加 StoreStore屏障，防止与第二个volatile读/写重排序
        v2 = j * 2; // 第二个volatile写
        // 指令中这里会添加一个 StoreLoad屏障，防止与后面可能有的volatile读/写重排序
    }
}
