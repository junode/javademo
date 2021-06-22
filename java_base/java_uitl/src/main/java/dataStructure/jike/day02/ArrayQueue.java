package dataStructure.jike.day02;

/**
 * 数组队列
 * @Author junode
 * @Date 2021/3/17
 */
public class ArrayQueue<E> {
    public E[] queues;
    public int count;
    public int content;

    public ArrayQueue(int content) {
        this.content = content;
        this.count = 0;
        this.queues = (E[])new Object[content];
    }




}
