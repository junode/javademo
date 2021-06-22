package dataStructure.sort;

import java.util.Arrays;

/**
 * 每次选择一个最小的数值放在开头
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] needSort = {1, 4, 2, 3};
        selectSort(needSort);
        Arrays.stream(needSort).forEach(System.out::println);
    }

    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
