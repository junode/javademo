package com.demo.cocurrent.wangwenjun.char03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: zwy
 * @Date: 2020/10/28
 * @Description: com.demo.cocurrent.wangwenjun.char03
 * @version:
 */
public class FightQueryExample {

    // 合作的各大航空公司
    private static List<String> fightCompany = Arrays.asList("CSA","CEA","HNA");

    public static void main(String[] args) {
        List<String> search = search("BJ", "SH");
        System.out.println("============= search result ===========");
        search.forEach(System.out::println);
    }

    // 查询航班列表
    private static List<String> search(String original, String dest){
        final List<String> result = new ArrayList<>();
        // 创建航班查询列表
        List<FightQueryTask> tasks = fightCompany.stream().map(f -> createSearchTask(f, original, dest)).collect(Collectors.toList());
        // 分别启动这几个线程
        tasks.forEach(Thread::start);
        // 分别调用每个线程的join方法，阻塞当前线程
        tasks.forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 在此之前，当前线程会阻塞住，获取每一个查询线程的结果，并且加入到result中。
        tasks.stream().map(FightQuery::get).forEach(result::addAll);
        return result;
    }

    private static FightQueryTask createSearchTask(String fight,String original,String destin){
        return new FightQueryTask(fight,original,destin);
    }


}
