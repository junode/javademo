package com.junode.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author junode
 * @version 1.0.0
 * @Description 获取平方后后续的数组
 * @createTime 2024年05月05日 22:29:00
 */
public class L977 {

    @Test
    public void sort() {
        int[] nums = new int[]{-4, -1, 0, 3, 10};
        int[] result = new int[nums.length];
        int size = nums.length-1;
        for (int i = 0, j = nums.length - 1; i < j; ) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                result[size--] = nums[i] * nums[i];
                i++;
            }else {
                result[size--] = nums[j] * nums[j];
                j--;
            }
        }
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(result));
    }
}
