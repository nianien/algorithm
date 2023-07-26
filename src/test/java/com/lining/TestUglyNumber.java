package com.lining;

import com.lining.uglynumber.UglyNumber;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: skyfalling
 * Date: 12-10-22
 * Time: 下午11:56
 * To change this template use File | Settings | File Templates.
 */
public class TestUglyNumber {

    private UglyNumber uglyNumber = new UglyNumber();
    private int testNum;

    @Before
    public void before() {
        testNum = 10;
    }

    @Test
    public void test() {
        long t1 = System.nanoTime();
        long[] un2 = uglyNumber.findUgly2(testNum);
        long t2 = System.nanoTime();

        System.out.println("time:" + (t2 - t1));
        t1 = System.nanoTime();
        long[] un = uglyNumber.findUgly(testNum);
        t2 = System.nanoTime();
        System.out.println("time:" + (t2 - t1));
        assertTrue(Objects.deepEquals(un, un2));
        System.out.println(Arrays.stream(un).mapToObj(i->i).collect(Collectors.toList()));
    }

}
