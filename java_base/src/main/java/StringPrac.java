/**
 * String示例
 *
 * javap -c -verbose StringPrac.class
 */
public class StringPrac {

    public static void main(String[] args) {
        String heapStr = new String("heapStr");
        String heapStr2 = new String("heapStr");
        /**
         * 在一些文章当中，会看到说常量池中存入的是对象的地址，通过下面证明，如果存入的是地址，则下面应是false的，但下面正确，表明存入的是字符串（
         * （heapStr.intern()调用时，发觉常量池没有，则将字符串创建，并返回地址；heapStr2.intern()调用时，发觉常量池已有该字符串，则直接返回该地址，
         * 从而他们相等）。
         */
        System.out.println(heapStr.intern() == heapStr2.intern()); // 这个就表明他们存入的字符串，而非各自的字符串地址。
        String stringLiteral = "heapStr";
        String constantStr = heapStr.intern();
        System.out.println(heapStr == constantStr);
        System.out.println(stringLiteral == heapStr);
        System.out.println(stringLiteral == constantStr);
        System.out.println(String.class.getName()+"@"+Integer.toHexString(System.identityHashCode(heapStr)));
        System.out.println(String.class.getName()+"@"+Integer.toHexString(System.identityHashCode(heapStr2)));
        System.out.println(String.class.getName()+"@"+Integer.toHexString(System.identityHashCode(constantStr)));
        System.out.println(String.class.getName()+"@"+Integer.toHexString(System.identityHashCode(stringLiteral)));


//        System.out.println(" =========test1 start======= ");
        String a = "abc";
        String b = "def";
        String c = a+b;
        String d = "abcdef";
        System.out.println(c == d);
        System.out.println(c.equals(d));
//        System.out.println(" =========test1 end======= ");

//        System.out.println(" =========test2 start======= ");
//        new StringPrac().test2();
//        System.out.println(" =========test2 end======= ");
//
//        System.out.println(" ======test3 start========== ");
//        new StringPrac().test3();
//        System.out.println(" ======test3 end========== ");
    }


//    public void test2() {
//        final String a = "abc";
//        final String b = "def";
//        String c = a+b;
//        String d = "abcdef";
//        System.out.println("c == d = " + (c == d));
//        System.out.println("c.equals(d) = " + c.equals(d));
//    }
//
//    public void test3() {
//        String str = "pingtouge";
//        String str1 = new String("pingtouge");
//        System.out.println(str==str1);
//    }
}
