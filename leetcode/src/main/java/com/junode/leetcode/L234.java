package com.junode.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author junode
 * @version 1.0.0
 * @Description
 * @createTime 2024年05月01日 00:28:00
 */
public class L234 {
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
    public void doublePointer(){
        List<Integer> result = new ArrayList<>();

        // 从链表中复制到数组中
        ListNode head = build();
        ListNode forCopy = head;

        while(forCopy != null) {
            result.add(forCopy.val);
            forCopy = forCopy.next;
        }

        int start = 0;
        int end = result.size() - 1;
        while(start < end) {
            if(result.get(start) != result.get(end)) {
                return;
            }
            start++;
            end--;
        }
        System.out.println("true = " + true);
    }

    @Test
    public void fastSlowPoint() {
        // 1、先找到中点。快指针走两步，慢指针走一步
        ListNode head = build();
        ListNode mid = midNode(head);
        // 2、将后半部分反转，得到一个新链表，返回头节点
        // 3、顺序比较两个头结点，若不想等则直接返回false，否则直至新链表达到尾部
    }

    public ListNode reverseList(ListNode node) {
        if(node==null) return null;
        // 太难了，放弃
        return null;
    }

    public ListNode midNode(ListNode node) {
        ListNode slow = node,fast = node;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;

    }
}
