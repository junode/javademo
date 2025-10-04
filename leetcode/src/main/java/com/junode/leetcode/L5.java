package com.junode.leetcode;

import org.junit.Test;

/**
 * @author junode
 * @version 1.0.0
 * @Description 最长回文子串 中心扩展法
 * @createTime 2024年05月23日 00:25:00
 */
public class L5 {
    @Test
    public void test() {
        String s = "babad";
        // 1、遍历字符串i
        // 2、以i、i+1为中心向两端遍历，当不等时或超过边界时，截止，返回长度
        // 3、比较获取最大的长度（初始为0,）；
        // 4、根据长度和当前位置i计算出起始位置和截止位置，该起始和截止位置就是最长子序列。
        int left = 0, right = 0;
        // 这里是左闭右开，但是因为考虑基数和偶数（中间两个元素），就使用到了i+1情况，防止越界，这里就直接用s.length() -1且是右开的
        for (int i = 0; i < s.length() - 1; i++) {
            int len1 = compareAndLen(s, i, i);
            int len2 = compareAndLen(s, i, i + 1);
            int maxLen = Math.max(len1, len2);
            if (maxLen > right - left) {
                left = i - (maxLen - 1) / 2;
                right = i + maxLen / 2;
            }
        }
        System.out.println("s.substring(left,right) = " + s.substring(left,right+1));
    }

    public int compareAndLen(String s, int left, int right) {
        // 注意：这里当比较不等时，left已经--，right 已经++
        // 比如字符串： caabab，当到达b向中心扩散时，b此时下标为3，left--，right++，此时left = 2,right=4,charAt比较left和right相等，
        // 然后left--，right++，left=1,right = 5，此时比较不等，然后返回长度应该是要为3，从而是right-left-1=5-1-1=3
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
