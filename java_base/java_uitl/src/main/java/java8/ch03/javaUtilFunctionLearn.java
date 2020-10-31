package java8.ch03;

import java8.ch01.Apple;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Auther: zwy
 * @Date: 2020/7/8
 * @Description: 该类是java.util.function包的示例
 * 参考自《java8 in action》及https://www.cnblogs.com/linzhanfly/p/9686941.html
 */
public class javaUtilFunctionLearn {

    /**
    * 功能描述: java.util.function.Consumer使用实例
     * Consumer 消费者 当然是将输入的东西消费了（具体业务自己实现），并不返回任何东西。
     * 这里就将输入的东西进行遍历
    * @auther: zwy
    */
    public static <T> void forEach(List<T> list, Consumer<T> consumer){
        for (T t : list){
            consumer.accept(t);// 对于consumer的具体行为，需要自己来实现
        }
    }

    /**
    * 功能描述: 也可以通过实现Consumer类，重写accpet方法来实现行为参数化
    * @auther: zwy
    */
    class ConsumerImpl01<String> implements Consumer<String>{
        @Override
        public void accept(String t) {
            if (t.equals("haha")){
                System.out.println("welcome to China");
            }else if(t.equals("111")){
                System.out.println("does you loss your way???");
            }else{
                System.out.println("get out!!!");
            }
        }
    }

    class ConsumerImpl02<Integer> implements Consumer<Integer>{

        @Override
        public void accept(Integer integer) {
            System.out.println("you have to go, number "+integer.toString());
        }
    }

    @Test
    public void test1(){
        forEach(Arrays.asList(1,2,3,4,5),(Integer i) -> System.out.println(i));
        forEach(Arrays.asList("hah","haha","111"),new ConsumerImpl01());
        forEach(Arrays.asList(1,2,3),new ConsumerImpl02<>());
    }

    /**
    * 功能描述: 关于lambda方法引用的使用步骤
    * @auther: zwy
    */
    @Test
    public void test2(){
        List<String> str = Arrays.asList("a","b","B","A");
        str.sort((s1,s2)->s1.compareToIgnoreCase(s2));
        System.out.println(str);

        // 该上面写法改成方法引用的写法
        List<String> str2 = Arrays.asList("a","b","A","B");
        str2.sort(String::compareToIgnoreCase);
        System.out.println(str2);
    }

    public class AppleComparator implements Comparator<Apple>{

        @Override
        public int compare(Apple o1, Apple o2) {
            return o1.getWeight().compareTo(o2.getWeight());
        }
    }

    @Test
    public void test3(){

    }
}
