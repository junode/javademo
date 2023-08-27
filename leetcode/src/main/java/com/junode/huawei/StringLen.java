package com.junode.huawei;

import java.util.*;

/**
 * @Author junode
 * @Date 2022/2/27
 */
public class StringLen {
    public static void main(String[] args) {
        String test = "cba";
        int len = 4;
        List<Character> listChar = new ArrayList<Character>() {{
            add('a');
            add('b');
            add('c');
            add('d');
            add('e');
            add('f');
            add('g');
            add('h');
            add('i');
            add('j');
            add('k');
            add('l');
            add('m');
            add('n');
            add('o');
            add('p');
            add('q');
            add('r');
            add('s');
            add('t');
            add('u');
            add('v');
            add('w');
            add('x');
            add('y');
            add('z');
        }};
        char[] chars = test.toCharArray();
        int searchResultLen = chars.length;
        // 限定每个字符串的限制
        Character limitChar = listChar.get(len - 1);
//        limitChar-chars[0]; // 最高位的限制
        Integer[] compareNumsIndexs = new Integer[chars.length];
        // 最高位与当前的字符串相等
        for (int j = 0; j < chars.length; j++) {
            for (int i = 0; i < listChar.size(); i++) {
                if (chars[j] == listChar.get(i)) {
                    if (j == 0) {
                        compareNumsIndexs[j] = i;
                    } else {
                        compareNumsIndexs[j] = i + 1;
                    }
                    break;
                }
            }
        }
        // searchResultLen 根据字符串长度拼接字符串了
        String prefix = chars[0] + ""; // 最高位固定
        List<String> savePossibles = new ArrayList<>();
        for (int i = 1; i < searchResultLen; i++) {
            Integer curIndex = compareNumsIndexs[i];
            Character cur = listChar.get(curIndex);
            String curKey = new String();
            while (cur <= limitChar) {
                curKey.concat(listChar.get(compareNumsIndexs[i]) + "");
                cur = listChar.get(++curIndex);
            }
            savePossibles.add(curKey);
        }
        List<String> result = new ArrayList<String>() {{
            add(prefix);
        }};
        for (int i = 0; i < savePossibles.size(); i++) {
            String s = savePossibles.get(i);
            for (Character c : s.toCharArray()) {
                for (String te : result) {
                    result.add(prefix + c);
                }
            }
        }
        /**
         *双指针遍历找到最长子串
         */
        for (String s : result) {
            for (int i = 0; i < s.length(); i++) {
                for (int j = s.length(); j > i; j--) {
                    String toBeJuged = s.substring(i, j);
                    if (!isPalindromeString(toBeJuged)) {
//                        return s;
                        System.out.println(s);
                    }
                }
            }
        }
        System.out.println("NO");

        // 最高位与当前的字符串不等
        for (int j = 0; j < chars.length; j++) {
            for (int i = 0; i < listChar.size(); i++) {
                if (chars[j] == listChar.get(i)) {
                    if (j == 0) {
                        compareNumsIndexs[j] = i + 1;
                    } else {
                        compareNumsIndexs[j] = i;
                    }
                    break;
                }
            }
        }

    }

    /**
     * 判断一个字符串是否是回文字符串的方法
     */
    static boolean isPalindromeString(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }
}
