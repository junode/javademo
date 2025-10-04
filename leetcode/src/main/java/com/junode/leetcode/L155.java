package com.junode.leetcode;

import org.junit.Test;

import java.util.Stack;

/**
 * @author junode
 * @version 1.0.0
 * @Description
 * @createTime 2024年05月05日 17:36:00
 */
class MinStack{
    // 数组栈[当前值，当前最小值]
    private Stack<int[]> stack = new Stack<>();
    public MinStack() {

    }

    public void push(int val) {
        if(stack.isEmpty()){
            stack.push(new int[]{val, val});
        }else {
            stack.push(new int[]{val, Math.min(stack.peek()[1], val)});
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.pop()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }
}
public class L155 {

    @Test
    public void test(){
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(3);
        minStack.push(2);
        minStack.push(-100);
        System.out.println("minStack.getMin() = " + minStack.getMin());
        minStack.pop();
        System.out.println("minStack.getMin() = " + minStack.getMin());
        minStack.pop();
        System.out.println("minStack.getMin() = " + minStack.getMin());
    }
}
