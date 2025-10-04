package com.junode.leetcode;

import org.junit.Test;

/**
 * @author junode
 * @version 1.0.0
 * @Description 长度最小的子数组
 * @createTime 2024年05月05日 23:15:00
 */
public class L209 {

    @Test
    public void slitWindow() {
        int[] nums = new int[]{2,3,1,2,4,3};
        int target  = 7;
        int result = Integer.MAX_VALUE;
        int i = 0, sum = 0;
        // j指向的是滑动窗口的终点位置
        for (int j = 0; j <= nums.length-1; j++) {
            sum += nums[j];
            while(sum >= target) {
                sum -= nums[i];
                result = Math.min(result, j - i + 1);
                i++;
            }
        }
        System.out.println("result = " + result);
    }
}
