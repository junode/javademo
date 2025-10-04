package com.junode.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author junode
 * @version 1.0.0
 * @Description 三数之和为0
 * @createTime 2024年04月26日 23:11:00
 */
public class L15 {


    @Test
    public void doublePointer() {
        // 1、排序，其时间复杂度为O(NlogN)；
        // 2、固定三个元素中的最左边元素k，采用双指针i,j，对剩余数组元素进行遍历，i,j为(k,len(nums))； PS：在固定k时，此时变成了两元素之和
        // 2.1、当nums[k]大于0时，则直接退出；
        // 2.2、k大于0时，若nums[k]与nums[k-1]相等，则跳过，无效解跳过；
        // 2.3、while循环i<j：
        // 2.3.1、当nums[i] + nums[j] + nums[k] > 0，则j--，并跳过重复nums[j]；
        // 2.3.2、当nums[i] + nums[j] + nums[k] < 0, 则i++，并跳过重复nums[i]；
        // 2.3.3、当nums[i]+nums[j] + nums[k] = 0时，保存记录到结果集中，则i--且j++，并且跳过重复的nums[j]、nums[i]。
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int k = 0; k < nums.length - 2; k++) {
            if (nums[k] > 0) {
                break;
            }
            if(k>0 && nums[k-1] == nums[k]){
                continue;
            }
            int i = k+1, j = nums.length-1;
            while(i<j) {
                int target = nums[i] + nums[j] + nums[k];
                if(target > 0) {
                    while(i<j && nums[j] == nums[--j]);
                }else if(target<0) {
                    while(i<j && nums[i] == nums[++i]);
                }else {
                    res.add(new ArrayList<>(Arrays.asList(nums[k], nums[i], nums[j])));
                    while(i<j && nums[j] == nums[--j]);
                    while(i<j && nums[i] == nums[++i]);
                }
            }
        }
        System.out.println("Arrays.toString(res.toArray()) = " + Arrays.toString(res.toArray()));

    }
}
