package com.junode.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author junode
 * @version 1.0.0
 * @Description 有效字母异位
 * @createTime 2024年05月11日 21:10:00
 */
public class L242 {

    @Test
    public void arrayHash(){
        String s = "anagram", t = "nagaram";
        int[] h = new int[26];
        // 相对位移下标
        char relativeIndex = 'a';
        for (int i = 0; i < s.length(); i++) {
            h[s.charAt(i)-relativeIndex]++;
        }
        for (int i = 0; i < t.length(); i++) {
            h[t.charAt(i)-relativeIndex]--;
        }
        for (int i = 0; i < h.length; i++) {
            if(h[i] != 0) {
                System.out.println(false);
            }
        }
        System.out.println("true = " + true);
    }

    @Test
    public void useArray() {
        String s = "anagram", t = "nagaram";
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        Arrays.sort(charS);
        Arrays.sort(charT);
        System.out.println(Arrays.equals(charS, charT));;
    }
}
