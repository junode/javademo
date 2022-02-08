package com.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * demo原于《深入理解Java虚拟机：JVM高级特性与最佳实践.pdf》文件
 * 2-3 Java堆内存溢出异常测试
 *
 */
public class Test01 {
    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
