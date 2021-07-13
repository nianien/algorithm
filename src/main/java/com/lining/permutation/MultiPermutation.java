package com.lining.permutation;

import java.util.Arrays;

/**
 * <pre>
 * 功能：
 *      给定一个可重复的字符串，能够对所有字符进行全排列
 * 算法思想：
 *      要确定其中的一种排列,就必须知道数组中每个位置的元素
 *      首先，确定第一个位置，有n种可能，即依次将其他位置的元素与首位置调换
 *      每次确定当前位置的元素后，接着递归确定其后位置上的元素
 *      当最后一个位置的元素确定时，便得出其中的一种排列
 * 注意：
 *     当某个位置所有可能的排列完成以后，应恢复当前位置的初始元素，
 *     这样，当它前面的位置改变时，能够对其后所有位置重新生成全排列
 * 提示：
 *      设集合S={n1.a1,n2.a2,...nk.ak},n=n1+n2+...+nk,即存在n1个a1,n2个a2,...nk个ak的元素集合，
 *      那么存在的不同全排列数为：n!/(n1!*n2!*...*nk!)
 * </pre>
 */
public class MultiPermutation {
    public interface ArrayVisitor {
        void visit(char[] array);
    }

    private ArrayVisitor visitor;

    public MultiPermutation(ArrayVisitor visitor) {
        this.visitor = visitor;
    }

    public MultiPermutation() {
        this(arr -> System.out.println(new String(arr)));
    }

    /**
     * 对字符数组进行全排列,返回排列总数
     *
     * @param arr
     */
    public int permute(char[] arr) {
        return permute(arr, 0);
    }

    /**
     * 对字符串进行全排列,返回排列总数
     *
     * @param str
     */
    public int permute(String str) {
        return permute(str.toCharArray());
    }

    /**
     * 对字符数组进行全排列，递归确定位置k上的元素
     *
     * @param arr
     * @param k
     */
    private int permute(char[] arr, int k) {
        if (k == arr.length - 1) {
            // 此时，数组最后一个位置k上的元素已确定，从而确定了一种排列
            visitor.visit(arr);
            return 1;
        }
        int count = 0;
        // 位置k上的元素有ch.Length-k+1种可能值
        // 即k和其后位置上的元素都可能放在k位置上
        for (int i = k; i < arr.length; i++) {
            // 确定i位置上的字符是不是第一次出现，
            // 如果不是第一次出现，则相同的字符已经调换过
            if (this.isFirstChar(arr, k, i)) {
                //将i位置的元素移动到k之前
                this.moveHead(arr, k, i);
                count += this.permute(arr, k + 1);
                //将k位置的元素移动到i之后
                this.moveTail(arr, k, i);
            }
        }
        return count;
    }

    /**
     * 将索引较大的元素插入到索引较小的元素前面<br/>
     *
     * @param arr
     * @param index1
     * @param index2
     */
    private void moveHead(char[] arr, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        if (index1 > index2) {
            moveHead(arr, index2, index1);
            return;
        }
        char temp = arr[index2];
        for (int i = index2; i > index1; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index1] = temp;
    }

    /**
     * 将索引较小的元素插入到索引较大的元素后面
     *
     * @param arr
     * @param index1
     * @param index2
     */
    private void moveTail(char[] arr, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        if (index1 > index2) {
            moveTail(arr, index2, index1);
            return;
        }
        char temp = arr[index1];
        for (int i = index1; i < index2; i++) {
            arr[i] = arr[i + 1];
        }
        arr[index2] = temp;
    }

    /**
     * 查找从位置begin开始，end位置上的元素是不是第一次出现
     *
     * @param arr
     * @param begin
     * @param end
     * @return
     */
    private boolean isFirstChar(char[] arr, int begin, int end) {
        if (end > begin) {
            for (int i = begin; i < end; i++) {
                if (arr[i] == arr[end])
                    return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        MultiPermutation p = new MultiPermutation();
        char[] arr = new char[]{'1', '2', '3', '4'};
        p.moveHead(arr, 1, 3);
        System.out.println(new String(arr));
        p.moveTail(arr, 3, 1);
        System.out.println(new String(arr));
    }
}
