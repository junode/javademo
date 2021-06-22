package dataStructure.mk.link_list;

/**
 * 链表测试
 * @Author junode
 * @Date 2021/3/14
 */
public class Test {
    public static void main(String[] args) {
        LinkList02<Integer> linkList = new LinkList02<>();
        for (int i = 0; i < 6; i++) {
            linkList.addLast(i);
            System.out.println(linkList);
            // 反转链表
//            linkList.reverse();
//            System.out.println(linkList);
        }
        linkList.reverse2();
        System.out.println(linkList);
        /*// 删除指定位置元素
        linkList.deleteElement(2);
        System.out.println(linkList);
        // 更新指定位置元素
        linkList.updateElement(2,33);
        System.out.println(linkList);
        // 添加指定位置元素
        linkList.addElement(3,44);
        System.out.println(linkList);

        // 查找刚才被修改的位置元素
        System.out.println(linkList.findElement(3));

        // 查找刚被修改元素的下标
        System.out.println(linkList.findElementIndex(44));
        System.out.println(linkList.findElementIndex(1));*/




    }
}
