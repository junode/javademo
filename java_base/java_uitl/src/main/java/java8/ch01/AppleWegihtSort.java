package java8.ch01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Auther: zwy
 * @Date: 2020/7/5
 * @Description: 根据重量为苹果排序
 * @version:
 */
public class AppleWegihtSort {

    /**
    * 功能描述: 筛选出绿色苹果
    * @auther: zwy
    */
    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> res = new ArrayList<>();
        for (Apple apple : inventory){
            if("green".equals(apple.getColor())){
                res.add(apple);
            }
        }
        return res;
    }

    /**
    * 功能描述: 根据颜色筛选出苹果
    * @auther: zwy
    */
    public static List<Apple> filterApplesByColor(List<Apple> inventory,String color){
        List<Apple> res = new ArrayList<>();
        for (Apple apple : inventory){
            if(color.equals(apple.getColor())){
                res.add(apple);
            }
        }
        return res;
    }

    /**
    * 功能描述: 对苹果判断标准建模，建立接口进行过滤
    * @auther: zwy
    */
    public static List<Apple> filterApples(List<Apple> ventory,ApplePredicate p){
        List<Apple> res = new ArrayList<>();
        for (Apple apple : ventory){
            if(p.test(apple)){ // 谓词对象封装了测试苹果的条件
                res.add(apple);
            }
        }
        return res;
    }

    class AppleComparator implements Comparator<Apple>{

        @Override
        public int compare(Apple o1, Apple o2) {
            return o1.getWeight().compareTo(o2.getWeight());
        }
    }

    /**
    * 功能描述: 颜色为红与重量大于150的苹果 测试
    * @auther: zwy
    */
    @Test
    public void test(){
        List<Apple> lis = new ArrayList<>();
        Apple apple1 = new Apple("1号",150,"blue");
        lis.add(apple1);
        Apple apple2 = new Apple("2号",160,"red");
        lis.add(apple2);
        Apple apple3 = new Apple("3号",180,"yellow");
        lis.add(apple3);
        Apple apple4 = new Apple("4号",130,"green");
        lis.add(apple4);

        // filterApples方法的行为参数化了。
        List<Apple> redAndHeavyApples = filterApples(lis,new AppleRedAndHeaveyPredicate());
        for (Apple apple : redAndHeavyApples){
            System.out.println(apple.toString());
        }

        lis.sort(new AppleComparator());

        // 匿名内部类实现按苹果重量排序
        lis.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        // 使用lambda表达式
        lis.sort((Apple o1,Apple o2)->o1.getWeight().compareTo(o2.getWeight()));

        // 使用方法引用
        lis.sort(Comparator.comparing(Apple::getWeight));
    }


}
