package com.lining.spiralmatrix;


/**
 * 螺旋矩阵
 *
 * @author NiaNien
 */
public class SpiralMatrix2 {


    public static void clockwise(int top, int left, int bottom, int right, int k, int[][] arr) {
        if (top > bottom || left > right) {
            return;
        }
        for (int j = left; j <= right; j++) {
            arr[top][j] = k++;
        }
        for (int i = top + 1; i <= bottom; i++) {
            arr[i][right] = k++;
        }
        for (int j = right - 1; j >= left && top < bottom; j--) {
            arr[bottom][j] = k++;
        }
        for (int i = bottom - 1; i > top && left < right; i--) {
            arr[i][left] = k++;
        }
        clockwise(top + 1, left + 1, bottom - 1, right - 1, k, arr);
    }

    public static void clockwise0(int top, int left, int bottom, int right, int k, int[][] arr) {
        while (top <= bottom && left <= right) {
            int t = top, b = bottom, l = left, r = right;
            // 向右
            for (int j = l; j <= r; j++) {
                arr[t][j] = k++;
            }
            // 向下
            for (int i = t + 1; i <= b; i++) {
                arr[i][r] = k++;
            }
            // 向左（只有至少两行才会进）
            for (int j = r - 1; j >= l && t < b; j--) {
                arr[b][j] = k++;
            }
            // 向上（只有至少两列才会进）
            for (int i = b - 1; i > t && l < r; i--) {
                arr[i][l] = k++;
            }
            // 进入下一圈
            top++;
            left++;
            bottom--;
            right--;
        }
    }

    public static void main(String[] args) {
        int m = 3, n = 5;
        int[][] arr = new int[m][n];
        clockwise(0, 0, m - 1, n - 1, 1, arr);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();

        }
    }

}
