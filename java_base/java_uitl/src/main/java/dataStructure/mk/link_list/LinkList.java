package dataStructure.mk.link_list;

/**
 * 链表实现
 * <p>
 * 后续的修改与删除操作在 {@link LinkList02} 完成
 * @Author junode
 * @Date 2021/3/14
 */
public class LinkList<E> {
    private class Node{
        E e;
        Node next;
        public Node(E e,Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e,null);
        }

        public Node() {
            this(null,null);
        }
    }

    private Node head;
    private int size;

    public LinkList(){
        this.head = null;
        this.size = 0;
    }

    // 向链表的index位置添加元素
    public void addElement(int index,E e){
        if(index < 0 || index > size) throw new IllegalArgumentException("out of index");

        Node prevNeedInsert = head;
        /* 获取到 待插入节点 的 前一个节点，将 将要插入的节点的next指向前一个节点的下一个节点，并将前一个节点的next指向要插入的节点
          注意：next的指向不能调转！ */
        if(size == 0) {
            this.head = new Node(e,null);
        }else {
            for (int i = 0; i < index - 1; i++) {
                prevNeedInsert = prevNeedInsert.next;
            }
        }
        /*Node needInsert = new Node(e);
        needInsert.next = prevNeedInsert.next;
        prevNeedInsert.next = needInsert;*/ // 可以化作一个步骤
        prevNeedInsert.next = new Node(e,prevNeedInsert.next);
        size ++;
    }

    // 在链表头部添加元素
    public void addFirst(E e) {
        addElement(0,e);
    }

    // 在链表尾部添加元素
    public void addLast(E e) {
        addElement(size,e);
    }

    // 链表是否为空
    public boolean isEmpty(){
        return this.size == 0;
    }

    // 获取链表大小
    public int getSize(){
        return this.size;
    }

}
