package com.demo.io_jdbc.entity;

/**
 * @Auther: zwy
 * @Date: 2020/6/10
 * @Description: 用户信息表
 * @version:
 */
public class UserInfo {
    private String name;
    private Integer age;

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


    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
