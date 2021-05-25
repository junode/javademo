package com.mashibing.tank.demo;

/**
 * @Author junode
 * @Date 2021/3/28
 */
public class Dog {
    private int age;
    private int weight;

    public Dog(int age, int weight) {
        this.age = age;
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "age=" + age +
                ", weight=" + weight +
                '}';
    }
}
