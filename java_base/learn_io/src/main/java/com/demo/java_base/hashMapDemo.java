package com.demo.java_base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class hashMapDemo {

    public static void main(String[] args) {
        test1();
    }

    // map的遍历
    public static void test1() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "demo");
        map.put("2", "value");

        System.out.println(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry);
        }

        for (String key : map.keySet()) {
            System.out.println(map.get(key));
        }

        // 利用Iterator进行遍历
        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        map.remove("1");
    }

    public static void test2() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("123", "22");
        System.out.println("123".hashCode());
    }
}
