package com.junode.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author junode
 * @version 1.0.0
 * @Description 删除有序数组中的重复项
 * @createTime 2024年05月03日 22:07:00
 */
public class L26 {

    @Test
    public void doublePointer() {
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        // 慢指针，指定当前待比较的位置
        // 快指针，指向当前最新的元素
        int slow=1,fast=1;
        while(fast < nums.length) {
            if(nums[slow-1] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        System.out.println("slow = " + slow);
        System.out.println("Arrays.asList(nums) = " + Arrays.toString(nums));
    }

    @Test
    public void doublePoint2() {
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        // 慢指针，指定当前待比较的位置
        // 快指针，指向当前最新的元素
        int slow=0,fast=1;
        while(fast < nums.length) {
            if(nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        System.out.println("slow = " + slow);
        System.out.println("Arrays.asList(nums) = " + Arrays.toString(nums));
    }
}
