package spring.cloud.learn.annotation.demo1;

/**
 * @description: 用来测试MyAnnotaion注解的使用
 * @author: hitton
 * @create: 2020-12-23 23:16
 **/
public class Hello {

    @MyAnnotation(value = 100)
    public void sayHello(){
        System.out.println("hello world");
    }
}
