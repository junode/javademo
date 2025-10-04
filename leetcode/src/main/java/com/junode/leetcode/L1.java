package com.junode.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author junode
 * @version 1.0.0
 * @Description
 * @createTime 2024年05月06日 23:18:00
 */
public class L1 {


    @Test
    public void twoSum() {
        // 哈希解决
        int[] nums = new int[]{3,2,4};
        int target = 6;
        Set<Integer> subSet = new HashSet<>(nums.length);
        int i = 0;
        for (; i < nums.length; i++) {
            if(subSet.contains(nums[i])) {
                break;
            } else {
                subSet.add(target-nums[i]);
            }
        }
        int j = 0;
        for (; j < i; j++) {
            if(nums[i] + nums[j]==target) {
                break;
            }
        }
        System.out.println("i + j = " + i + j);
    }
}
