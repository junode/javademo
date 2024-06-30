package com.junode.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author junode
 * @version 1.0.0
 * @Description 两个数组的交集
 * @createTime 2024年05月11日 21:30:00
 */
public class L349 {

    @Test
    public void set() {
        int[] nums1 =new int[] {1,2,2,1}, nums2 = new int[]{2,2};
        Set<Integer> result = new HashSet<>();
        Set<Integer> nums = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            nums.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if(nums.contains(nums2[i])) {
                result.add(nums2[i]);
            }
        }
        int[] ints = result.stream().mapToInt(x -> x).toArray();
        System.out.println("arr = " + Arrays.toString(ints));
    }

    @Test
    public void testArr() {
        int[] nums1 =new int[] {1,2,2,1}, nums2 = new int[]{2,2};
        int[] hash = new int[1001];
        int[] tmp = new int[1001];
        int resultSize = 0;
        for (int i = 0; i < nums1.length; i++) {
            hash[nums1[i]]++;
        }
        for (int i = 0; i < nums2.length; i++) {
            if(hash[nums2[i]]>0) {
                tmp[resultSize++] = nums2[i];
                hash[nums2[i]] = 0;
            }
        }
        int[] result = new int[resultSize];
        for (int i = 0; i < resultSize; i++) {
            result[i] = tmp[i];
        }
        System.out.println("result = " + Arrays.toString(result));
    }
}
