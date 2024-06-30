package com.junode.leetcode.backtrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author junode
 * @version 1.0.0
 * @Description 子集 回溯法
 * @createTime 2024年06月05日 22:45:00
 */
public class L78 {

    @Test
    public void test() {
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrack(nums,0, path, result);
        result.forEach(d-> System.out.println("Arrays.toString(d.toArray()) = " + Arrays.toString(d.toArray())));
    }

    public void backtrack(int[] nums, Integer begin, List<Integer> path, List<List<Integer>> result) {
        // 判断条件，收集结果
        result.add(new ArrayList<>(path));
        // 判断终止条件
        if(nums.length<=begin) {
            return;
        }
        for (int i = begin; i < nums.length; i++) {
            // 尝试选择
            path.add(nums[i]);
            // backtrack(路径, 选择列表)
            backtrack(nums, i+1, path, result);
            // 回退
            path.remove(path.size()-1);
        }
    }
}
