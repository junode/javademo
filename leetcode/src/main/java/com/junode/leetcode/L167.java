package com.junode.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author junode
 * @version 1.0.0
 * @Description
 * @createTime 2024年04月25日 23:51:00
 */
public class L167 {

    @Test
    public void doublePointer() {
        int[] temp = new int[]{1, 2, 3, 4, 5, 6, 7};
        int target = 10;
        int low = 0, high = temp.length - 1;
        while (low < high) {
            if(temp[low] + temp[high] == target) {
                System.out.println("Arrays.toString(new int[]{low+1, high+1}) = " + Arrays.toString(new int[]{low + 1, high + 1}));
            }else if(temp[low] + temp[high] > target) {
                high--;
            }else {
                low++;
            }
        }
        System.out.println("not match");
    }
}
