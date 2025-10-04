package com.junode.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author junode
 * @version 1.0.0
 * @Description 斐波那契数列
 * @createTime 2024年05月24日 10:46:00
 */
public class L509 {

    @Test
    public void testDp() {
        // 第一步： dp[i]的含义：第i项时的结果为dp[i]
        // 第二步： 寻找递归公式：  dp[n] = dp[n-1] + dp[n-2]
        // 第三步： 初始化： dp[0] = 0, dp[1] = 1
        // 第四步： 遍历顺序： 从前往后，从后到前，还是？？？
        // 第五步： 打印dp数组
        int n = 4;
        if(n<2) {
            System.out.println("n = " + n);
            return;
        }
        // 索引下标从0开始，所以需要+1
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        System.out.println("dp[n] = " + Arrays.toString(dp));
    }
}
