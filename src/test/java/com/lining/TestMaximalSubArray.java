package com.lining;

import com.lining.maxsubarray.MaximalSubArray;
import com.sun.tools.javac.util.ArrayUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: skyfalling
 * Date: 12-10-23
 * Time: 下午1:13
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Parameterized.class)
public class TestMaximalSubArray {
    private MaximalSubArray maximalSubArray = new MaximalSubArray();

    private int[] testArray;
    private int[] result;


    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][]{
                {new int[]{4,5,6, -123, 1,14}, new int[]{1,14}},
                {new int[]{1, 2, -2, 4, 5, 6}, new int[]{1, 2, -2, 4, 5, 6}}
        });
    }

    public TestMaximalSubArray(int[] array,int[] result) {
        this.testArray = array;
        this.result=result;
    }

    @Before
    public void before() {
        System.out.println("test:" + Arrays.toString(testArray));
    }

    @After
    public void after() {
        System.out.println("==============================");
    }


    @Test
    public void max() {
        assertTrue(Objects.deepEquals(maximalSubArray.max(testArray), result));
    }
}
