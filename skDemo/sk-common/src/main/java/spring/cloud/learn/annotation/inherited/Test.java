package spring.cloud.learn.annotation.inherited;

/**
 * @Description 注解测试类
 * @Author junode
 * @Date 2020/12/23
 */

public class Test {
    
    /**
    * @Description
    * @Param
    * @Date 2020/12/23 10:35 PM
    * @Author hitton
    * @return
    **/
    @org.junit.Test
    public void test(){
        System.out.println(new ClassA().getClass().getAnnotation(InheritedAnnotationType.class));
        System.out.println(new ClassB().getClass().getAnnotation(InheritedAnnotationType.class));
        // 因ClassB标注的@InheritedAnnotationType注解中有使用@Inherited标注，从而C继承了B上的注解。
        System.out.println(new ClassC().getClass().getAnnotation(InheritedAnnotationType.class));
        System.out.println(new ClassD().getClass().getAnnotation(InheritedAnnotationType.class));
        System.out.println("_________________________________");
        System.out.println(new ClassA().getClass().getAnnotation(UninheritedAnnotationType.class));
        // 因B上没有@UninheritedAnnotationType注解，从而为空。
        System.out.println(new ClassB().getClass().getAnnotation(UninheritedAnnotationType.class));
        // C上去B上找UninheritedAnnotationType注解，但是B上没有，从而返回为空。
        System.out.println(new ClassC().getClass().getAnnotation(UninheritedAnnotationType.class));
        // 虽然ClassD继承了ClassA，但是并不会获取到ClassA类的注解信息。
        System.out.println(new ClassD().getClass().getAnnotation(UninheritedAnnotationType.class));
    }
}
