package com.mashibing.tank.demo;

/**
 * 排序器 插入排序
 * @Author junode
 * @Date 2021/3/29
 */
public class Sorter<T> {

    public void sort(T[] arr,ComparatorTest<T> comparator) {
        for (int i = arr.length - 1; i >= 0 ; i--) {
            int maxIndex = i;
            for (int j = 0;j < i; j ++) {
                maxIndex = comparator.comparator(arr[maxIndex],arr[j]) == 1 ? maxIndex : j;
            }
            swap(arr,maxIndex,i);
        }
    }

    private void swap(T[] arr, int maxIndex, int i) {
        T temp = arr[i];
        arr[i] = arr[maxIndex];
        arr[maxIndex] = temp;
    }
}
