package com.lining.climbstair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * 问题：
 *      给定n阶楼梯，可以一次跨1阶、2阶……k(k<=n)阶，
 *      求共有多少走法，并记录每种走法
 * 记录走法：
 *      分析如下，先确定第一步的走法，分别可以1阶、2阶...k阶，
 *      当跨1阶时，对剩余n-1阶递归求第一步的走法
 *      当跨2阶时，对剩余n-2阶递归求第一步的走法
 *      当跨k阶时，对剩余n-k阶递归求第一步的走法
 *  等价问题：
 *      将整数n分解成不大于k的正因子有序加和，统计所有因子组合的总数
 * </pre>
 */
public class StairClimber {

    public interface StepsRecorder {
        /**
         * 记录到达楼顶时的当前走法
         *
         * @param steps
         */
        void record(List<Integer> steps);
    }

    private StepsRecorder recorder;

    /**
     * 构造方法
     *
     * @param recorder
     */
    public StairClimber(StepsRecorder recorder) {
        this.recorder = recorder;
    }

    /**
     * 默认构造方法
     */
    public StairClimber() {
        this(steps -> System.out.println(steps));
    }

    /**
     * 将整数number分解成不大于maxFactor的正因子有序加和，统计所有因子组合的总数
     *
     * @param number
     * @param maxFactor
     * @return
     */
    public static int decompose(int number, int maxFactor) {
        if (number <= maxFactor)
            return decompose(number);
        int count = 0;
        for (int i = 1; i <= maxFactor; i++) {
            count += decompose(number - i, maxFactor);
        }
        return count;
    }

    /**
     * 将整数number分解成正因子的有序加和，统计所有因子组合数<br/>
     * 注：这里当number小于等于0时，认定组合数为1
     *
     * @param number
     * @return
     */
    public static int decompose(int number) {
        if (number <= 1)
            return 1;
        int count = 0;
        for (int i = 1; i <= number; i++)
            count += decompose(number - i);
        return count;
    }


    /**
     * 楼梯数为level,最大步长为maxStep的全部走法
     *
     * @param level
     */
    public int climb(int level, int maxStep) {
        if (level <= 0 || maxStep <= 0)
            throw new IllegalArgumentException("参数level和maxStep必须大于零!");
        if (level < maxStep)
            throw new IllegalArgumentException("参数maxStep不能大于level!");
        int count = 0;
        List<Integer> steps = new ArrayList<Integer>();
        for (int i = 1; i <= maxStep; i++) {
            //开始时跨i阶
            count += climb(level, i, maxStep, steps);
            //清除第一步
            backStep(steps);
        }
        return count;
    }

    /**
     * 对n阶楼梯，起步跨step阶的走法
     *
     * @param n
     * @param step
     * @param maxStep
     * @param steps
     * @return
     */
    private int climb(int n, int step, int maxStep, List<Integer> steps) {
        if (step > n)
            return 0;
        steps.add(step);
        if (n == step) {
            //当走到最后一层时,此为一种走法
            //记录每次走法
            recorder.record(steps);
            return 1;
        }
        int count = 0;
        //如果没有达到最后一层，则递归剩余楼梯的走法
        //此时，第一步可跨最大阶数由允许所跨最大阶数和剩余楼梯阶数的较小值决定
        for (int i = 1; i <= maxStep && i <= n - step; i++) {
            //递归剩余楼梯第一步的每种走法
            count += climb(n - step, i, maxStep, steps);
            //退出递归时，清除剩余楼梯的走法以记录新的走法
            backStep(steps);
        }
        return count;
    }

    /**
     * 撤销当前步，以便重新走下一步
     *
     * @param list
     */
    private void backStep(List<Integer> list) {
        list.remove(list.size() - 1);
    }


}
