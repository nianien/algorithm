package com.lining;

import com.lining.spiralmatrix.SpiralMatrix;
import org.junit.Test;

public class TestSpiralMatrix {


    @Test
    public void test() {
        int[][] arr1 = SpiralMatrix.clockwise(5, 9);
        for (int[] a : arr1) {
            for (int n : a) {
                System.out.print(n + "\t");
            }
            System.out.println();
        }
        System.out.println("==========================");
        int[][] arr2 = SpiralMatrix.counterClockwise(9, 5);
        for (int[] a : arr2) {
            for (int n : a) {
                System.out.print(n + "\t");
            }
            System.out.println();
        }
    }

}
