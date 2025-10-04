package com.junode.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author junode
 * @version 1.0.0
 * @Description 螺旋矩阵
 * @createTime 2024年05月05日 23:47:00
 */
public class L59 {

    @Test
    public void rotate() {
        int n = 3;
        int[][] result = new int[n][n];
        // 参考： https://www.bilibili.com/video/BV1SL4y1N7mV/?p=5&spm_id_from=pageDriver
        // 遍历规则为：[,)，左闭右开
        // X轴和Y轴每转一圈的偏移位置，开始位置为1.
        int offset = 1;
        // startX, startY，X轴和Y轴分别转圈圈时的起始位置。
        int count = 1, startX = 0, startY = 0;
        int loop = n / 2;
        int i = 0, j = 0;
        while (loop > 0) {
            for (j = startY; j < n - offset; j++) {
                result[startX][j] = count++;
            }
            for (i = startX; i < n - offset; i++) {
                result[i][j] = count++;
            }
            for (; j > startY; j--) {
                result[i][j] = count++;
            }
            for (; i > startX; i--) {
                result[i][j] = count++;
            }
            startX++;
            startY++;
            offset++;
            loop--;
        }
        if (n % 2 != 0) {
            result[n / 2][n / 2] = count;
        }
        for (int[] ints : result) {
            System.out.println("Arrays.toString(result) = " + Arrays.toString(ints));
        }
    }
}
