package com.junode.data.structure;

/**
 * @author junode
 * @version 1.0.0
 * @Description 单向链表。
 * @createTime 2024年05月04日 21:24:00
 */
public class SingleLinkList<E> {

    private transient Node<E> first;
    private transient Node<E> last;
    private int size;

    private static class Node<E> {
        E val;
        Node<E> next;

        public Node(E val, Node<E> next) {
            this.val = val;
            this.next = next;
        }
    }

    public SingleLinkList(E val) {
        this.first = new Node(val, null);
        this.last = null;
        this.size = 1;
    }

    public SingleLinkList(E val, E next) {
        if (next == null) {
            new SingleLinkList(val);
        } else {
            this.last = new Node(next, null);
            this.first = new Node(val, last);
            this.size = 2;
        }
    }

    public E getFirst() {
        return first.val;
    }

    public E getLast() {
        return last.val;
    }

    public Object[] toArray() {
        Object[] arr = new Object[size];
        int i = 0;
        for (Node cur = first; cur != null; cur = cur.next) {
            arr[i++] = cur.val;
        }
        return arr;
    }

    private Node get(int index) {
        Node temp = first;
        for (int i = 0; temp != null; temp = temp.next, i++) {
            if (i == index) {
                return temp;
            }
        }
        return null;
    }

    public void set(E val, int index) {
        Node temp = get(index - 1);
        if (temp != null) {
            Node next = temp.next;
            temp.next = new Node(val, next);
            this.size++;
        } else {
            // 没有找到对应的元素，越界了
        }
    }

    public void remove(E val) {
        if(val == null){
            if(first == null) {
                return;
            }
            for (Node pre = first, cur = first.next; cur != null; cur = cur.next,pre = pre.next) {
                if(val == cur.val) {
                    pre.next = cur.next;
                    cur.next = null;
                }
            }
        }else {
            if(first == null || first.val == val) {
                Node next = first.next;
                first.next = null;
                first = next;
            }else {
                for (Node pre = first, cur = first.next; cur != null; cur = cur.next,pre = pre.next) {
                    if(val.equals(cur.val)) {
                        pre.next = cur.next;
                        cur.next = null;
                    }
                }
            }
        }
        size--;
    }
}
