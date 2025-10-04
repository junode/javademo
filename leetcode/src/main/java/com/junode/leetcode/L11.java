package com.junode.leetcode;

import org.junit.Test;

/**
 * @author junode
 * @version 1.0.0
 * @Description 水槽最多面积。
 * @createTime 2024年04月27日 09:24:00
 */
public class L11 {


    @Test
    public void doublePointer() {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int left = 0, right = height.length - 1;
        int area = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                area = Math.max(height[left] * (right - left), area);
                left++;
            } else {
                area = Math.max(height[right] * (right - left), area);
                right--;
            }
        }
        System.out.println("area = " + area);
    }
}
