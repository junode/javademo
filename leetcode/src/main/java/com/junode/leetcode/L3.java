package com.junode.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * @Author junode
 * @Date 2022/2/27
 */
public class L3 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abaca"));;
    }

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> temp = new HashMap<>();
        int left = 0; // 最左边位置
        int max = 0;// 最长子串
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (temp.containsKey(s.charAt(i))) {
                // 判断当前重复字符串的下标+1位置，这样就跳过了重复字符。
                // 如zqabcdafh,要跳过重复的a，则需要将最左边下标指向a的下一个位置b，从而要+1
                left = Math.max(left, temp.get(s.charAt(i)) + 1);
            }
            max = Math.max(max,i-left+1);
            temp.put(s.charAt(i),i);
        }
        return max;
    }
}
