package com.demo.cocurrent.wangwenjun.char02;

import org.junit.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Auther: zwy
 * @Date: 2020/10/26
 * @Description: 2.1 线程的命名
 * @version:
 */
public class demo01 {


    /**
    * 功能描述: 线程的默认命名
    * @auther: zwy
    */
    @Test
    public void test(){
        IntStream.range(0,5).boxed().map(
                i->new Thread(()-> System.out.println(Thread.currentThread().getName()))
        ).forEach(Thread::start);
    }

}
