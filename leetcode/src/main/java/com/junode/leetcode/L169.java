package com.junode.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author junode
 * @version 1.0.0
 * @Description 多数元素
 * @createTime 2024年05月03日 23:27:00
 */
public class L169 {

    @Test
    public void sort() {
       // 通过排序进行解决
       int[] nums = new int[]{2,2,1,1,1,2,2} ;
        Arrays.sort(nums);
        System.out.println("nums[nums.length/2] = " + nums[nums.length/2]);
    }

    @Test
    public void vote(){
        int[] nums = new int[]{2,2,1,1,1,2,2} ;
        int voteNum = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++) {
            if(voteNum == nums[i]) {
                count++;
            }else if(--count == 0) {
                voteNum = nums[i];
                count=1;
            }
        }
        System.out.println("voteNum = " + voteNum);
    }
}
