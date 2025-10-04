package com.junode.leetcode;

import org.junit.Test;

/**
 * @author junode
 * @version 1.0.0
 * @Description 中心扩展法
 * @createTime 2024年05月05日 18:00:00
 */
public class L84 {

    /**
     * 中心扩散法
     */
    @Test
    public void test(){
        int[] heights = new int[]{2,1,2};
        int max = heights[0];
        for (int i = 0; i < heights.length; i++) {
            int left = i-1, right = i+1;
            while(left >= 0 && heights[i] <= heights[left]) {
                left--;
            }
            while(right < heights.length && heights[i] <= heights[right]) {
                right++;
            }
            max = Math.max((right -left - 1) * heights[i], max );
        }
        System.out.println("max = " + max);
    }
}
