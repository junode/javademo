package spring.cloud.learn.annotation.inherited;

/**
 * @Description TODO
 * @Author junode
 * @Date 2020/12/23
 */

public class Test {

    public static void main(String[] args) {
        System.out.println(new ClassA().getClass().getAnnotation(InheritedAnnotationType.class));
        System.out.println(new ClassB().getClass().getAnnotation(InheritedAnnotationType.class));
        System.out.println(new ClassC().getClass().getAnnotation(InheritedAnnotationType.class));
        System.out.println("_________________________________");
        System.out.println(new ClassA().getClass().getAnnotation(UninheritedAnnotationType.class));
        System.out.println(new ClassB().getClass().getAnnotation(UninheritedAnnotationType.class));
        System.out.println(new ClassC().getClass().getAnnotation(UninheritedAnnotationType.class));
    }
}
