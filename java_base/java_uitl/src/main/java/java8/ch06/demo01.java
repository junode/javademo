package java8.ch06;

import java8.ch04.pojo.Dish;
import java8.ch04.pojo.Trader;
import java8.ch04.pojo.Transaction;
import org.junit.Before;
import org.junit.Test;

import javax.swing.text.html.Option;
import java.util.*;

import static java.util.stream.Collectors.*;

/**
 * @Auther: zwy
 * @Date: 2020/7/21
 * @Description: java8.ch06
 * @version:
 */
public class demo01 {

    public List<Dish> menu;
    private List<Transaction> transactions;

    /**
    * 功能描述: 该before直接复制于ch04包下的demo01.java文件
    * @auther: zwy
    */
    @Before
    public void be(){
        menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    /**
    * 功能描述: 查找流中的最大值和最小值
    * @auther: zwy
    */
    @Test
    public void test1(){
        Comparator<Dish> disCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        Double averageCalories = menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println(averageCalories);
        IntSummaryStatistics summarys = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(summarys);
        System.out.println(summarys.getMax());

        Map<Boolean, Map<Dish.Type, List<Dish>>> collect = menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        System.out.println(collect);

        Map<Dish.Type, List<Dish>> collect1 = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(collect1);

        Map<Dish.Type,Long> typesCount = menu.stream().collect(groupingBy(Dish::getType,counting()));
        System.out.println(typesCount);



    }




































}
