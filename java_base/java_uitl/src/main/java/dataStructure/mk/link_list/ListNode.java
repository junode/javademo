package dataStructure.mk.link_list;

/**
 * @Author junode
 * @Date 2021/3/20
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) {
        this.val = x;
    }

    public ListNode(int[] values) {
        if(values == null || values.length == 0) {
            throw new IllegalArgumentException("参数出错");
        }

        this.val = values[0];
        ListNode cur = this;
        for (int i = 1; i < values.length; i++) {
            cur.next = new ListNode(values[i]);
            cur = cur.next;
        }
    }

    // 以当前节点为头结点的链表信息字符串
    @Override
    public String toString(){

        StringBuilder s = new StringBuilder();
        ListNode cur = this;
        while(cur != null){
            s.append(cur.val + "->");
            cur = cur.next;
        }
        s.append("NULL");
        return s.toString();
    }
}
