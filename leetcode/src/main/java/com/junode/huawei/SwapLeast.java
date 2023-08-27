package com.junode.huawei;

import java.util.List;
import java.util.Scanner;

/**
 * @Author junode
 * @Date 2022/2/27
 */
public class SwapLeast {
    public static void main(String[] args) {
        String[] keys = new String[]{"1","3","1","4","0"};
        String limit = "2";
        int lease = 0;
        int curIndex = 0;
        // 从头开始交换
        String[] fromHead = keys;
        for (int i = 0; i < fromHead.length - 1; i++) {
            if (fromHead[i].compareTo(limit) < 0) {
                curIndex = i;
                for (int j = curIndex + 1; j < fromHead.length; j++) {
                    if (fromHead[j].compareTo(limit) >= 0 && j != 1) { // 非临头节点
                        swap(fromHead, curIndex, j);
                        lease = lease + 1;
                    }
                }
            }
        }
        System.out.println(lease);
    }

    public static void swap(String[] list, int i, int j) {
        String temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }
}
