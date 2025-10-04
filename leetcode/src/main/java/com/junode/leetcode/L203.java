package com.junode.leetcode;

import org.junit.Test;

/**
 * @author junode
 * @version 1.0.0
 * @Description 移除链表元素
 * @createTime 2024年05月07日 23:34:00
 */
public class L203 {

    @Test
    public void withoutDummyHead(){
        // 非虚拟头结点，处理结构不统一
        ListNode head = ListNode.build();
        int val  = 2;
        while(head != null && head.val() == val) {
            head = head.next;
        }
        ListNode cur = head;
        while(cur != null && cur.next != null) {
            if(cur.next.val() == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        System.out.println("head.next.val = " + head.next.val);
    }

    @Test
    public void dummyHead(){
        ListNode head = ListNode.build();
        int val  = 2;
        if(head == null) {
            return;
        }
        ListNode dummyHead = new ListNode(-1, head);
        ListNode cur = dummyHead;
        while(cur != null && cur.next != null) {
            if(cur.next.val() == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        System.out.println("head.next.val = " + head.next.val);
    }
}
