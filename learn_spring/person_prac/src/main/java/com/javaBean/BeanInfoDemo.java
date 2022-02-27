package com.javaBean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.Arrays;

/**
 * @Description Java beans示例
 * @Author junode
 * @Date 2021/2/27
 */
public class BeanInfoDemo {

    public static void main(String[] args) throws IntrospectionException {
        // 排除Object.class,是因为Object.class中有一个getClass()方法，是一个可读方法，但并不是Person.class类的方法
        // 若只想打印当前类的可读可写方法，则第二个参数可以打断 Java bean自省 查找父类方法。
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class,Object.class);

        Arrays.stream(beanInfo.getPropertyDescriptors()).forEach(
                propertyDescriptor -> {
                    // PropertyDescriptor 允许添加属性编辑器 PropertyEditor
                    // GUI —> text(String) -> PropertyType
                    // name -> String
                    // age -> Integer
                    Class<?> properType = propertyDescriptor.getPropertyType();
                    String properName = properType.getName();
                    // 我们这边事先知道age这字段和类型，从而在这边做个拦截
                    if("age".equals(properName)) { // 为"age“字段/属性添加 PropertyEditor
                        // 这里对age进行转换，输入的是String -> Integer
                        // 这里如何转换呢？需要通过propertyDescriptor 关联 PropertyEditor，从而需要实现 PropertyEditor
                        propertyDescriptor.setPropertyEditorClass(StringtoIntegerPropertyEditor.class);
                        // 继而我们需要去创建PropertyEditor，同时要把我们的bean扔进去
//                        propertyDescriptor.setPropertyEditorClass(Person.class);
                    }
                }
        );
    }
    static class StringtoIntegerPropertyEditor extends PropertyEditorSupport{
        // 重写这个拦截器方法
        public void setAsText(String text) throws java.lang.IllegalArgumentException {
            Integer value = Integer.valueOf(text);
            setValue(value); // 转换之后需要将值存储起来（临时存储），为了后面取出
        }
    }

}
