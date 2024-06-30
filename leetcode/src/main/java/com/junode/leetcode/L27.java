package com.junode.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author junode
 * @version 1.0.0
 * @Description 移除元素，并返回移除后的数组大小
 * @createTime 2024年05月03日 21:15:00
 */
public class L27 {

    @Test
    public void doublePointer() {
        int[] nums = new int[]{3,2,3,2,3};
        int val =2;
        // 双指针
        // 慢指针为待赋值的下标
        // 快指针是待比较的下标
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if(nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        System.out.println("slow = " + slow);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
    }

    @Test
    public void doublePoint2(){
        int[] nums = new int[]{3,2,2,3};
        int val =2;
        int slow = 0, fast = nums.length;
        while(slow < fast) {
            if(nums[slow] == val) {
                nums[slow] = nums[fast-1];
                fast--;
            }else{
                slow++;
            }
        }
        System.out.println("slow = " + slow);
        System.out.println("nums = " + Arrays.toString(nums));
    }

}
