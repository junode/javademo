package com.junode.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author junode
 * @version 1.0.0
 * @Description 移动0
 * @createTime 2024年05月06日 23:50:00
 */
public class L283 {

    @Test
    public void doublePointer() {
        int[] nums = new int[]{0,1,0,3,12};
        int slow = 0, fast = 0;
        while(fast < nums.length) {
            if(nums[fast] != 0) {
                int temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow] = temp;
                slow++;
            }
            fast++;
        }
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
    }
}
