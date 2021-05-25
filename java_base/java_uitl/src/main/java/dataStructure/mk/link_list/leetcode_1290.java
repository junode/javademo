package dataStructure.mk.link_list;

/**
 * @Author junode
 * @Date 2021/3/20
 */
public class leetcode_1290 {
    public static void main(String[] args) {
        int[] arr = {1, 0, 1};

        ListNode head = new ListNode(arr);

        ListNode temp = head;
        int index = -1;
        while (temp != null) {
            index++;
            temp = temp.next;
        }
        int result = 0;
        while (head != null) {
            if (head.val == 1) {
                result += (int) Math.pow(2, index);
            }
            index--;
            head = head.next;
        }
        System.out.println(result);

    }
}
