package com.junode.leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author junode
 * @version 1.0.0
 * @Description
 * @createTime 2024年04月29日 00:28:00
 */
public class L142 {

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
                System.out.println("true = " + head);
                return;
            }
            head = head.next;
        }
        System.out.println("null");
    }

    // 双指针
    @Test
    public void doublePointer() {
        ListNode head = build();
        if(head == null) {
            return;
        }
        ListNode slow = head, fast = head;
        while(fast != null) {
            slow = slow.next;
            if(fast.next != null) {
                fast = fast.next.next;
            } else {
                return;
            }
            if(slow == fast) {
                ListNode plt = head;
                while(plt != slow) {
                    plt = plt.next;
                    slow = slow.next;
                }
                return ;
            }
        }
        System.out.println("finish");
    }

}
