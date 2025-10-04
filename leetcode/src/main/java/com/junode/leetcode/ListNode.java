package com.junode.leetcode;

/**
 * @author junode
 * @version 1.0.0
 * @Description
 * @createTime 2024年05月07日 23:36:00
 */
public class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        this.val = x;
        this.next = null;
    }

    ListNode(int x, ListNode next) {
        this.val = x;
        this.next = next;
    }

    public void next(ListNode listNode) {
        this.next = listNode;
    }

    public int val() {
        return this.val;
    }

    public static ListNode build() {
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
}
