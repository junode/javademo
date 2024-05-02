package com.junode.leetcode;

import org.junit.Test;

/**
 * @author junode
 * @version 1.0.0
 * @Description
 * @createTime 2024年04月30日 23:17:00
 */
public class L19 {
    static class ListNode {
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
    public void testDouleLoop(){
        ListNode head = build();
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        int remove = 2;
        ListNode cur = dummy;
        for (int i = 1; i <length-remove+1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        System.out.println("dummy.next.val = " + dummy.next.val);

    }

    private int getLength(ListNode listNode){
        int len = 0;
        while(listNode.next != null) {
            len ++;
            listNode = listNode.next;
        }
        return len;
    }


    @Test
    public void doublePointer(){
        // 设置一个快指针，往前走n步后，然后再设置一个慢指针，从头开始，与快指针一起往后走，当快指针到达末尾时，此时慢指针指向的位置下一个就是要删除的节点。
        ListNode head = build();
        // 为了与倒数第n个相匹配，这里补充一个虚拟头结点，因为我们指标指向1。
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy, fast = head;
        int remove = 2;
        for (int i = 0; i < remove; i++) {
            fast = fast.next;
        }
        while(fast != null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        System.out.println("dummy.next.val = " + dummy.next.val);
    }
}
