package com.junode.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author junode
 * @version 1.0.0
 * @Description 轮转数组
 * @createTime 2024年05月04日 00:22:00
 */
public class L189 {

    @Test
    public void rotate() {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        int k = 3;
        int[] copy = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
           copy[(i+k)%nums.length] = nums[i]; 
        }
        System.arraycopy(copy,0,nums,0, nums.length);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
    }


    @Test
    public void reverse() {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        int k = 3;
        k = k % nums.length;
        reverse(nums,0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums,k, nums.length-1);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
    }

    public void reverse(int[] nums, int start, int end){
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
