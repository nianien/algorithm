package com.lining;

import com.lining.minsubstring.MinimalSubString;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: skyfalling
 * Date: 12-10-22
 * Time: 下午11:25
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Parameterized.class)
public class TestMinimalSubString {

    private MinimalSubString minimalSubString = new MinimalSubString();
    private String testString;
    private String result;
    private String resultInCycle;

    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][]{
                {"afdadbcdefdcbfebcz", "adbcdefdcbfebcz", "ebczafd"},
                {"afdadbcdefdcbfebc", "adbcdef", "ebcafd"},
                {"abcdef", "abcdef", "abcdef"},
        });
    }

    public TestMinimalSubString(String testString, String result, String resultInCycle) {
        this.testString = testString;
        this.result = result;
        this.resultInCycle = resultInCycle;
    }

    @Before
    public void before() {
        System.out.println("test:" + testString);
    }

    @After
    public void after() {
        System.out.println("============================");
    }

    @Test
    public void min() {
        assertEquals(minimalSubString.min(testString), result);
    }

    @Test
    public void min2() {
        assertEquals(minimalSubString.minInCycle(testString), resultInCycle);
    }
}
