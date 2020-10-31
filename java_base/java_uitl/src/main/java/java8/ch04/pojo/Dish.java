package java8.ch04.pojo;

import java.lang.reflect.Type;

/**
 * @Auther: zwy
 * @Date: 2020/7/17
 * @Description: java8.ch04.pojo
 * @version:
 */
public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name,Boolean vegetarian,int calories,Type type){
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    public enum Type{MEAT,FISH,OTHER}


}
