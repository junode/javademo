package org.example.concurrent;


public class ReadAndWriteLockDemo {
    private static Integer shared_shift = 16;
    private static Integer exclusive_mask = (1 << shared_shift) - 1;

    public static void main(String[] args) {
        demo1();
    }

    /**
     * 读写锁高16位表示读，低16位表示写
     */
    private static void demo1() {
        Integer shared_unit = (1 << shared_shift);
        System.out.println("Integer.toBinaryString(shared_unit) = " + Integer.toBinaryString(shared_unit)); // 10000000000000000 17位
        Integer max_count = (1 << shared_shift) - 1;
        System.out.println("Integer.toBinaryString(max_count) = " + Integer.toBinaryString(max_count));// 1111111111111111 16位
        System.out.println("Integer.toBinaryString(exclusive_mask) = " + Integer.toBinaryString(exclusive_mask));// 1111111111111111 16位
        System.out.println("shareCount(1) = " + Integer.toBinaryString(shareCount(1))); // 1 >>> 16 = 0
        System.out.println("exclusiveCount(1) = " + Integer.toBinaryString(exclusiveCount(1))); // 1 & 1111111111111111(16位） = 1

    }
    // 因为读的状态在Int高16位，当要为读状态的锁加一，则需要通过 c + (1 << 16) 完成高16位加一操作
    // 而若是想获取读锁个数，则需要从c中获取读锁次数，则需要将从无符号右移16位，从而得到高16位的数值，即是获取读锁的个数。
    private static int shareCount(int c) {
        return c >>> shared_shift;
    }
    // 因为写的状态在Int低16位，当要为写状态的锁加一，则直接通过 c + 1即可。
    // 而如果要获取
    private static int exclusiveCount(int c) {
        return c & exclusive_mask;
    }
}
