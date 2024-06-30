package org.example;

/**
 * @author junode
 * @version 1.0.0
 * @Description 老年代GC示例
 * @createTime 2024年06月16日 09:30:00
 */
public class OldGCDemo {
    // 如果Survivor区域内年龄1+年龄2+年龄3+年龄n的对象总和大
    // 于Survivor区的50%，此时年龄n以上的对象会进入老年代，也就是所谓的动态年龄判定规则。

    public static void main(String[] args) {
        byte[] array1 = new byte[2*1024*1024];
        array1 = new byte[2*1024*1024];
        array1 = new byte[2*1024*1024];
        array1=null;
        byte[] array2 = new byte[128*1024];
        array2=null;
        byte[] array3 = new byte[2*1024*1024];

        array3 = new byte[2*1024*1024];
        array3 = new byte[2*1024*1024];
        array3 = new byte[128*1024];

        byte[] array4 = new byte[2*1024*1024];
    }
}
