package com.mashibing.tank.demo;

/**
 * @Author junode
 */
public class AgeDogComparator implements ComparatorTest<Dog>{

    @Override
    public int comparator(Dog a, Dog b) {
        if(a.getAge() > b.getAge()) return 1;
        if(a.getAge() < b.getAge()) return -1;
        return 0;
    }
}
