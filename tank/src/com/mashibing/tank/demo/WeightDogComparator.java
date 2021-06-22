package com.mashibing.tank.demo;

/**
 * @Author junode
 * @Date 2021/3/28
 */
public class WeightDogComparator implements ComparatorTest<Dog>{
    @Override
    public int comparator(Dog a,Dog b) {
        if(a.getWeight() > b.getWeight()) return 1;
        else if(a.getWeight() < b.getWeight()) return -1;
        return 0;
    }
}
