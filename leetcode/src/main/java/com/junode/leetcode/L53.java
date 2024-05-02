package com.junode.leetcode;

import org.junit.Test;


/**
 * @author junode
 * @version 1.0.0
 * @Description 最大子数组和（Maximum Subarray Sum）
 * @createTime 2024年05月02日 00:03:00
 */
public class L53 {
    /**
     * 复用已有累计。
     * 1、正常情况：
     * 获取所有的子列表，
     * 第一次
     *      3
     *      3+(-1)
     *      3+(-1)+2
     *      3+(-1)+2+(-5)
     *      3+(-1)+2+(-5) + 2
     *  第二次
     *      (-1)
     *      (-1)+2
     *      (-1)+2+(-5)
     *      (-1)+2+(-5) + 2
     *  第三次
     *      2
     *      2+(-5)
     *      2+(-5) + 2
     *  第四次
     *      (-5)
     *      (-5) + 2
     *  第五次
     *      2
     *  对于每一次而言，其前面的累计是可以复用的。直接是前面的累计值加上下一个元素，就得到当前区间的累计值，
     *  然后再与最大的累计值进行比较，保留最大累计值，当五次遍历完后，留下的累计值就是子区间的最大累计。
     */
    @Test
    public void twoLoop() {
        int[] ls = new int[] {3,-1,2,-5,2};
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < ls.length; i++) {
            sum = ls[i];
            maxSum = Math.max(maxSum, sum);
            for (int j = i+1; j < ls.length; j++) {
                sum += ls[j];
                maxSum = Math.max(maxSum, sum);
            }
        }
        System.out.println("maxSum = " + maxSum);
    }

    /**
     * 采用分治法。判断问题能够通过分治覆盖全，如果能在最大层面上拆分，而递归后的分治也就能覆盖全。
     * 以本题为例，将列表分成两部分，求左侧最大区间，和右侧最大区间，此时少了中间相连部分的区间情况，此时就补充左右两个区间的的数值，s1和s2，
     * 从而最终返回的是max(sumLeft,sumRight, s1 + 1)
     */
    @Test
    public void method2(){
        int[] ls = new int[] {3,-1,2,-5,2};
        int max = maxSubSum(ls, 0, ls.length-1);
        System.out.println("max = " + max);
    }

    public int maxSubSum(int[] subLs, int left, int right) {
        if(left == right) return subLs[left];
        int mid = left + (right - left) / 2;
        int leftMax = maxSubSum(subLs, left, mid);
        int rightMax = maxSubSum(subLs, mid+1, right);
        int s1 = maxContinueLeft(subLs, left, mid);
        int s2 = maxContinueRight(subLs, mid+1, right);
        return Math.max(Math.max(leftMax, rightMax), s1 + s2);
    }

    public int maxContinueLeft(int[] subLs, int left, int right) {
        if (left == right) {
            return subLs[left];
        }
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = right; i >= left; i--) {
            sum += subLs[i];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    public int maxContinueRight(int[] subLs,int left, int right){
        if (left == right) {
            return subLs[left];
        }
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += subLs[i];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    /**
     * 使用双指针方法解决 LeetCode 第 53 题“最大子序和”实际上是一种贪心算法的实现，通常称为“Kadane算法”。
     * 这个算法的基本思想是，遍历数组，用一个指针（或者说变量）来跟踪当前位置的最大子序和，同时用另一个指针（或者说变量）来跟踪到目前为止的最大子序和。
     */
    @Test
    public void doublePointer() {
        int[] ls = new int[] {3,-1,2,-5,2};
        int curMax = ls[0];
        int totalMax = ls[0];
        for (int i = 1; i < ls.length; i++) {
            curMax = Math.max(ls[i], curMax + ls[i]);
            if(totalMax < curMax) {
                totalMax = curMax;
            }
        }
        System.out.println("totalMax = " + totalMax);
    }
}

