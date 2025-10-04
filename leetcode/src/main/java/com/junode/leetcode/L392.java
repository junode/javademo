package com.junode.leetcode;

import org.junit.Test;

/**
 * @author junode
 * @version 1.0.0
 * @Description
 * @createTime 2024年05月26日 17:56:00
 */
public class L392 {

    @Test
    public void isSubsequence() {
        String s="b", t="c";
        // 双指针
        int slow = 0, fast = 0;
        int sLen = s.length();
        for(;fast<t.length();fast++) {
            if(s.charAt(slow) == t.charAt(fast)) {
                slow++;
            }
            if(sLen == slow) {
                System.out.println(true);;
            }
        }
        System.out.println("false = " + false);;
    }
}
