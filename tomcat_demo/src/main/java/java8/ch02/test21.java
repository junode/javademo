package java8.ch02;

import java8.ch01.Apple;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: zwy
 * @Date: 2020/7/5
 * @Description: 第二章测验2.1 编写灵活的prettyPrintTypeApple方法
 * @version:
 */
public class test21 {

    public static void printApple(List<Apple> ventory, ApplePrintType printType) {
        for (Apple apple : ventory) {
            String output = printType.print(apple);
            System.out.println(output);
        }
    }

    public List<Apple> genList(){
        List<Apple> list = new ArrayList<>();
        Apple apple1 = new Apple("1号", 150, "blue");
        list.add(apple1);
        Apple apple2 = new Apple("2号", 160, "red");
        list.add(apple2);
        Apple apple3 = new Apple("3号", 180, "yellow");
        list.add(apple3);
        Apple apple4 = new Apple("4号", 130, "green");
        list.add(apple4);
        return list;
    }

    /**
     * 功能描述: 打印苹果重量
     *
     * @auther: zwy
     */
    @Test
    public void test() {

        List<Apple> list = genList();

        printApple(list, new AppleWeightPrint());
        printApple(list, new AppleColorPrint());

        // 直接编写匿名内部类避免声明几个实体类的繁琐问题。
        printApple(list, new ApplePrintType() {
            @Override
            public String print(Apple apple) {
                return "当前苹果为：" + apple.getName() + " , 苹果颜色为：" + apple.getColor();
            }
        });
        // 使用lambda表达式来改写匿名内部类代码不简介问题。;
        printApple(list, (Apple apple) -> "当前苹果为 " + apple.getName() + " , 颜色为" + apple.getColor());
    }

    /**
     * 功能描述: 2.4 真实的例子
     *
     * @auther: zwy
     */
    @Test
    public void testd() {
        // 使用 Comparator 来排序
        List<Apple> list = genList();
        // 使用匿表达式实现排序
        list.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o2.getWeight().compareTo(o1.getWeight());
            }
        });
        for (Apple apple:list){
            System.out.println(apple.toString());
        }
        list = genList();
        System.out.println("================");
        // lambda表达式实现排序
        list.sort((Apple a1,Apple a2)-> a1.getWeight().compareTo(a2.getWeight()));
        for (Apple apple:list){
            System.out.println(apple.toString());
        }
    }

    /**
    * 功能描述: Runnable执行代码块
    * @auther: zwy
    */
    @Test
    public void testd2(){
        // 使用Runnable实现要执行的代码块
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("test thread to run");
            }
        });
        t.start();

        Thread t3 = new Thread(()-> runOther());
        t3.start();

        // 使用lambda来表示run()接口代码，当然代码复杂的化，还是使用匿名内部类把。
        Thread t2 = new Thread(()-> System.out.println("test thread by lambda express"));
        t2.start();


    }

    private void runOther() {
        // 这段代码放在这里导致print未输出，主要是因为当前线程t3是被test线程调起的，
        // 若是test线程执行结束而当前线程还未执行，那么会导致t3线程失效？
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello world");
    }
}
