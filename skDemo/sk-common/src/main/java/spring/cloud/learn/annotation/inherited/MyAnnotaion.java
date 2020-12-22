package spring.cloud.learn.annotation.inherited;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 自定义注解，用于测试@Inherited注解
 * @author: junode
 * @create: 2020-12-22 23:23
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotaion {

}
