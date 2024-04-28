package com.junode.leetcode;

import jdk.nashorn.internal.parser.JSONParser;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author junode
 * @version 1.0.0
 * @Description 合并两个有序数组
 * @createTime 2024年04月24日 23:23:00
 */
public class L85 {

    @Test
    public void method01() {
        int[] param1 = new int[]{2, 4, 6, 0, 0, 0};
        int[] param2 = new int[]{1, 3, 5};
        int param1M = 3;
        int param2M = 3;
        // 方法1，合并后排序
        // 将nums2从0的开始元素赋值到nums1，从nums1的m位置开始赋值，共拷贝n个元素
        System.arraycopy(param2, 0, param1, param1M, param2M);
        Arrays.sort(param1);
        System.out.println("Arrays.toString(param1) = " + Arrays.toString(param1));
        ;
    }

    @Test
    public void doublePoint() {
        int[] param1 = new int[]{2, 4, 6, 0, 0, 0};
        int[] param2 = new int[]{1, 3, 5};
        int param1M = 3;
        int param2M = 3;
        // 方法2： 双指针
        int param1Index = 0;
        int param2Index = 0;
        int[] container = new int[param1M + param2M];
        int cur = 0;
        while (param1Index < param1M || param2Index < param2M) {
            // 当param1到达末尾时，将param2数据复制到container，并移位；
            // 当param2到大末尾时，将param1数据复制到container，并移位；
            // 当param1小于param2，则将param1值赋值给container，param1移位；
            // 否则将param2赋值给container，并移位
            if (param1Index == param1M) {
                cur = param2[param2Index++];
            } else if (param2Index == param2M) {
                cur = param1[param1Index++];
            } else if (param1[param1Index] < param2[param2Index]) {
                cur = param1[param1Index++];
            } else {
                cur = param2[param2Index++];
            }
            // 其中container下标位置
            container[param1Index + param2Index - 1] = cur;
        }
        for (int i = 0; i < container.length; i++) {
            param1[i] = container[i];
        }
        System.out.println(Arrays.toString(param1));
    }

    @Test
    public void reverseDoublePointer() {
        int[] param1 = new int[]{2, 4, 6, 0, 0, 0};
        int[] param2 = new int[]{1, 3, 5};
        int param1M = 3;
        int param2M = 3;
        // 方法3： 逆向双指针
        int p1 = param1M - 1,p2 = param2M - 1, tail = param1M + param2M - 1;
        int cur = 0;
        while (p1 >= 0 || p2 >= 0) {
            if(p1 == -1) {
                cur = param2[p2--];
            }else if(p2 == -1) {
                cur = param1[p1--];
            }else if(param1[p1] > param2[p2]) {
                cur = param1[p1--];
            }else {
                cur = param2[p2--];
            }
            param1[tail--] = cur;
        }
        System.out.println(Arrays.toString(param1));
    }
}
