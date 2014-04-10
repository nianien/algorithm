package com.lining.uglynumber;

import com.nianien.core.exception.ExceptionHandler;

/**
 * 寻找丑数 题目：我们把只包含因子2、3和5的数称作丑数（Ugly Number） 习惯上我们把1当做是第一个丑数 求按从小到大的顺序的第1500个丑数
 * 算法思想: 1)从小到大依次遍历当前丑数序列中的数字,并分别乘以2\3\5使乘积大于当前最大丑数,乘积分别记作a,b,c,则下一丑数为min{a,b,c}
 *
 * @author NiaNien
 */
public class UglyNumber {

    /**
     * long型溢出抛异常
     *
     * @param n
     * @return
     */
    public long[] findUgly(int n) {
        long[] uglies = new long[n];
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;
        uglies[0] = 1;
        int i = 1;
        long a;
        long b ;
        long c;
        while (i < n) {
            while ((a = uglies[i2] * 2) <= uglies[i - 1])
                i2++;
            while ((b = uglies[i3] * 3) <= uglies[i - 1])
                i3++;
            while ((c = uglies[i5] * 5) <= uglies[i - 1])
                i5++;
            uglies[i] = min(a, b, c);
            i++;
        }

        return uglies;
    }

    /**
     * long型溢出不抛异常,返回错误结果
     *
     * @param n
     * @return
     */
    public long[] findUgly2(int n) {
        long[] uglies = new long[n];
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;
        uglies[0] = 1;
        int i = 1;
        long a = 2;
        long b = 3;
        long c = 5;
        while (i < n) {
            switch (min235(a, b, c)) {
                case 2:
                    if (uglies[i - 1] != a) {
                        uglies[i++] = a;
                    }
                    a = uglies[i2++] * 2;
                    ExceptionHandler.throwIf(a < 0, "Long型溢出");
                    break;
                case 3:
                    if (uglies[i - 1] != b) {
                        uglies[i++] = b;
                    }
                    b = uglies[i3++] * 3;
                    ExceptionHandler.throwIf(b < 0, "Long型溢出");
                    break;
                case 5:
                    if (uglies[i - 1] != c) {
                        uglies[i++] = c;
                    }
                    c = uglies[i5++] * 5;
                    ExceptionHandler.throwIf(c < 0, "Long型溢出");
                    break;
            }
        }

        return uglies;
    }

    /**
     * 求最小值
     *
     * @param numbers
     * @return
     */
    private long min(long... numbers) {
        long min = numbers[0];
        for (long n : numbers)
            if (n < min)
                min = n;
        return min;
    }

    /**
     * 判断当前最小丑数应该乘2/3/5的哪个值
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    private int min235(long a, long b, long c) {
        return a < b ? a < c ? 2 : 5 : b < c ? 3 : 5;
    }
}
