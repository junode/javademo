package com.demo.java_base;

import java.util.HashSet;
import java.util.Set;

public class CollectionDemo01 {

    public static void main(String[] args) {
//        testFor();
        testHashSet();
    }

    public static void testFor() {
        String[] str = new String[5];
        for (String mystr : str) {
            mystr = "demofor";
            System.out.println(mystr);
        }
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
    }

    public static void testHashSet() {
        Set set = new HashSet<>();
        set.add(new Integer(123));
        System.out.println("添加125：" + set.add(125));
        System.out.println("添加125：" + set.add(125));
        set.add("abcdf");
        set.add(new String("abcdfghi"));
        set.add(new Student());

        // 已完成Set集合的存储
        System.out.println("Set集合是否为空：" + set.isEmpty());
        System.out.println("Set集合是否包含123：" + set.contains(123));
        System.out.println("Set集合中的元素个数：" + set.size());

        for (Object o : set) {
            System.out.println(o);
        }

    }
}

class Student {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
