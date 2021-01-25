package com.demo.java_base;


public class Str01 {
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();

        String res = "query";
        int start = 1;
        int end = 3;
//        reverseStr(res,start,end);

        String parsentStr = "abkkcadkabkebfkabkskab";
        String parsentStr2 = "kkcadkabkebfkabksk";
        String parsentStr3 = "abkkcadkabkebfkabksk";

        String subStr = "ab";
//        count(subStr,parsentStr3);

        String smallOne = "hell";
        String bigOne = "hehelhelleeel";
//        getBigSubStr(smallOne, bigOne);

//        stringAndStringBufferAndStringBuilder();

        testBuf();

    }

    // 构造方法
    public static void test1() {
        String s1 = "atguigu";
        /**
         * s2和s3是直接在字符串常量池中的。
         * 从而他们引用的是同一个内存地址
         */
        String s2 = "java";
        String s3 = "java";
        // 以new的方式创建的对象，是放在堆空间
        // 以new方式会开辟新内存空间，用于存放数据
        String s4 = new String("java");
        String s5 = new String("java");
        // s2和s3相等，表明
        System.out.println(s2 == s3);
        System.out.println(s3 == s4);
        System.out.println(s2.equals(s4));
        System.out.println(s4 == s5);
        // String重写了equals方法，比较的是数值是否相等，不是内存地址。
        // == 比较的是内存地址。
        System.out.println(s4.equals(s5));
    }

    // intern()方法
    public static void test2() {
        String s1 = "atguigu";
        String s2 = "java";
        String s3 = new String("java");
        String s4 = "atguigujava";
        String s5 = (s1 + s2).intern();
        // 相等，说明intern方法将s5放入到了常量池中，然后比较他们是否相等。
        System.out.println(s4 == s5); // true
        String s6 = s1 + s3;
        String s7 = (s1 + s3).intern();
        System.out.println(s6 == s4); // fasle
        System.out.println(s7 == s4); // true
    }

    /**
     *
     */
    public static void test3() {
        String s1 = "javaEE";
        s1 = "javaEEAndroid";

        StringBuffer sb = new StringBuffer("javaEE");
        sb.append("android");
        System.out.println(sb);
    }

    // 指定部分的字符串反转
    public static void reverseStr(String str, int start, int end) {
        if (str == null && str.length() <= 1) {
            return;
        }
        int len = str.length();
        if (len < end) {
            System.out.println("越界了");
            return;
        } else if (start < 0) {
            System.out.println("开始位置必须大于0");
        }
        String starStr = str.substring(0, start);
        String endStr = str.substring(end, len);
        char[] median = str.substring(start, end).toCharArray();
        for (int i = median.length - 1; i >= 0; i--) {
            starStr += median[i];
        }
        System.out.println(str);
        str = starStr + endStr;
        System.out.println(str);
    }

    //实现字符串指定“区间”的字符之间的反序。且指定的start和end都是包含在此区间的。
    public String reverseString(String str, int start, int end) {
        char[] c = str.toCharArray();
        return reverseChar(c, start, end);
    }

    //实现了字符数组中指定区间字符间的反序。且指定的start和end都是包含在此区间的。
    public String reverseChar(char[] c, int start, int end) {
        for (int x = start, y = end; x < y; x++, y--) {
            swap(c, x, y);
        }
        return new String(c);
    }

    //实现指定字符数组中两个元素的交换
    public void swap(char[] c, int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }


    // 获取一个字符串在另一个字符串中出现的次数
    public static void count(String subStr, String parentStr) {
        int count = parentStr.split(subStr).length - 1;
        for (String str : parentStr.split(subStr)) {
            System.out.println(str);
        }
        // 判断时候在开头有
        if (parentStr.startsWith("ab")) {
            // 可以不用理会，因为开头的会以空表示
        }
        // 判断时候再结尾有
        if (parentStr.endsWith("ab")) {
            count += 1;
        }

        System.out.println(count);
    }

    // 获取两个字符串中最大相同的子串
    public static void getBigSubStr(String str1, String str2) {
        String bigOne = str1.length() >= str2.length() ? str1 : str2;
        String smallOne = str1.length() < str2.length() ? str1 : str2;
        for (int i = smallOne.length(); i >= 0; i--) {
            if (bigOne.contains(smallOne.substring(0, i))) {
                System.out.println(smallOne.substring(0, i));
                return;
            }
        }
        System.out.println(str1 + " and " + str2 + " has no same substring");
    }

    // 测试String、StringBuidler、StringBuffer的效率
    public static void stringAndStringBufferAndStringBuilder() {
        String text = "";
        long startTime = 0L;
        long endTime = 0L;
        StringBuffer buffer = new StringBuffer("");
        StringBuilder builder = new StringBuilder("");
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            buffer.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer的执行时间：" + (endTime - startTime));
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            builder.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder的执行时间：" + (endTime - startTime));
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            text = text + i;
        }
        endTime = System.currentTimeMillis();
        System.out.println("String的执行时间：" + (endTime - startTime));
    }

    public static void testBuf() {
        String str = null;
        StringBuffer sb = new StringBuffer();
        sb.append(str);

        System.out.println(sb.length());
        System.out.println(sb);

        StringBuffer sb1 = new StringBuffer(str);
        System.out.println(sb1);
    }
}
