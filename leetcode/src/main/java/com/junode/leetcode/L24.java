package com.junode.leetcode;

import org.junit.Test;

/**
 * @author junode
 * @version 1.0.0
 * @Description 两两交换链表中的节点
 * @createTime 2024年05月10日 23:35:00
 */
public class L24 {

    @Test
    public void swap() {
//        参考： https://www.bilibili.com/video/BV1YT411g7br/?p=9&spm_id_from=pageDriver
        ListNode head = ListNode.build();
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        // 第一个是判断为偶数链表个数时的终止条件，第二个是判断为奇数时的终止条件
        while(cur.next != null && cur.next.next != null) {
            // 画图理解
            ListNode temp = cur.next;
            ListNode temp1 = cur.next.next.next;
            cur.next = cur.next.next;
            cur.next.next = temp;
            temp.next = temp1;
            cur = cur.next.next;
        }
        System.out.println("dummy = " + dummy.next);
    }
}
