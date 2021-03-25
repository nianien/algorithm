package com.lining;

/**
 * 题目：
 * 有两个数组A和B，假设A和B已经有序（从大到小），求A和B数组中所有数的第K大。
 * <p>
 * 思路：
 * 1、如果k为2的次幂，且A,B 的大小都大于k，那么
 * <p>
 * 考虑A的前k/2个数和B的前k/2个数，
 * <p>
 * 如果A[k/2]<B[k/2]，说明A的前k/2个数一定在A和B总的前k个数中，因此只需要在A的k/2之后的数和B中查找第k/2大的数；
 * <p>
 * 否则，说明A的前k/2个数一定在A和B总的前k个数中，因此只需要在B的k/2之后的数和A中查找第k/2大的数；
 * <p>
 * 递归实现即可；
 * <p>
 * 2、如果A+B的数组大小大于k
 * <p>
 * 二分法，考虑A的前一半m/2和B的前一半n/2，
 * <p>
 * 假设A[mid]<B[mid]：
 * <p>
 * 如果m/2+n/2大于k，则表明第k大存在于A和B的前一半中；否则，只需在A的m/2之后的数和B中找第k-m/2大的数；
 * <p>
 * 假设A[mid]>B[mid]：
 * <p>
 * 如果m/2+n/2大于k，则表明k存在于A和B的前一半中；否则，只需在B的n/2之后的数和A中找第k-n/2大的数；
 * <p>
 * 递归实现即可；
 * scm.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
public class 两数组中位数 {



    public static void main(String[] args) {
        int[] b = {2, 3, 5, 6};
        int[] a = {4, 5, 6, 7, 8, 9, 10};
        int total = a.length + b.length;
        int mid = total / 2;
        if (total % 2 == 0) {
            int topk1 = findTopK(a, b, 0, 0, mid);
            int topk2 = findTopK(a, b, 0, 0, mid + 1);
            System.out.println("==>1:" + (topk1 + topk2) / 2.0);
        } else {
            System.out.println("==>2:" + findTopK(a, b, 0, 0, (total + 1) / 2));
        }
        System.out.println(findMedianSortedArrays(a, b));

        int k = 11;
        System.out.println(findTopK(a, b, 0, 0, k));
    }


    /**
     * 因为递归过程是排除K之前的元素,因此需要递归修正起始索引位置
     *
     * @param arr1   第一个数组
     * @param arr2   第二个数组
     * @param start1 第一个数组起始位置
     * @param start2 第二个数组起始位置
     * @param k
     * @return
     */
    private static int findTopK(int[] arr1, int[] arr2, int start1, int start2, int k) {
        if (start1 == arr1.length) {
            return arr2[start2 + k - 1];
        }
        if (start2 == arr2.length) {
            return arr1[start1 + k - 1];
        }
        if (k == 1) {
            return Math.min(arr1[start1], arr2[start2]);
        }
        //查找剩余的k/2个元素, 如果不足k/2,则查找剩余全部元素
        int mid1 = Math.min(arr1.length - start1, k / 2);
        int mid2 = Math.min(arr2.length - start2, k / 2);
        //判定两个数组第mid个元素的大小,对于mid较小的数组,前mid个元素一定属于top(k/2),因此只需在剩余元素中查找top(k-mid)即可
        if (arr1[start1 + mid1 - 1] < arr2[start2 + mid2 - 1]) {
            return findTopK(arr1, arr2, start1 + mid1, start2, k - mid1);
        } else {
            return findTopK(arr1, arr2, start1, start2 + mid2, k - mid2);
        }
    }

    public static double findMedianSortedArrays(int[] a, int[] b) {
        if (a.length > b.length) {
            return findMedianSortedArrays(b, a);
        }
        int m = a.length;
        int n = b.length;
        int left = 0, right = m;
        // median1：前一部分的最大值
        // median2：后一部分的最小值
        int median1 = 0, median2 = 0;

        while (left <= right) {
            // 前一部分包含 a[0 .. i-1] 和 b[0 .. j-1]
            // 后一部分包含 a[i .. m-1] 和 b[j .. n-1]
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;
            // a_left_max, a_right_min, b_left_max, b_right_min 分别表示 a[i-1], a[i], b[j-1], b[j]
            int a_left_max = (i == 0 ? Integer.MIN_VALUE : a[i - 1]);
            int a_right_min = (i == m ? Integer.MAX_VALUE : a[i]);
            int b_left_max = (j == 0 ? Integer.MIN_VALUE : b[j - 1]);
            int b_right_min = (j == n ? Integer.MAX_VALUE : b[j]);

            if (a_left_max <= b_right_min) {
                median1 = Math.max(a_left_max, b_left_max);
                median2 = Math.min(a_right_min, b_right_min);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }
        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }
}
