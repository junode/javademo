package com.junode.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author junode
 * @version 1.0.0
 * @Description LRU缓存实现
 * @createTime 2024年05月15日 21:28:00
 */
public class L146 {

    static class LruDLinkNode{
        private int key;
        private int val;
        private LruDLinkNode prev;
        private LruDLinkNode next;
        public LruDLinkNode(){}
        public LruDLinkNode(int key, Integer val) {
            this.val = val;
            this.key = key;
        }
    }

    private final Map<Integer, LruDLinkNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    // 虚拟头节买的好处，在进行获取和删除时不需要考虑边界判断，如curNode.next = head; head.next = curNode; tail同样如此
    private LruDLinkNode head, tail;

    public L146(int capacity){
        this.size = 0;
        this.capacity = capacity;
        this.head = new LruDLinkNode();
        this.tail = new LruDLinkNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        LruDLinkNode lruDLinkNode = cache.get(key);
        if(lruDLinkNode == null) {
            return -1;
        }
        moveToHead(lruDLinkNode);
        return lruDLinkNode.val;
    }

    public void put(int key, int value) {
        LruDLinkNode lruDLinkNode = cache.get(key);
        if(lruDLinkNode == null) {
            LruDLinkNode addNode = new LruDLinkNode(key, value);
            cache.put(key, addNode);
            addHead(addNode);
            size++;
            if(this.size > this.capacity) {
                LruDLinkNode removeNode = tail.prev;
                // 先删除尾部节点
                removeNode(removeNode);
                // 删除哈希表中的指定key
                cache.remove(removeNode.key);
                size--;
            }
        } else {
            lruDLinkNode.val = value;
            moveToHead(lruDLinkNode);
        }
    }

    private void moveToHead(LruDLinkNode lruDLinkNode) {
        removeNode(lruDLinkNode);
        addHead(lruDLinkNode);
    }

    private void removeNode(LruDLinkNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addHead(LruDLinkNode addNode) {
        head.next.prev = addNode;
        addNode.next = head.next;
        head.next = addNode;
        addNode.prev = head;
    }

    @Test
    public void lruCache() {

    }
}
