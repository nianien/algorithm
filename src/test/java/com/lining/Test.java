package com.lining;

import java.util.ArrayList;
import java.util.List;

public class Test {


    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}, {17, 18, 19, 20}
        };
        System.out.println(visit(arr));
        arr = new int[][]{{1, 2, 3, 4, 5, 6}, {7, 8, 9, 10, 11, 12}, {13, 14, 15, 16, 17, 18}};
        System.out.println(visit(arr));
        arr = new int[][]{{1, 2, 3}, {7, 8, 9}, {13, 14, 15}, {16, 17, 18}
        };
        System.out.println(visit(arr));
    }

    public static List<Integer> visit(int[][] array) {
        List<Integer> list = new ArrayList<>();
        int x1 = 0, y1 = 0, x2 = array.length - 1, y2 = array[0].length - 1;
        while (x1 <= x2 && y1 <= y2) {
            for (int i = y1; i <= y2; i++) {
                list.add(array[x1][i]);
            }
            for (int i = x1 + 1; i <= x2; i++) {
                list.add(array[i][y2]);
            }
            //保证不同行，避免重复
            for (int i = y2 - 1; i >= x1 && x1 < x2; i--) {
                list.add(array[x2][i]);
            }
            //保证不同列，避免重复
            for (int i = x2 - 1; i > x1 && y1 < y2; i--) {
                list.add(array[i][y1]);
            }
            x1++;
            y1++;
            x2--;
            y2--;
        }
        return list;

    }


}
