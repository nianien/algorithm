package com.lining.arraymove;

/**
 * 题目：设计一个时间复杂度为o(n),空间复杂度不超过o(2)的算法,实现数组a[0.n-1]中所有元素依次循环左（右）移k个位置<br/>
 * Tips: 移动法时间复杂度为o(kn),翻转法时间复杂度为o(2n),定位法时间复杂度为o(n)<br/>
 * User: skyfalling
 * Date: 12-10-24
 * Time: 上午11:25
 * To change this template use File | Settings | File Templates.
 */
public class MovingArray {

    /**
     * 利用定位法将数组元素循环左移指定位数，返回数组元素移动次数<br/>
     * 其中，k为负值表示反方向移动
     *
     * @param arr
     * @param k
     * @param <T>
     * @return
     */
    public static <T> int moveLeft(T[] arr, int k) {
        int length = arr.length;
        //等价移动的最少位置
        k %= length;
        if (k == 0)
            return 0;
        if (k < 0)
            k = length + k;
        int count = 0;
        //求最大公约数p
        int p = gcd(length, k);
        //如果p>1，则表示需要p轮替换才能完成移动，每个元素只会在第gcd(p,i)轮中被替换
        for (int i = 0; i < p; i++) {
            //temp记录开始位置的元素，current为当前元素的位置，next为将要移动到当前位置的元素位置
            T temp = arr[i];
            int current = i, next = (i + k) % length;
            //依次替换当前位置的元素，直到回到初始位置
            while (i != next) {
                count++;
                arr[current] = arr[next];
                current = next;
                next = (next + k) % length;
            }
            arr[current] = temp;
            count++;
        }
        return count;
    }


    /**
     * 利用翻转法将数组元素循环左移指定位数，返回数组元素移动次数<br/>
     * 其中，k为负值表示反方向移动
     *
     * @param arr
     * @param k
     * @param <T>
     */
    public static <T> int moveLeft2(T[] arr, int k) {
        int length = arr.length;
        //等价移动的最少位置
        k %= length;
        if (k == 0)
            return 0;
        if (k < 0)
            k = length + k;
        int count = 0;
        count += reverse(arr, 0, k - 1);
        count += reverse(arr, k, arr.length - 1);
        count += reverse(arr, 0, arr.length - 1);
        return count;
    }


    /**
     * 辗转相除法求最大公约数
     *
     * @param bigger
     * @param smaller
     * @return
     */
    private static int gcd(int bigger, int smaller) {
        if (bigger < smaller)
            return gcd(smaller, bigger);
        if (bigger % smaller == 0)
            return smaller;
        return gcd(bigger % smaller, smaller);
    }

    /**
     * 翻转数组索引begin和end之间的元素
     *
     * @param arr
     * @param begin
     * @param end
     * @param <T>
     * @return
     */
    private static <T> int reverse(T[] arr, int begin, int end) {
        int count = 0;
        for (; begin < end; begin++, end--) {
            T temp = arr[begin];
            arr[begin] = arr[end];
            arr[end] = temp;
            count += 2;
        }
        return count;
    }
}
