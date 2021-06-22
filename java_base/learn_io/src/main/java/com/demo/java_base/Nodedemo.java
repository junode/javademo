package com.demo.java_base;


/**
 * 链表demo
 */
public class Nodedemo {

    private Object value;
    private Nodedemo next;

    public Nodedemo(Object value, Nodedemo next) {
        this.value = value;
        this.next = next;
    }

    public static void main(String[] args) {
        // 创建头结点
        Nodedemo nd = new Nodedemo("123", null);
        Nodedemo nd2 = new Nodedemo("456", null);
        nd.next = nd2;

        // 在这个demo中，我们可以发觉，在头部添加节点操作是最快的，如：
        Nodedemo nd3 = new Nodedemo("789", nd);

        // 而若是想插入链表尾部的化，那么就需要遍历链表，直到next为null，就到了尾部，从而在头部插入是最快的。

    }
}
