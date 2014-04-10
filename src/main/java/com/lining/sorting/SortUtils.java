package com.lining.sorting;

/**
 * Created with IntelliJ IDEA.
 * User: skyfalling
 * Date: 12-10-23
 * Time: 上午9:42
 * To change this template use File | Settings | File Templates.
 */
public class SortUtils {
    /**
     * 将指定区间的数据分割成两部分，左边的元素小于枢纽元素，右边的元素大于枢纽元素
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    private static <T extends Comparable<T>> int partition(T[] arr, int low, int high) {
        T pivot = arr[low];
        while (low < high) {
            while (low < high && arr[high].compareTo(pivot) >= 0) --high;
            arr[low] = arr[high];
            while (low < high && arr[low].compareTo(pivot) <= 0) ++low;
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }

    /**
     * 快排指定区间的元素
     *
     * @param arr
     * @param low
     * @param high
     * @param <T>
     */
    private static <T extends Comparable<T>> void quick(T[] arr, int low, int high) {
        if (low < high) {
            int n = partition(arr, low, high);
            quick(arr, low, n - 1);
            quick(arr, n + 1, high);
        }
    }

    /**
     * 快速排序数据
     *
     * @param arr
     * @param <T>
     */
    public static <T extends Comparable<T>> void quickSort(T[] arr) {
        quick(arr, 0, arr.length - 1);
    }
}
