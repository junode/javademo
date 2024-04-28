package com.junode.generic;

import java.lang.reflect.Array;

/**
 * @author junode
 * @version 1.0.0
 * @Description
 * @createTime 2024年04月13日 13:44:00
 */
public class GenericArray<T> {
    private T[] array;

    public GenericArray(Class<T> type, int size) {
        // 使用Array.newInstance来创建泛型类型的数组
        array = (T[]) Array.newInstance(type, size);
    }

    public T get(int index) {
        return array[index];
    }

    public void set(int index, T value) {
        array[index] = value;
    }

    public static void main(String[] args) {
        GenericArray<Integer> integerArr = new GenericArray(Integer.class, 10);
        // 设置第0个位置的元素为123
        integerArr.set(0, 123);

        // 获取并打印第0个位置的元素
        Integer num = integerArr.get(0);
        System.out.println(num); // 输出123

    }
}
