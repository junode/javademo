package com.junode.huawei;

import java.util.*;

/**
 * max profile
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[2];
        nums[0] = 1;
        nums[1] = 100;

    }

    public static int[] tranArr(int[] arrA, int K) {
        // write code here
        int[] reuslt = new int[2];
        if (arrA.length == 1) {
            reuslt[0] = arrA[0];
            reuslt[1] = arrA[0];
            return reuslt;
        }
        int aLen = arrA.length;
        int[] arrB = new int[aLen];
        Map<Integer, Integer> sets = new HashMap<>();
        for (int i = -K; i <= K; i++) {
            for (int j = 0; j < aLen - 1; j++) {
                arrB[j] = arrA[j] + i;
            }
            Arrays.sort(arrB);
            int minVal = arrB[aLen - 1] - arrB[0];

            if (minVal < arrB[0]) {
                reuslt[0] = arrB[0];
                reuslt[1] = arrB[1];
            }
        }
        return reuslt;
    }
}
