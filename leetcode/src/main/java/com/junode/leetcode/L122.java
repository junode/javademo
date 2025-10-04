package com.junode.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author junode
 * @version 1.0.0
 * @Description 买卖股票的最佳时机Ⅱ
 * @createTime 2024年05月24日 11:51:00
 */
public class L122 {

    /**
     * 先看121，再看122。121和122的差别就是需要考虑卖出之后，最大利润需要包含。其变化主要是在递归公式上。
     * 递推公式：
     *  1、dp[i][0] = max(dp[i-1][0], dp[i-1][1] - price[i])
     *      dp[i][0] 表示持有股票时的最大现金。第i天持有状态的状态的形成有两种情况。
     *          1、昨天及以前就一直持有，一直到现在；
     *          2、之前没有持有，当天买入，但此时因为多次买卖，需要将之前操作的现金涵盖进来
     *
     *  2、dp[i][0] = max(dp[i-1][1], dp[i-1][0] + price[i])
     *      dp[i][0] 表示不持有股票时的最大现金。第i天不持有股票的状态的形成有两种情况。
     *          1、昨天及之前不持有，一直到现在；
     *          2、之前持有，当天执行卖出操作
     */
    @Test
    public void dp() {
        int[] prices = new int[]{7,1,5,3,6,4};
        // 先想清楚递推公式，然后再进行初始化，但是代码是先写初始化，再for遍历递推公式
        int len = prices.length;
        // 这里的容量 2 ：表示股票状态，0：持有，1 不持有
        int[][] dp = new int[len][2];
        // 初始化。初始时的买入操作，需要消费现金，从而是负数
        dp[0][0] = -prices[0];
        // 初始时没有执行买卖，从而不持有时的现金为0.
        dp[0][1] = 0;
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + prices[i]);
        }
        System.out.println("dp[len-1][1] = " + dp[len - 1][1]);
    }

    @Test
    public void test() {
        // 贪心。来自carl视频。获取每次利润为正的买卖操作的结果
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            result += Math.max(prices[i] - prices[i - 1], 0);
        }
        System.out.println("result = " + result);
    }

    @Test
    public void test2() {
        int[] compare = new int[]{1, 2, 4, 3, 10, 13, 15, 12, 16, 18, 17};
        int leftMaxVal = Integer.MIN_VALUE;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < compare.length; i++) {
            if (leftMaxVal > compare[i]) {
                continue;
            } else {
                leftMaxVal = compare[i];
            }
            boolean flag = true;
            for (int j = i+1; j < compare.length; j++) {
                if(compare[i] > compare[j]) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                result.add(compare[i]);
            }
        }
        System.out.println("result = " + Arrays.toString(result.toArray()));
    }
}
