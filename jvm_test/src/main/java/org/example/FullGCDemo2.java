package org.example;

/**
 * @author junode
 * @version 1.0.0
 * @Description JVM的Full GC日志应该怎么看？
 * @createTime 2024年06月16日 10:05:00
 */
public class FullGCDemo2 {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB]; // 这里触发第一次Minor GC

    }
}
