package com.demo.java_base;

import java.util.Date;

public class DateDemo01 {

    public static void main(String[] args) {
        testDate();
    }

    public static void testDate() {
        Date date = new Date();
        System.out.println(date);
        System.out.println(System.currentTimeMillis());
        System.out.println(date.getTime());
        Date date1 = new Date(date.getTime());
        System.out.println(date1.getTime());
        System.out.println(date1.toString());
    }


}
