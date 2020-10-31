package dataStructure;

/**
 * @Auther: zwy
 * @Date: 2020/8/6
 * @Description: 这个是链表
 */
public class Ch02_LinkList {

    private Node[] nodes = new Node[]{};

    class Node{
        private int value;
        private Node next;

        public Node(Integer value,Node next){
            this.value = value;
            this.next = next;
        }
    }

    public Ch02_LinkList initList(){
        return new Ch02_LinkList();
    }
}
