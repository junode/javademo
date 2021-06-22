package com.demo.java_base;

import java.util.*;

public class HashMapTest {

    public static void main(String[] args) {
//        test1();
        testHashSet();
    }

    public static void test1() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(new Integer(1), "first");
        map.put(new Integer(2), "second");
        map.put(new Integer(3), "third");
        map.put(new Integer(4), "forth");

        Set key = map.keySet();
        System.out.println(key);

        Collection value = map.values();
        System.out.println(value);

        Set kvPari = map.entrySet();
        System.out.println(kvPari);


        String str = map.get(2);
        System.out.println(str);
    }

    // 面试题，给定一个字符串，统计每个字符出现的次数
    public static void test2(String str) {
//        char[] chars = str.toCharArray();
        List<String> chars = Arrays.asList(str);
    }

    //判断数组中是否包含特定值
    public static void containsValue(String[] arr, String targetValue) {
        System.out.println(Arrays.asList(arr).contains(targetValue));
    }

    // 判断数组中是否包含特定值
    public static void containsValue2(String[] arr, String targetValue) {
        Set<String> set = new HashSet<String>(Arrays.asList(arr));
        System.out.println(set.contains(targetValue));
    }

    // 利用for循环判断是否有值
    public static void containsValue3(String[] arr, String targetValue) {
        for (String demo : arr) {
            if (demo.equals(targetValue)) {
                System.out.println(true);
                return;
            }
        }
        System.out.println(false);
    }

    public static void testHashSet() {
        HashSet set = new HashSet();
        Person p1 = new Person(1001, "AA");
        Person p2 = new Person(1002, "BB");

        set.add(p1);
        set.add(p2);
        p1.name = "CC";
        set.remove(p1);
        System.out.println(set);
        set.add(new Person(1001, "CC"));
        System.out.println(set);
        set.add(new Person(1001, "AA"));
        System.out.println(set);

    }
}

class Person {
    public int id;
    public String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
