package com.junode.huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 扑克牌大小
 * 题目里的单排应该就是一张牌，这种的话只用比较长度和最低位，比较简单直接。我一开始想的单排是257这种，这种很麻烦。 anyway，根据题目的意思。
 *
 *      是王炸，直接输出。不是则比较两副牌的数量。
 *      如果都为4，则是两幅炸，输出较大的一个。
 *      如果只有其中一个是4，则输出这个。
 *      如果都不是炸，则直接比较最小位。这个没法理解
 *      无法比较，ERROR
 */
public class PuKe {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        String[] cards = input.trim().split("-");
        String output = max(cards[0], cards[1]);
        System.out.println(output);
    }

    public static String max(String cards1, String cards2){
        Map<String,Integer> map = new HashMap<String, Integer>(){
            {
                put("3",3);
                put("4",4);
                put("5",5);
                put("6",6);
                put("7",7);
                put("8",8);
                put("9",9);
                put("10",10);
                put("J",11);
                put("Q",12);
                put("K",13);
                put("A",14);
                put("2",15);
                put("joker",16);
                put("JOKER",17);
            }
        };
        String[] c1 = cards1.split(" ");
        String[] c2 = cards2.split(" ");
        //王炸
        if(cards1.equals("joker JOKER")){
            return cards1;
        }
        else if(cards2.equals("joker JOKER")){
            return cards2;
        }
        //这里不一定是王炸，也可能是最后的其他情况。四个都是个子。
        else if(c1.length == c2.length && c1.length == 4){
            return map.get(c1[0]) > map.get(c2[0]) ? cards1 : cards2;
        }
        //有一个是炸弹
        else if(c1.length == 4){
            return cards1;
        }
        else if(c2.length == 4){
            return cards2;
        }
        //其他的
        else if(c1.length == c2.length){
            return map.get(c1[0]) > map.get(c2[0]) ? cards1 : cards2;
        }
        //无法比较
        else {
            return "ERROR";
        }
    }
}
