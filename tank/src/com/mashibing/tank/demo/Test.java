package com.mashibing.tank.demo;

/**
 * @Author junode
 * @Date 2021/3/29
 */
public class Test {
    public static void main(String[] args) {
        Dog[] dogs = {new Dog(3,30),new Dog(5,100),new Dog(2,20),new Dog(1,40)};
        Sorter<Dog> sorter = new Sorter<>();
        sorter.sort(dogs,new WeightDogComparator());
        for(Dog dog : dogs) {
            System.out.println(dog);
        }
        System.out.println("===== split ====");
        sorter.sort(dogs,new AgeDogComparator());
        for(Dog dog : dogs) {
            System.out.println(dog);
        }
    }
}
