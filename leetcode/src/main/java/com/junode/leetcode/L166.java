package com.junode.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author junode
 * @version 1.0.0
 * @Description 分数到小数。给定分子和分母，返回对应相除之后的字符串，若除不尽，则用括号()将除不尽的括起来
 * @createTime 2024年05月25日 09:08:00
 */
public class L166 {

    @Test
    public void test() {
        int numerator = 3, denominator = 4;
        long copyA = numerator;
        long copyB = denominator;
        System.out.println("helper(copyA, copyB) = " + helper(copyA, copyB));
    }

    public String helper(long copyA, long copyB) {
        // 若能整除，则直接返回结果
        if (copyA % copyB == 0) {
            return String.valueOf(copyA / copyB);
        }
        // 处理负数
        if (copyA / copyB < 0) {
            return "-" + helper(Math.abs(copyA), Math.abs(copyB));
        }

        // 采用map维护每一个余数对应在结果字符串的索引下标位置
        Map<Long, Integer> balanceSbIndex = new HashMap<>();
        // 用于记录结果
        StringBuilder sb = new StringBuilder();
        // 记录整数部分
        sb.append(copyA / copyB);
        // 添加小数点部分
        sb.append(".");
        // 获取余数部分
        long sub = copyA % copyB;
        // 当余数不为0，则表明可以继续往下整除
        while (sub != 0) {
            if (balanceSbIndex.get(sub) != null) {
                sb.insert(balanceSbIndex.get(sub),"(");
                sb.append(")");
                break;
            }
            // 记录每一个余数相应的位置
            balanceSbIndex.put(sub, sb.length());
            // 模拟除法，扩大10
            sub = sub * 10;
            sb.append(sub / copyB);
            // 计算下一位
            sub = sub % copyB;
        }
        return sb.toString();
    }
}
