package com.junode.leetcode;

import java.util.PriorityQueue;

/**
 * 有效的正方形：给四个点，判断是否是正方形
 */
public class L593 {
    public static void main(String[] args) {
        int[] a = new int[2];
        a[0] = 0;
        a[1] = 0;
        int[] b = new int[2];
        b[0] = 0;
        b[1] = 1;
        int[] c = new int[2];
        c[0] = 1;
        c[1] = 0;
        int[] d = new int[2];
        d[0] = 1;
        d[1] = 1;
        System.out.println(isQuare(a, b, c, d));
    }


    public static boolean isQuare(int[] p1, int[] p2, int[] p3, int[] p4) {
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((para1,para2)->{
            if(para1[0] != para2[0]) {
                return para1[0] - para2[0];
            }else {
                return para1[1] - para2[1];
            }
        });
        queue.offer(p1);
        queue.offer(p2);
        queue.offer(p3);
        queue.offer(p4);
        p1 = queue.poll();
        p2 = queue.poll();
        p3 = queue.poll();
        p4 = queue.poll();
        int sideab = getSideLen(p1,p2);
        int sidebc = getSideLen(p2,p4);
        int sidecd = getSideLen(p4,p3);
        int sideda = getSideLen(p1,p3);
        int sideac = getSideLen(p1,p4);
        int sidebd = getSideLen(p2,p3);
        if(sideab != 0 && sideab == sidebc && sidebc == sidecd && sidecd == sideda && sideac == sidebd ) {
            return true;
        }
        return false;
    }
    public static int getSideLen(int[] p1,int[] p2) {
        return (p2[0] - p1[0]) * (p2[0] - p1[0]) + (p2[1] - p1[1]) * (p2[1] - p1[1]);
    }
}
