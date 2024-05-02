package com.junode.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author junode
 * @version 1.0.0
 * @Description
 * @createTime 2024年04月29日 23:41:00
 */
public class L876 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        public void next(ListNode listNode) {
            this.next = listNode;
        }
    }

    public ListNode build() {
        // 输入：head = [3,2,0,-4], pos = 1
        ListNode listNode4 = new ListNode(-4);

        ListNode listNode0 = new ListNode(0);
        listNode0.next(listNode4);
        ListNode listNode2 = new ListNode(2);
        listNode2.next(listNode0);

        ListNode listNode3 = new ListNode(3);
        listNode3.next(listNode2);
        return listNode3;
    }

    @Test
    public void testArray() {
        ListNode head = build();
        ListNode[] ls = new ListNode[100];
        int t = 0;
        while (head != null) {
            ls[t++] = head;
            head = head.next;
        }
//        return ls[t / 2];
    }

    @Test
    public void doublePoint() {
        ListNode head = build();
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        System.out.println(slow.val);
    }

}
