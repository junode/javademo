package dataStructure.jike.day01;

/**
 * @description: 第一题：找数组中的缺失值
 * @author: hitton
 * @create: 2021-03-10 23:35
 **/
public class Question01 {

    public static void main(String[] args) {
        System.out.println("hello world");
        int[] arr = {0, 1, 2, 4 ,5,6,7,8,9,10};
        System.out.println(findLossValue(arr));
    }

    public static int findLossValue(int[] arr) {
        if (arr.length == 0 || arr.length == 1) {
            return -1;
        }
        return findLossVal(arr, 0, arr.length - 1);
    }

    private static int findLossVal(int[] arr, int start, int end) {
        int med = (end + start) / 2;
        if (end - start == 1) {
            return start + 1;
        }
        if (arr[med] > med) { // 在左边
            return findLossVal(arr, 0, med);
        } else if (arr[med] <= med) {// 在右边
            return findLossVal(arr, med, end);
        }
        return -1;
    }
}
