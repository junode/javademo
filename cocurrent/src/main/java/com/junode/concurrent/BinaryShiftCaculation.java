package com.junode.concurrent;

/**
 * @Author junode
 * @Date 2022/1/23
 */
public class BinaryShiftCaculation {
    public static void main(String[] args) {
        int COUNT_BITS = Integer.SIZE - 3;
        Integer RUNNING = -1 << COUNT_BITS;
        Integer SHUTDOWN = 0 << COUNT_BITS;
        Integer STOP = 1 << COUNT_BITS;
        Integer TIDYING = 2 << COUNT_BITS;
        Integer TERMINATED = 3 << COUNT_BITS;
        System.out.println("Integer.toBinaryString(RUNNING) = " + Integer.toBinaryString(RUNNING));
        System.out.println("Integer.toBinaryString(SHUTDOWN) = " + Integer.toBinaryString(SHUTDOWN));
        System.out.println("Integer.toBinaryString(STOP) = " + Integer.toBinaryString(STOP));
        System.out.println("Integer.toBinaryString(TIDYING) = " + Integer.toBinaryString(TIDYING));
        System.out.println("Integer.toBinaryString(TERMINATED) = " + Integer.toBinaryString(TERMINATED));
    }


}
