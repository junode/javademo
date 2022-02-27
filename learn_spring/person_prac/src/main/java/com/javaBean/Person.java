package com.javaBean;

/**
 * 描述人的 POJO 类
 * Setter / Getter 方法，在bean中称为：
 * 可写方法(Writable) / 可读方法(Readable)
 * @Author junode
 * @Date 2021/2/27
 */
public class Person {

    // 类型转换 String to String
    String name; // name 被称作 Property
    // 类型转换 String to Integer
    Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
