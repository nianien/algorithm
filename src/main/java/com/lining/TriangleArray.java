package com.lining;

/**
 * scm.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
public class TriangleArray {
    public static void main(String[] args) {
        int[][] up = {
                {3, 9, 1, 4, 7},
                {0, 5, 2, 5, 8},
                {0, 0, 5, 2, 4},
                {0, 0, 0, 1, 7},
                {0, 0, 0, 0, 9}
        };
        int[] arr = new int[up.length * (up.length + 1) / 2];
        for (int i = 0; i < 5; i++) {
            for (int j = i; j < 5; j++) {
                arr[j * (j + 1) / 2 + i] = up[i][j];
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = i; j < 5; j++) {
                System.out.print(arr[j * (j + 1) / 2 + i]+" ");
                System.out.println();
            }
        }
    }
}
