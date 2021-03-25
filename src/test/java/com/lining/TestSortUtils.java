package com.lining;

import com.lining.sorting.SortUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: skyfalling
 * Date: 12-10-23
 * Time: 下午3:54
 * To change this template use File | Settings | File Templates.
 */
public class TestSortUtils {
    @Test
    public void quickSort() {
        Integer[] arr = {3, 5, 2, 4, 9, 8, 7, 6, 1, 5, 3, 6};
        Integer[] arr2 = {3, 5, 2, 4, 9, 8, 7, 6, 1, 5, 3, 6};
        SortUtils.quickSort(arr);
        Arrays.sort(arr2);
        System.out.println(Arrays.toString(arr));
        assert Objects.deepEquals(arr, arr2);
    }
}
