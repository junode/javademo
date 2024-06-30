package com.junode.leetcode;

import org.junit.Test;

/**
 * @author junode
 * @version 1.0.0
 * @Description 链表反转
 * @createTime 2024年05月10日 22:51:00
 */
public class L206 {
    
    @Test
    public void doublePointer() {
        ListNode head = ListNode.build();
        ListNode cur = head;
        ListNode pre = null;
        while(cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        System.out.println("head = " + pre);
    }

    @Test
    public void reverse() {
        ListNode head = ListNode.build();
        // 这里的入参参考双指针的初始化操作
        ListNode result = executeReverse(head, null);
        System.out.println("result = " + result);

    }

    public ListNode executeReverse(ListNode cur, ListNode pre) {
        if(cur == null) {
            return pre;
        }
        // 这里的指向改变操作操找双指针的交换操作
        ListNode temp = cur.next;
        cur.next = pre;

        // 这里递归的入参参考双指针的赋值操作
        return executeReverse(temp, cur);
    }
}
