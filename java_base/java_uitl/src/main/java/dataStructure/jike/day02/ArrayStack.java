package dataStructure.jike.day02;


/**
 * 数组栈
 * @Author junode
 * @Date 2021/3/17
 */
public class ArrayStack<E> {
    private E[] stacks; // 存数据的容器
    private int count; // 栈当前数量
    private int content; // 栈的大小

    private Double factor = 0.75; // 扩容/缩容因子

    public ArrayStack(int content){
        this.content = content;
        this.count = 0;
        this.stacks = (E[])new Object[content];
    }

    /**
     * 入栈，添加元素
     * @param e
     * @return 是否添加元素成功
     */
    public boolean pull(E e) {
        if (this.count == this.content) return false;
        this.stacks[this.count] = e;
        this.count++;
        if(this.count >= this.content * factor) {
            Double addContent = this.content * factor;
            tableResize(this.count + addContent.intValue()); // 扩容
        }
        return true;
    }

    private void tableResize(int resize) {
        E[] temps = (E[])new Object[resize];
        for (int i = 0; i < count; i++) {
            temps[i] = this.stacks[i];
        }
        this.stacks = temps;
        this.content = resize;
    }

    /**
     * 出栈
     */
    public E pop() {
        if(this.count <= 0) return null;
        E temp = this.stacks[this.count - 1];
        this.count--;
        if(this.count >= this.content * factor) {
            Double addContent = this.content * factor;
            tableResize(this.count - addContent.intValue()); // 缩容
        }
        return temp;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("start->");
        for (int i = 0; i < count; i++) {
            sb.append(stacks[i]).append("->");
        }
        return sb.append("end").toString();
    }
}
