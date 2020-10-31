package java8.ch02.servenTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Auther: zwy
 * @Date: 2020/7/5
 * @Description: 第七次尝试
 * @version:
 */
public class ServenTest {

    /**
    * 功能描述: 对过滤行为，行为参数化
    * @auther: zwy
    */
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> rest = new ArrayList<>();
        for (T t:list){
            if(p.test(t)){
                rest.add(t);
            }
        }
        return rest;
    }

    class PredicateImpl implements Predicate<String>{
        private String name;

        public PredicateImpl(String name){
            this.name = name;
        }

        @Override
        public boolean test(String s) {
            return name.length()==s.length();
        }
    }

    @Test
    public void test1(){
        List<String> list = new ArrayList<>();
        list.add("junode");
        list.add("demo");
        list.add("zhunode");
        list.add("haha");
        List<String> filRes = ServenTest.filter(list,new PredicateImpl("hehe"));
        for (String fil:filRes){
            System.out.println(fil);
        }

        List<String> res2 = ServenTest.filter(list,(String s)->"hehe".length() == s.length());
        System.out.println(res2.size());
    }
}
