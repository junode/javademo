package org.example;

/**
 * @author junode
 * @version 1.0.0
 * @Description young gc的示例
 * @createTime 2024年06月16日 00:01:00
 */
public class YoungGCDemo {
    /**
     * JVM参数示例
     *  -XX:NewSize=5242880 -XX:MaxNewSize=5242880 -XX:InitialHeapSize=10485760 -
     * XX:MaxHeapSize=10485760 -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=10485760 -
     * XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -
     * Xloggc:gc.log
     * @param args
     */
    public static void main(String[] args) {
        // 1M
        byte[] array1 = new byte[1024*1024];
        array1 = new byte[1024*1024];
        array1 = new byte[1024*1024];
        array1=null;
        byte[] array2 = new byte[1024*1024*2];
    }
}
