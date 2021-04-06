package com.lining;

/**
 * 从0打印n位最大10进制数
 * scm.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
public class 大数打印 {

    public static void main(String[] args) {
        print(2);
    }

    private static void print(int n) {

        char[] arr = new char[n];
        for (int i = 0; i < n; i++) {
            arr[i] = '0';
        }
        out:
        for (; ; ) {
            while (arr[0] < '9') {
                arr[0]++;
                print(arr);
            }
            for (int i = 1; i < n; i++) {
                if (arr[i] < '9') {
                    arr[i]++;
                    break;
                } else if (i == n - 1) {
                    break out;
                } else {
                    arr[i] = '0';
                }
            }
            arr[0] = '0';
            print(arr);
        }

    }

    private static void print(char[] arr) {
        StringBuilder s = new StringBuilder();
        int n = arr.length;
        boolean valid = false;
        for (n--; n >= 0; n--) {
            if (arr[n] != '0') {
                valid = true;
            }
            if (valid || arr[n] != '0') {
                s.append(arr[n]);
            }
        }
        System.out.println(s.toString());
//        System.out.println(new StringBuilder(new String(arr)).reverse());
    }


}
