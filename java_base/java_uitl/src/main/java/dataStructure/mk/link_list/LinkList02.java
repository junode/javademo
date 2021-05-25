package dataStructure.mk.link_list;

import java.util.regex.Pattern;

/**
 * 链表实现 -- 虚拟头结点
 *
 * @Author junode
 * @Date 2021/3/14
 */
public class LinkList02<E> {
    private class Node {
        E e;
        Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return "" + e;
        }
    }

    private Node dummyHead;
    private int size;

    public LinkList02() {
        this.dummyHead = new Node();
        this.size = 0;
    }

    /**
     * 链表反转参考：https://www.cnblogs.com/keeya/p/9218352.html
     */
    public void reverse() {
        Node pre = null;
        Node cur = dummyHead.next;
        while (cur != null) {
            Node temp = cur;
            Node tempNext = temp.next;
            cur.next = pre;
            pre = temp;
            cur = tempNext;
        }
        dummyHead.next = pre;
    }
    public void reverse2() {
        Node curr = reverse(dummyHead.next);
        dummyHead.next = curr;
    }
    // todo 以递归的方式实现链表反转
    private Node reverse(Node node) {
        if(node == null || node.next == null){
            return node;
        }
        Node temp = node.next; // 获取到当前节点
        Node newNode = reverse(node.next); // 获取到下一个节点
        temp.next = node; // 将下一个节点的next 指向 当前节点
        node.next = null; // 将当前节点的next 与下一节点断开
        return newNode;
    }

    // 获取指定位置元素的值
    public E findElement(int index) {
        if (index < 0 || index > size) throw new IllegalArgumentException("out of index");

        Node cur = dummyHead;

        for (int i = 0; i < index + 1; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    // 获取指定元素的索引
    public int findElementIndex(E e) {
        Node cur = dummyHead;
        int count = 0;
        while (cur.next != null) {
            if (cur.e == e) break;
            count++;
            cur = cur.next;
        }
        return --count; // 减1操作是因为有虚拟头结点
    }

    // 向链表的index位置添加元素
    public void addElement(int index, E e) {
        if (index < 0 || index > size) throw new IllegalArgumentException("out of index");

        Node prevNeedInsert = dummyHead;
        /* 获取到 待插入节点 的 前一个节点，将 将要插入的节点的next指向前一个节点的下一个节点，并将前一个节点的next指向要插入的节点
          注意：next的指向不能调转！ */
        for (int i = 0; i < index; i++) { // 注意：此时遍历的size为size - 1 + 1，+1操作是虚拟头结点
            prevNeedInsert = prevNeedInsert.next;
        }
        /*Node needInsert = new Node(e);
        needInsert.next = prevNeedInsert.next;
        prevNeedInsert.next = needInsert;*/ // 可以化作一个步骤
        prevNeedInsert.next = new Node(e, prevNeedInsert.next);
        size++;
    }

    // 在链表头部添加元素
    public void addFirst(E e) {
        addElement(0, e);
    }

    // 在链表尾部添加元素
    public void addLast(E e) {
        addElement(size, e);
    }

    // 在链表中修改节点元素
    public void updateElement(int index, E e) {
        if (index < 0 || index > size) throw new IllegalArgumentException("out of index");

        Node currNode = dummyHead;
        for (int i = 0; i < index + 1; i++) { // 注意：此时遍历的size为size + 1，+1操作是虚拟头结点
            currNode = currNode.next;
        }
        currNode.e = e;
    }

    // 在链表中删除节点索引：找到删除节点的前一个节点，将前一个节点的next指向下一个节点的next
    public E deleteElement(int index) {
        if (index < 0 || index > size) throw new IllegalArgumentException("out of index");

        Node prevNode = dummyHead;
        for (int i = 0; i < index; i++) { // 注意：此时遍历的size为size - 1 + 1，+1操作是虚拟头结点
            prevNode = prevNode.next;
        }
        Node deleteNode = prevNode.next;
        prevNode.next = deleteNode.next;
        deleteNode.next = null;// 取消指针引用，可以被gc回收
        size--;
        return deleteNode.e;
    }

    // 从链表中删除元素e
    public void deleteElement(E e) {
        Node prevNode = dummyHead;
        while (prevNode.next != null) {
            if (prevNode.next.e.equals(e)) {
                break;
            }
            prevNode = prevNode.next;
        }

        Node needDel = prevNode.next;
        prevNode.next = needDel.next;
        needDel.next = null;
        size--;
    }

    // 链表是否为空
    public boolean isEmpty() {
        return this.size == 0;
    }

    // 获取链表大小
    public int getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = dummyHead.next;
        while (cur != null) {
            sb.append(cur).append("->");
            cur = cur.next;
        }
        sb.append("null");
        return sb.toString();
    }

}
