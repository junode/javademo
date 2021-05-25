package dataStructure.jike.day02;

/**
 * @Author junode
 * @Date 2021/3/17
 */
public class Test {

    public static void main(String[] args) {
        ArrayStack<String> stack = new ArrayStack<>(4);
        for (int i = 0; i < 10; i++) {
            stack.pull(i + "");
        }
        System.out.println(stack);
        for (int i = 0; i < 10; i++) {
            if( i % 2 == 0){
                stack.pop();
            }
        }

        System.out.println(stack);
    }
}
