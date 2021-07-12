package com.lining;

import com.lining.permutation.MultiPermutation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMultiPermutation {


    private MultiPermutation multiPermutation = new MultiPermutation();


    @Before
    public void before() {
        multiPermutation = new MultiPermutation();
    }

    @Test
    public void test() {
        assertEquals(multiPermutation.permute("123"), factorial("123".length()));
//        assertEquals(multiPermutation.permute("abbcddef"), factorial("abbcddef".length()) / (factorial("bb".length()) * factorial("dd".length())));
    }

    private long factorial(int n) {
        if (n == 1 || n == 0)
            return 1;
        return n * factorial(n - 1);
    }
}
