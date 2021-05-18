package com.lining;

/**
 * 折叠数组
 * scm.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
public class 旋转数组 {

    public static void main(String[] args) {
        //旋转数组
        int[] arr1 = new int[]{4, 5, 6, 7, 8, 9, 0, 1, 2, 3};
        System.out.println(min1(arr1, 0, arr1.length - 1));

        //折叠数组
        int[] arr2 = new int[]{9, 8, 7, 6, 5, 0, 1, 2, 3, 4};
        System.out.println(min2(arr2, 0, arr2.length - 1));
    }


    private static int min1(int[] arr, int low, int high) {
        if (low == high) {
            return arr[low];
        }
        int mid = (low + high) / 2;
        if (arr[mid] > arr[high]) {
            return min1(arr, mid + 1, high);
        } else {
            return min1(arr, low, mid);
        }
    }

    private static int min2(int[] arr, int low, int high) {
        if (low == high) {
            return arr[low];
        }
        int mid = (low + high) / 2;
        if (arr[mid] > arr[high]) {
            return min2(arr, mid + 1, high);
        } else {
            return min2(arr, low, mid);
        }

    }
}
