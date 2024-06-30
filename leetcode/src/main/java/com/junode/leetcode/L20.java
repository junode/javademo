package com.junode.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * @author junode
 * @version 1.0.0
 * @Description
 * @createTime 2024年05月05日 17:09:00
 */
public class L20 {

    @Test
    public void stack() {
        String s = "()";
        char[] sChar = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0;i < sChar.length;i++) {
            if(stack.isEmpty()) {
                stack.push(sChar[i]);
            } else {
                if((stack.peek().equals('{') && sChar[i] == '}') || (stack.peek().equals('[') && sChar[i] == ']')
                        || (stack.peek().equals('(') && sChar[i] == ')')  ) {
                    stack.pop();
                } else {
                    stack.push(sChar[i]);
                }
            }
        }
        System.out.println("stack.isEmpty() = " + stack.isEmpty());
    }
}
