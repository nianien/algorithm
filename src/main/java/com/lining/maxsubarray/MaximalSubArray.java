package com.lining.maxsubarray;

import java.util.Arrays;

/**
 * <pre>
 *     给定一个整数数组，求子数组序列使元素的和最大
 * </pre>
 */
public class MaximalSubArray {

    /**
     * 求最短的最大子数组
     *
     * @param array
     */
    public int[] max(int[] array) {
        long maxSum = 0, thisSum = 0;
        //最大子数组的起止索引位置
        int from = 0, to = 0;
        //当前子数组的起始索引位置
        int begin = 0;
        for (int i = 0; i < array.length; i++) {
            thisSum += array[i];
            if (thisSum <= 0) {
                thisSum = 0;
                begin = i + 1;
            }
            //若唯一，求最大子数组，否则求最短的最大子数组
            if (thisSum > maxSum || (thisSum == maxSum && to - from > i - begin)) {
                maxSum = thisSum;
                from = begin;
                to = i;
            }
        }
        return handle(array, from, to, maxSum);
    }

    private int[] handle(int[] array, int from, int to, long sum) {
        int[] subArray = new int[to - from + 1];
        for (int i = from; i <= to; i++) {
            subArray[i - from] = array[i];
        }
        System.out.println("[" + from + ":" + to + "|sum=" + sum + "]" + Arrays.toString(subArray));
        return subArray;
    }

}
