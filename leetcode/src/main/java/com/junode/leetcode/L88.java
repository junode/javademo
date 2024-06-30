package com.junode.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author junode
 * @version 1.0.0
 * @Description 删除有序数组中的重复项，其中重复项可重复两次
 * @createTime 2024年05月03日 22:20:00
 */
public class L88 {

    @Test
    public void doublePointer() {
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4,4,4};
        // 判断重复， nums[slow−2]=nums[slow−1]=nums[fast]
        int n = nums.length;
        if (n <= 2) {
            System.out.println("n = " + n);
        }
        int slow = 2;
        for (int fast = 2; fast < n; fast++) {
            if(nums[fast] != nums[slow-2]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        System.out.println("slow = " + slow);
        System.out.println(Arrays.toString(nums));
    }
}
