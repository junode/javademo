package spring.cloud.learn.annotation.inherited;

import java.lang.annotation.*;

/**
 * @Description TODO
 * @Author junode
 * @Date 2020/12/23
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface InheritedAnnotationType {
}
