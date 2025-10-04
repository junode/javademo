package com.junode.leetcode;

import org.junit.Test;

/**
 * @author junode
 * @version 1.0.0
 * @Description
 * @createTime 2024年04月26日 23:14:00
 */
public class L125 {

    @Test
    public void testReverse() {
        // 判断反转后是否相等
        String temp = "race a car";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < temp.length(); i++) {
            if (Character.isLetterOrDigit(temp.charAt(i))) {
                sb.append(Character.toLowerCase(temp.charAt(i)));
            }
        }
        System.out.println(sb);
        String reverse = sb.reverse().toString();
        System.out.println(reverse);
        System.out.println(sb.toString());
    }

    @Test
    public void doublePointer() {
        String s = "A man, a plan, a canal: Panama";
        int low = 0, high = s.length() - 1;
        // 用 < 非 <= de仅有一个字符的时候不比较，其默认相等
        while (low < high) {
            while(!Character.isLetterOrDigit(s.charAt(low)) && low < high) {
                low++;
            }
            while(!Character.isLetterOrDigit(s.charAt(high)) && low < high ) {
                high--;
            }
            // 控制low和high变化的情况
            if(low<high) {
                if(Character.toLowerCase(s.charAt(low)) != Character.toLowerCase(s.charAt(high))){
                    System.out.println("false");;
                }
                low++;
                high--;
            }
        }
        System.out.println("true");;
    }
}
