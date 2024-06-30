package com.junode.leetcode;

import org.junit.Test;

/**
 * @author junode
 * @version 1.0.0
 * @Description 爬楼梯
 * @createTime 2024年05月23日 22:47:00
 */
public class L70 {

    @Test
    public void test(){
        int n = 5;
        for (int i = 0; i < 6; i++) {
            System.out.println("getClimbMethod(i) = " + getClimbMethod(i));;
        };
    }

    public int getClimbMethod(int cur) {
        if(cur == 0) return 0;
        if(cur == 1) return 1;
        if(cur == 2) return 2;
        return getClimbMethod(cur-1) + getClimbMethod(cur-2);
    }

    @Test
    public void testWhile() {
        int n = 5;

        if(n<3) {
            System.out.println("1 = " + n + "");
            return;
        }
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        System.out.println("dp = " + dp[n]);
    }
}
