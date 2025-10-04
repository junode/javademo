package com.junode.leetcode;

import org.junit.Test;

/**
 * @author junode
 * @version 1.0.0
 * @Description 去除重复字符串
 * @createTime 2024年05月26日 17:27:00
 */
public class L316 {

    @Test
    public void greedyAndStack() {
        String s = "bcabc";
        boolean[] vis = new boolean[26];
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!vis[ch - 'a']) {
                // sb.charAt(sb.length() - 1) > ch 栈顶元素与当前元素比较，若当前元素比栈顶大，则判断是否需要替换
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch) {
                    // 若当前元素计数个数还有，表明后面还有当前元素
                    if (num[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                        // 将状态先置位false
                        vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                        // 然后删除栈顶元素。
                        sb.deleteCharAt(sb.length() - 1);
                        // 然后继续弹出栈顶元素，与当前元素比较
                    } else {
                        break;
                    }
                }
                vis[ch - 'a'] = true;
                sb.append(ch);
            }
            num[ch - 'a'] -= 1;
        }
        System.out.println(sb.toString());;
    }
}
