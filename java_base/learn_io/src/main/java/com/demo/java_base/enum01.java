package com.demo.java_base;

enum SeasonEnum {
    SPRING, SUMMER, AUTUMN, WINTER
}

public class enum01 {
    public static void main(String[] args) {
        SeasonEnum[] seasonArr = SeasonEnum.values();
        SeasonEnum season = seasonArr[3];
        System.out.println(season);
        season = SeasonEnum.valueOf("AUTUMN");
        System.out.println(season);
        season = SeasonEnum.SUMMER;
        System.out.println(season);
    }
}
