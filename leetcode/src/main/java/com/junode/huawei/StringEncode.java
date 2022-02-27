package com.junode.huawei;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * 字符串加密
 * 利用有序的LinkedHashSet集合，先把密钥添加进去，再把剩下的字符添加进去形成完整的密钥；
 * 然后利用明文，将明文的char对应的整型值作为下标，去完成密钥的数组拿对应的字符，即为对应的密文；逐一遍历明文，就得到了密文。
 *      比如，若密钥字符串为：BAH,则对应的密钥数组为：
 *          [B A H C D E F G I J K L M N O P Q R S T U V W X Y Z]
 *      若你的明文为 GH，则
 *          用 'G' - 'A' = 6，然后用6做下标去上面数组拿结果，得到F，
 *          用 'H' - 'A' = 7，然后用7做下标去上面数组拿结果，得到G，
 *      从而加密的信息为：FG。
 * 再根据要加密的字符串字符一个个取出拼接输出，ps：不要忘了大小写和空格
 * @Author junode
 * @Date 2022/2/27
 */
public class StringEncode {
    public static void main(String[] args) {
        String s1 = "AJ";
        String s2 = "HJG";
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        LinkedHashSet<Character> set = new LinkedHashSet();
        for (int i = 0; i < chars1.length; i++) {
            set.add(chars1[i]);
        }
        int k = 0;
        while (set.size() < 26) {
            char a = (char) ('A' + k);
            // System.out.println(new Character(a));
            set.add(a);
            k++;
        }
        ArrayList<Character> list = new ArrayList<>(set);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars2.length; i++) {
            if (chars2[i] == ' ') {
                sb.append(chars2[i]);
            } else if (chars2[i] < 'a') {
                int n = (int) (chars2[i] - 'A');
                char c = list.get(n);
                sb.append(c);
            } else {
                int n = (int) (chars2[i] - 'a');
                char c = (char)(list.get(n)+'a'-'A');
                sb.append(c);
            }
        }

        System.out.println(sb.toString());
    }
}
