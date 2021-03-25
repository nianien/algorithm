package com.lining;

import com.lining.arraymove.MovingArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: skyfalling
 * Date: 12-10-24
 * Time: 下午1:00
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Parameterized.class)
public class TestArrayMoving {

    private Integer[] testArray;
    private int moved;

    @Parameterized.Parameters
    public static Collection params() {
        return Arrays.asList(new Object[][]{
                new Object[]{new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9}, 6},
                new Object[]{new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5}, 5}
        });
    }

    public TestArrayMoving(Integer[] arr, int k) {
        this.testArray = arr;
        this.moved = k;
    }

    @Test
    public void test() {
        System.out.println("before:" + Arrays.toString(testArray) + ",move left:" + moved);
        Integer[] arr1 = Arrays.copyOf(testArray, testArray.length);
        int count1 = MovingArray.moveLeft(arr1, moved);
        System.out.println(count1 + " moved:" + Arrays.toString(arr1));
        Integer[] arr2 = Arrays.copyOf(testArray, testArray.length);
        int count2 = MovingArray.moveLeft2(arr2, moved);
        System.out.println(count2 + " moved:" + Arrays.toString(arr2));
        assertTrue(Objects.deepEquals(arr1, arr2));
        assertTrue(count1 <= count2);
        System.out.println("==================================");
    }
}
