import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static <E> void printArray(E[] inputArray){
        for(E element : inputArray){
            System.out.printf("%s", element);
        }

        System.out.println();
    }

    public static <T extends Comparable<T>> T maximum(T x, T y, T z){
        T max = x;
        if(y.compareTo(max) > 0){
            max = y;
        }
        if(z.compareTo(max) > 0){
            max = z;
        }

        return max;
    }
    public static void main(String[] args) {
        // printArray
        //Integer[] intArray = {1,2,3,4,5};
        //Double[] doubleArray = {1.1,1.2,1.3,1.4};
        //Character[] charArray = {'H', 'E','L','L','O'};
        //System.out.println("整形数组元素为：");
        //printArray(intArray);
        //System.out.println("\n双精数组元素为：");
        //printArray(doubleArray);
        //System.out.println("\n字符型组元素为：");
        //printArray(charArray);

        //System.out.printf("%d, %d, 和 %d, 中最大的数为 %d, \n\n", 3,4,5,maximum(3,4,5));
        //System.out.printf("%.1f, %.1f, 和 %.1f, 中最大的数为 %.1f, \n\n", 3.1,4.1,5.1,maximum(3.1,4.2,5.3));
        //System.out.printf("%s, %s, 和 %s, 中最大的数为 %s, \n\n", "pear","apple","orange",maximum("pear","apple","orange"));
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        Date startTime = StrToDate("2018-10-21 10:41:43");
        Date endTime = StrToDate("2018-10-21 19:42:44");
        long diff = endTime.getTime() - startTime.getTime();
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        System.out.printf(day + "天" + hour + "小时" + min + "分钟");

    }

    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
