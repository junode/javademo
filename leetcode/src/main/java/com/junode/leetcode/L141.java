package com.junode.leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author junode
 * @version 1.0.0
 * @Description 是否有环。
 * @createTime 2024年04月28日 23:59:00
 */
public class L141 {
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
        listNode4.next(listNode2);

        ListNode listNode3 = new ListNode(3);
        listNode3.next(listNode2);
        return listNode3;
    }

    @Test
    public void hashSetSolve() {
        ListNode head = build();
        Set<ListNode> seen = new HashSet<>();
        while(head != null) {
            if(!seen.add(head)){
                System.out.println("true = " + true);
                return;
            }
            head = head.next;
        }
        System.out.println(false);
    }

    @Test
    public void doublePointer() {
        ListNode head = build();
        ListNode fast = head.next;
        while (head != null) {
            if(head.equals(fast)) {
                System.out.println("true = " + true);
                return;
            }
            head = head.next;
            fast = fast.next.next;
        }
        System.out.println("false = " + false);
    }
}
