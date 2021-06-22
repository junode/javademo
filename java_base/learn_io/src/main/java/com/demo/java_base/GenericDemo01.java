package com.demo.java_base;

public class GenericDemo01 {
}

class Person2<T> {
    // 使用T类型定义变量
    private T info;

    // 使用T类型定义一般方法
    public T getInf() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public Person2() {
    }

    public Person2(T info) {
        this.info = info;
    }
}