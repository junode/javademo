package dataStructure.jike;

/**
 * 递归学习 第10小节
 *
 * @Author junode
 * @Date 2021/3/24
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(step(5));
    }

    private static int step(int total) {
        if (total == 1) return 1;
        if (total == 2) return 2;
        return step(total - 1) + step(total - 2);
    }
}
