package com.lining;

public class MaxSubtractBetweenElementAndPrevious {

    public static void main(String[] args) {
        System.out.println(maxSubtract(new int[]{5, 2, 1, 2, 6}));
    }


    /**
     * 求数组任意元素与之前元素差值的最大值
     *
     * @param array
     * @return
     */
    private static int maxSubtract(int[] array) {
        int min = array[0];
        int ans = array[1] - min;
        for (int i = 2; i < array.length; i++) {
            int distance = array[i] - min;
            if (distance > ans) {
                ans = distance;
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        return ans;
    }

}
