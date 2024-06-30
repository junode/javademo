package com.demo;

import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author junode
 * @version 1.0.0
 * @Description
 * @createTime 2024年05月27日 19:39:00
 */
public class StrToPb {

    /**
     * key为字符串，value为替换后的字符串
     */
    private Map<String, String> map = new ConcurrentHashMap<>();
    /**
     * key为字符串，value为timestamp
     */
    private Map<String, Long> timeCountMap = new ConcurrentHashMap<>();

    /**
     * 超时时间统计
     */
    private Long timeOutCount = 10_000l;
    /**
     * 1、写入缓存，并带有超时
     * 2、当超时
     * @param str
     * @return
     */
    public String strToPb(String str) {
        // 当前时间
        long l = System.currentTimeMillis();
        Long aLong = timeCountMap.get(str);
        if(aLong != null && aLong + timeOutCount > l) {
            System.out.println(" get from cache？？？ " );
            // 没有超时
            return map.get(str);
        } else {
            String result = replace(str, "");
            map.put(str, result);
            timeCountMap.put(str, l);
            return result;
        }
    }

    private String replace(String str, String subStr) {
        return str;
    }

    @Test
    public void test(){
        String abc = "abc";
        String s = strToPb(abc);
        System.out.println("result = " + s);
        wait(1000);
        String s1 = strToPb(abc);
        System.out.println("s1 = " + s1);
        wait(10_000);
        String dealAgain = strToPb(abc);
        System.out.println("s1 = " + dealAgain);
    }

    private void wait(int timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
