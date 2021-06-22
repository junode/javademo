package java8.ch04;

import java8.ch04.pojo.Dish;
import java8.ch04.pojo.Trader;
import java8.ch04.pojo.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: zwy
 * @Date: 2020/7/17
 * @Description: java8.ch04
 * @version:
 */
public class demo01 {
    public List<Dish> menu;
    private List<Transaction> transactions;

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
    * 功能描述: Streams的demo实例
    * @auther: zwy
    */
    @Test
    public void test1(){
//        List<String> threeHeighCaloricDishNames = menu.stream()
//                .filter(dish->dish.getCalories()>350)
//                .map(Dish::getName)
//                .limit(3)
//                .collect(Collectors.toList());
//        System.out.println(threeHeighCaloricDishNames);
        List<String> names = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
        // 改变一下代码，便于查看执行顺序与过程
        menu.stream().filter(d->{
            System.out.println("filtering : "+ d.getName());
            return d.getCalories()>300;
        }).map(d->{
            System.out.println("mapping : " + d.getName());
            return d.getName();
        }).limit(3).collect(Collectors.toList());
    }

    /**
    * 功能描述: Streams实践操作
    * @auther: zwy
    */
    @Test
    public void test2(){
        // 1 找出2011年发生的所有交易，并按照交易金额排序(从低到高)
        List<Transaction> collect = transactions.stream().filter(d -> d.getYear() == 2011).sorted(
                Comparator.comparing(Transaction::getAmount)).collect(Collectors.toList());
        collect.forEach(System.out::println);
        // 2 交易员都在哪些不同城市工作过
        List<String> collect1 = transactions.stream().map(Transaction::getTrader)
                .map(Trader::getWorkAddr).distinct().collect(Collectors.toList());
        System.out.println(collect1);
        // 3 查找来自剑桥的交易员，并按照字母排序
        List<String> cambridge = transactions.stream().map(Transaction::getTrader).filter(d -> d.getWorkAddr().equals("Cambridge"))
                .map(Trader::getName).distinct().sorted(Comparator.comparing(String::toLowerCase))
                .collect(Collectors.toList());
        System.out.println(cambridge);

        // 4 查找所有交易员的姓名字符串，按字母顺序排列
        String reduce = transactions.stream().map(Transaction::getTrader).map(Trader::getName).distinct().sorted()
                .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(reduce);

        // 5 有没有交易员是在米兰工作的？
        boolean milan = transactions.stream().anyMatch(d -> d.getTrader().getWorkAddr().equals("Milan"));
        System.out.println(milan);

        // 6 打印生活在剑桥的交易员的所有交易额
        transactions.stream().filter(d->"Cambridge".equals(d.getTrader().getWorkAddr()))
                .map(Transaction::getAmount).forEach(System.out::println);

        // 7 所有交易中，最高的交易额是多少？
        Optional<Integer> reduce1 = transactions.stream().map(Transaction::getAmount).reduce(Integer::max);
        System.out.println(reduce1.get());

        // 7 找到交易额最小的交易。
        Optional<Transaction> reduce2 = transactions.stream().reduce((a, b) -> a.getAmount() < b.getAmount() ? a : b);
        System.out.println(reduce2.get());

    }

    @Test
    public void test3() throws IOException {
//        System.out.println(this.getClass().getResource("/demo.txt"));
//        InputStream resourceAsStream = this.getClass().getResourceAsStream("/demo.txt");
//        byte[] by = new byte[1024];
//        InputStream bis = new BufferedInputStream(resourceAsStream);
//        int byteread = 0;
//        while((byteread = bis.read(by))!=-1){
//            // 将读取的byte转为字符串
//            String str = new String(by,0,byteread);
//            System.out.println(str);
//        }


    }
}
