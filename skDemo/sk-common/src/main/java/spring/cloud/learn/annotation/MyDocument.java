package spring.cloud.learn.annotation;

import java.lang.annotation.Documented;

///**
// * @description: 自定义document注解
// * @author: junode
// * @create: 2020-12-21 23:16
// **/
@Documented
public @interface MyDocument {
    public String key() default "Linkage";
    public String value() default "www.baidu.com";
}
