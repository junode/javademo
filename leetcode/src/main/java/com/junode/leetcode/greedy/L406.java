package com.junode.leetcode.greedy;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author junode
 * @version 1.0.0
 * @Description 根据身高重建队列
 * @createTime 2024年06月05日 23:56:00
 */
public class L406 {

    @Test
    public void tryGreedy() {
        // 贪心算法，尝试局部最优解，若是局部最优解有，且找不到反例，则用；
        // 当涉及到多个纬度时，先固定一个纬度，再考虑另一个纬度
        int[][] people = new int[][]{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        // 对身高排序（在思考的时候，也可以尝试按照第二纬度排序）
        Arrays.sort(people, (a,b)->{
            // 身高相等，则按照看到的人数进行升序排序
            if(a[0]==b[0]) return a[1]-b[1];
            return b[0]-a[0];
        });
        List<int[]> result = new LinkedList<>();

        // [7,0], [7,1], [6,1], [5,0], [5,2], [4,4]
        // 再一个一个插入。
        // [7,0]
        // [7,0], [7,1]
        // [7,0], [6,1], [7,1]
        // [5,0], [7,0], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [4,4], [7,1]
        for(int[] i : people) {
            result.add(i[1],i);
        }
        for (int[] ele : result.toArray(new int[result.size()][2])) {
            System.out.println("Arrays.toString(ele) = " + Arrays.toString(ele));
        }
    }
}
