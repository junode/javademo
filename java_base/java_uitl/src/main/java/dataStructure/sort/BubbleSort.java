package dataStructure.sort;

import java.util.Arrays;

/**
 * 每次遍历得到最大的一个数值。
 * 从0~N-1数组中找到最大值放到N-1位置
 * 从0-N-2数组中找到最大值放到N-2位置。
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] needSort = {1,4,2,3};
        bubbleSort(needSort);
        Arrays.stream(needSort).forEach(System.out::println);
    }
    public static void bubbleSort(int[] arr) {
        if(arr == null || arr.length <2) {
            return;
        }
        for(int end = arr.length - 1;end > 0; end--) {
            for (int i = 0; i < end; i++) {
                if(arr[end] < arr[i]) {
                    swap(arr,end,i);
                }
            }
        }
    }
    public static void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
