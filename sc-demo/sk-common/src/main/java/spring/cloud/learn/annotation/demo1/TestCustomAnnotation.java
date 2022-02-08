package spring.cloud.learn.annotation.demo1;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @description: 关于注解的使用demo。来源：www.javatpoint.com
 * Example of custom annotation: creating, applying and accessing annotation
 * @author: hitton
 * @create: 2020-12-23 23:14
 **/
public class TestCustomAnnotation {
    @Test
    public void test1() throws NoSuchMethodException {
        Hello hello = new Hello();
        Method sayHello = hello.getClass().getMethod("sayHello");
        MyAnnotation annotation = sayHello.getAnnotation(MyAnnotation.class);
        System.out.println("获取到注解上的值为： "+annotation.value());
    }
}
