package com.junode.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author junode
 * @version 1.0.0
 * @Description 四数之和。这题和三数之和的思路差不多，不过现在由一个固定，变成了固定两个。
 * @createTime 2024年04月27日 11:11:00
 */
public class L18 {

    @Test
    public void doublePointer(){
        int[] nums = new int[]{1000000000,1000000000,1000000000,1000000000};
        int target = -294967296;
        // 1、排序
        // 2、首先固定外层循环k，判断nums[k] + nums[k+1] + nums[k+2] + nums[j]是否大于target，若大于，则直接调出
        // 2.1 当k>0且nums[k] = nums[k-1]，则continue；
        // 2.2 固定第二层的m，m初始值为k + 1；
        // 2.2.1、判断nums[k] + nums[m] + nums[m+1] + nums[j]是否大于target，若是，则直接退出；
        // 2.2.2 当m >i+1 且nums[m] = nums[m-1]，则continue；
        // 2.2.3 初始赋值i = m+1，j = nums.length -1
        // 2.2.4 当 i < j 时，遍历
        // 2.2.4.1 当nums[k] + nums[m] + nums[i] + nums[j] = target时，则i++,j--且跳过重复的nums[i]、nums[j]
        // 2.2.4.2 当nums[k] + nums[m] + nums[i] + nums[j] > target时，则j--且跳过重复的nums[j]
        // 2.2.4.3 当nums[k] + nums[m] + nums[i] + nums[j] < target时，则i++且跳过重复的nums[i]
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length<4) {
            System.out.println(" error " );
        }
        Arrays.sort(nums);
        for (int k = 0; k < nums.length - 3; k++) {
            if(k>0 && nums[k] == nums[k-1]) {
                continue;
            }
            for (int m = k + 1; m < nums.length - 2; m++) {
                if(m>k+1 && nums[m] == nums[m-1]) {
                    continue;
                }
                int i = m + 1, j = nums.length-1;
                while(i<j) {
                    long sum = (long)nums[k] + nums[m] + nums[i] + nums[j];
                    if(sum > target) {
                        j--;
                    }else if(sum < target) {
                        i++;
                    }else {
                        res.add(new ArrayList<>(Arrays.asList(nums[k], nums[m], nums[i],nums[j])));
                        while(i<j && nums[j] == nums[--j]);
                        while(i<j && nums[i] == nums[++i]);
                    }
                }
            }
        }
        System.out.println(Arrays.toString(res.toArray()));
    }
}
