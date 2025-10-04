package com.junode.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author junode
 * @version 1.0.0
 * @Description 买卖股票的最佳时机4：最多买卖K次
 * @createTime 2024年05月24日 11:33:00
 */
public class L188 {

    /**
     * 在做这题之前，先看下《买卖股票最佳时机3》，最多买卖两次。
     * <p>
     * 1、状态：
     * 当最多买卖两次时，有5中状态，则买卖k次就有2k+1中状态。
     * 2、递推公式：
     * 在最多买两次时，其递推公式有4个，则对于买卖K次时，其递推公式有2k个
     * 3、初始化：
     * 在最多买卖两次时，其状态有5个，则对于买卖K次，其状态有2k+1次，但是通过观察最多买卖两次可知，
     * 其都是奇数进行买入时，其现金需要减少
     * 4、递推遍历：
     * 当前需要遍历两次，外层是原来的数组遍历，内层变为买卖K次的遍历，就是2K次遍历
     * 5、获取结果，最多买卖两次获取的结果是： dp[len-1][4]，则最多买卖K次获取的结果是： dp[len-1][2k]
     */
    @Test
    public void dp() {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        int k = 3;
        // 定义dp数组
        int[][] dp = new int[prices.length][2 * k + 1];
        // 1、初始化
        for (int i = 1; i < 2 * k; i = i + 2) {
            dp[0][i] = -prices[0];
        }
        // 2、遍历数组
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j < 2 * k; j = j + 2) {
                // 奇数：第j次买入
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] - prices[i]);
                // 偶数：第j次卖出
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] + prices[i]);
            }
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.println("Arrays.toString(dp[i]) = " + i + "  " + Arrays.toString(dp[i]));
        }
        System.out.println("dp[prices.length-1][2*k] = " + dp[prices.length - 1][2 * k]);
    }
}
