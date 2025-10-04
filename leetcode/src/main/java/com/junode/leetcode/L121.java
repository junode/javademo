package com.junode.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author junode
 * @version 1.0.0
 * @Description 获取股票的最大利润
 * @createTime 2024年05月04日 00:45:00
 */
public class L121 {

    /**
     * 采用动态规划的算法
     * 五步走：
     * 1、定义dp[i]的含义：
     *      dp[i][0]表示持有股票的最大现金
     *      dp[i][1]表示第i天不持有股票的最大现金
     * 2、递推公式：股票买卖，依赖于前一天的买入/卖出的状态，从而与dp[i-1]有关
     *      dp[i][0]：表示持有股票时的最大现金，该状态和结果的来源有两种情况：
     *          1、第i天执行买入；
     *          2、第i-1天或之前就买入，一直持有，则其状态与前一天的相同；
     *          dp[i][0] = max(dp[i-1][0], -price[i])
     *      pd[i][1]：表示不持有股票时的最大现金，该状态和结果的来源有两种情况：
     *          1、第i天的卖出；
     *          2、第i-1天或之前就卖出了，该状态和前一天的相同
     *          dp[i][1] = max(dp[i-1][0]+price[i], dp[i-1][1])
     *  3、状态初始化
     *      dp[0][0] = -price[0]
     *      dp[0][1] = 0
     *   4、数组遍历： 前序遍历（依赖前面的状态）
     *   5、打印dp
     */
    @Test
    public void dp() {
        int[] prices = new int[]{7,1,5,3,6,4};
        // 1、初始化
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            // 2、递归公式。用-prices[i]表示执行买入操作，现金减少。卖出，现金增加
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        System.out.println("dp[prices.length-1][1] = " + dp[prices.length - 1][1]);
    }

    @Test
    public void test() {
        // 下面的用if else 难看，有Max比较好理解
        int[] prices = new int[]{7,1,5,3,6,4};
        int cost = Integer.MAX_VALUE, profit = 0;
        for (int price : prices) {
            cost = Math.min(cost, price);
            profit = Math.max(profit, price - cost);
        }
        System.out.println("profit = " + profit);
    }


    @Test
    public void doublePointer() {
        int[] prices = new int[]{7,1,5,3,6,4};
        // 最低成本用来获取记忆，后续获取最大价格差；最高利润，就是用来记忆历史最大的利润
        // 两个变量，最低成本，最高利润（但仅记录最高价格还不行，需要记录利润，因为需要考虑成本）
        int[] costAndProfile = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < costAndProfile[0]) costAndProfile[0] = prices[i];
            if (prices[i] - costAndProfile[0] > costAndProfile[1]) costAndProfile[1] = prices[i] - costAndProfile[0];
        }
        System.out.println(costAndProfile[1]);
    }
}
