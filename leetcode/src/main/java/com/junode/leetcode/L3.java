package com.junode.leetcode;

import com.junode.zuoshen.class33.Hash;
import org.junit.Test;

import java.util.*;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * @Author junode
 * @Date 2022/2/27
 */
public class L3 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abaca"));
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


    @Test
    public void doublePoint(){
        String s = "abaca";
        // 采用双指针
        // 慢指针指向当前的起始位置，快指针向前挪动（当没有出现过时，就一直往下走）
        Set<Character> flag = new HashSet<>();
        // 表示从左边的开始位置之前，还没有挪动
        int right = -1, size = 0;
        for (int i = 0; i < s.length(); i++) {
            // 我们需要将已经比较过的首位元素删除
            if(i != 0){
                // 开始下一轮的遍历时的起始位置
                flag.remove(s.charAt(i-1));
            }
            // 当遇到相同的时候，则要开始下一轮遍历，但是下一轮遍历的时候，right位置可以保持不变，就left位置往前挪移位，然后flag中的前一位要删除
            while (right+1<s.length() && !flag.contains(s.charAt(right+1))) {
                right++;
                flag.add(s.charAt(right));
            }
            size = Math.max(size, right-i+1);
        }
        System.out.println(size);
    }
}
