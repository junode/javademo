package org.example;

/**
 * @author junode
 * @version 1.0.0
 * @Description 要模拟在触发Young GC之前，由于老年代可用空间小于历次Young GC后升入老年代的对象的平均大小，从而提前触发Full GC的场景，我们可以通过控制对象的分配和生命周期来实现。以下是一个简单的示例：
 * @createTime 2024年06月16日 11:13:00
 */
public class FullGCBeforeYoungGCDemo {
    private static final int _1MB = 1024 * 1024;

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation1 = null; // 使allocation1可回收
        allocation4 = new byte[2 * _1MB];
        allocation5 = new byte[2 * _1MB];
        allocation4 = null; // 使allocation4可回收
        allocation5 = null; // 使allocation5可回收
        allocation6 = new byte[2 * _1MB];
        // 这里可能会触发Young GC，但是由于老年代空间不足，会先触发Full GC
        allocation7 = new byte[4 * _1MB]; // 尝试分配一个较大的对象
    }
}
