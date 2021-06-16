package leetcode.editor.cn.defined.java;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * scm.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
public class Lists {

    /**
     * 整型列表
     *
     * @param arr
     * @return
     */
    public static List<Integer> toList(int[] arr) {
        return Arrays.stream(arr).mapToObj(i -> i).collect(Collectors.toList());
    }

    /**
     * 整型数组
     *
     * @param list
     * @return
     */
    public static int[] toInts(List<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
