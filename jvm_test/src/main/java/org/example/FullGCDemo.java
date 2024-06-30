package org.example;

/**
 * @author junode
 * @version 1.0.0
 * @Description JVM的Full GC日志应该怎么看？
 * @createTime 2024年06月16日 10:05:00
 */
public class FullGCDemo {
    /**
     *  byte[] array1 = new byte[6 * 1024 * 1024];
     *         array1 = null;
     *         byte[] array2 = new byte[2 * 1024 * 1024];
     *         byte[] array3 = new byte[2 * 1024 * 1024];
     *         byte[] array4 = new byte[2 * 1024 * 1024];
     *         byte[] array5 = new byte[128 * 1024];
     *         byte[] array6 = new byte[2 * 1024 * 1024];
     * 以上代码，在jdk 8 默认的虚拟机下，会执行full gc（包含metaspace），而在添加了JVM参数：-XX:+UseParNewGC -XX:+UseConcMarkSweepGC 时，却没有执行old gc和metaspace gc？
     *
     * 用ParNew + CMS时，可能没有触发Old GC或Metaspace GC，这是因为CMS旨在避免Full GC。CMS通过并发回收老年代来减少应用暂停时间，
     * 但在某些情况下（如老年代空间不足、CMS回收过程中出现"concurrent mode failure"等），仍然可能会退化为Full GC。
     *
     * 为什么在这种情况下没有触发Old GC和Metaspace GC？
     *
     *  Old GC没有触发：可能是因为在执行示例代码期间，老年代中还有足够的空间容纳新晋升的对象。由于CMS是并发运行的，它可能在没有明显应用暂停的情况下，就已经完成了必要的垃圾回收。
     *
     *  Metaspace GC没有触发：Metaspace主要用于存放类元数据信息。如果没有大量加载和卸载类，Metaspace的使用量可能不会达到触发GC的阈值。
     *  此外，Metaspace的默认大小是不固定的（取决于平台），并且可以根据需要自动扩展，直到达到MaxMetaspaceSize（如果设置了此参数）。
     * @param args
     */
    public static void main(String[] args) {
        // 挪到中间部分
//        byte[] array1 = new byte[6 * 1024 * 1024];
//        array1 = null;
        byte[] array2 = new byte[2 * 1024 * 1024];
        byte[] array3 = new byte[2 * 1024 * 1024];
        // 这里和书中示例不同，为了看到在CMS垃圾回收器下看到效果，这里就就代码进行挪动。可以看下放在前面和后面的GC的日志差异。
        // 根据gpt给的解释是：
        byte[] array1 = new byte[6 * 1024 * 1024];
        array1 = null;
        byte[] array4 = new byte[2 * 1024 * 1024];
        byte[] array5 = new byte[128 * 1024];
        byte[] array6 = new byte[2 * 1024 * 1024];
    }
}
