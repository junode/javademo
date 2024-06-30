package com.junode.leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author junode
 * @version 1.0.0
 * @Description 最长公共前缀
 * @createTime 2024年05月23日 22:27:00
 */
public class L14 {

    @Test
    public void maxPrefixLen() {
        String[] strs =new String[] {"flower","flow","flight"};
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }
        int maxPre = 0;
        for (int i = 0; i < minLen; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if(c != strs[j].charAt(i)) {
                    System.out.println("maxPre = " + strs[0].substring(0,maxPre));
                    return ;
                }
            }
            maxPre++;
        }
        System.out.println("maxPre = " + strs[0].substring(0,maxPre));
    }

}
