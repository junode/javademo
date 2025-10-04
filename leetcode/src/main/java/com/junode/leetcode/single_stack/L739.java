package com.junode.leetcode.single_stack;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author junode
 * @version 1.0.0
 * @Description 每日温度。 参考代码随想录
 * @createTime 2024年06月09日 21:40:00
 */
public class L739 {

    @Test
    public void test() {
        int[] temperatures =new int[] {73,74,75,71,69,72,76,73};
        Deque<Integer> deque = new LinkedList<>();
        deque.push(0);
        int[] result = new int[temperatures.length];
        for (int i = 1; i < temperatures.length; i++) {
            if(temperatures[deque.peek()] > temperatures[i]) {
                deque.push(i);
                continue;
            }
            while (!deque.isEmpty() && temperatures[deque.peek()] < temperatures[i]) {
                Integer preIndex = deque.pop();
                result[preIndex] = i - preIndex;
            }
            deque.push(i);
        }
        while(!deque.isEmpty()) {
            result[deque.poll()] = 0;
        }
        System.out.println("Arrays.toString(result) = " + Arrays.toString(result));
    }


    @Test
    public void update01() {
        int[] temperatures =new int[] {73,74,75,71,69,72,76,73};
        Deque<Integer> deque = new LinkedList<>();
        deque.push(0);
        int[] result = new int[temperatures.length];
        for (int i = 1; i < temperatures.length; i++) {
            if(temperatures[deque.peek()] > temperatures[i]) {
                deque.push(i);
                continue;
            }
            while (!deque.isEmpty() && temperatures[deque.peek()] < temperatures[i]) {
                Integer preIndex = deque.pop();
                result[preIndex] = i - preIndex;
            }
            deque.push(i);
        }
        // 因队列初始化的时候，Integer的默认值就是0，所以这里的while可以省略
        System.out.println("Arrays.toString(result) = " + Arrays.toString(result));
    }


    @Test
    public void update2() {
        int[] temperatures =new int[] {73,74,75,71,69,72,76,73};
        Deque<Integer> deque = new LinkedList<>();
        deque.push(0);
        int[] result = new int[temperatures.length];
        for (int i = 1; i < temperatures.length; i++) {
            // 因为最后面都需要push一遍，这样当前判断是否可以省略的。
            while (!deque.isEmpty() && temperatures[deque.peek()] < temperatures[i]) {
                Integer preIndex = deque.pop();
                result[preIndex] = i - preIndex;
            }
            deque.push(i);
        }
        // 因队列初始化的时候，Integer的默认值就是0，所以这里的while可以省略
        System.out.println("Arrays.toString(result) = " + Arrays.toString(result));
    }
}
