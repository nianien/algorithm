package com.lining.minsubstring;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 题目:给一个字符串s1和一个小串s2, 求算法能在s1中找到包含s2里所有字符的最小子串.
 * 如:s1 = "ADOBECODEBANC", s2 = "ABC",则最小子串为"BANC",要求O(N)的算法.
 * 思路:
 *  1.先找出第一个包含所有字符的子串,记下起始索引位置:begin,end
 *  2.然后end向后移动一个位置,判断begin可移动的位置,begin移动的条件是除掉begin位置的字符仍包含所有字符
 *  3.当begin移动结束后,得到一个新的子串,判断该子串与上一个子串的长度,取较小的子串,更新begin和end位置
 *  重复2~3得到最小子串
 * </pre>
 * User: skyfalling
 * Date: 12-10-22
 * Time: 下午9:47.
 */
public class MinimalSubString {

    /**
     * 求包含全部字符的最小子串，不支持循环
     *
     * @param source
     */
    public String min(String source) {
        //扫描字符串,统计共有几种字符
        Map<Character, Integer> counter = new HashMap<Character, Integer>(source.length());
        char[] array = source.toCharArray();
        for (char ch : array) {
            counter.put(ch, 0);
        }
        int different = counter.size();
        counter.clear();
        int length = source.length();
        //记录最小子串的起止索引位置(左闭右开)
        int from = 0, to = length;
        for (int begin = 0, end = 0; end < length; end++) {
            char ch = array[end];
            addIfNotExists(counter, ch);
            //从当前位置去重,即如果当前位置的字符在之后还有出现,那么移除当前字符
            while (removeIfMoreThanOne(counter, array[begin])) {
                begin++;
            }
            if (counter.size() == different) {
                if (end - begin < to - from) {
                    from = begin;
                    to = end;
                }
                counter.remove(array[begin]);
                begin++;
            }
        }
        //处理结果
        return handle(source, from, to);
    }

    /**
     * 求包含全部字符的最小子串,支持循环
     *
     * @param source
     */
    public String minInCycle(String source) {

        //扫描字符串,统计共有几种字符
        Map<Character, Integer> counter = new HashMap<Character, Integer>(source.length());
        char[] array = source.toCharArray();
        for (char ch : array) {
            counter.put(ch, 0);
        }
        int different = counter.size();
        counter.clear();
        int length = source.length();
        //记录最小子串的起止索引位置
        int from = 0, to = length;
        //begin,end记录当前子串的起止索引位置
        //循环终止条件：1）end-begin>=length，表示如果整个字符串就是最小子串，那么它就是唯一的最小子串
        //2）begin >length，以最后一个元素为起始位置的最小子串，是最后一个不重复的最小子串
        for (int begin = 0, end = 0; end - begin < length && begin < length; end++) {
            char ch = array[end % length];
            addIfNotExists(counter, ch);
            while (removeIfMoreThanOne(counter, array[begin])) {
                if (begin < length - 1)
                    begin++;
                else
                    break;
            }
            if (counter.size() == different) {
                if (end - begin < to - from) {
                    from = begin;
                    to = end;
                }
                counter.remove(array[begin]);
                begin++;
            }
        }
        //处理结果
        return handle(source, from, to);
    }

    /**
     * 打印字符串索引从from到to之间的子串并返回。当索引到达字符串末尾时，则重新定位到开始位置
     *
     * @param str
     * @param from
     * @param to
     * @return
     */
    private String handle(String str, int from, int to) {
        StringBuilder sb = new StringBuilder(to - from + 1);
        for (int i = from; i <= to; i++) {
            sb.append(str.charAt(i % str.length()));
        }
        String substring = sb.toString();
        int length = str.length();
        System.out.println("[" + from + ":" + (to > length ? (length - 1) + ",0:" : "") + (to % length) + "|length=" + substring.length() + "]" + substring);
        return substring;
    }


    /**
     * 统计指定字符串的个数
     *
     * @param map
     * @param target
     */
    private void addIfNotExists(Map<Character, Integer> map, char target) {
        Integer n = map.get(target);
        map.put(target, n == null ? 1 : n + 1);
    }

    /**
     * 如果指定字符串计数大于1,则计数减1,并返回true
     *
     * @param map
     * @param target
     * @return
     */
    private boolean removeIfMoreThanOne(Map<Character, Integer> map, char target) {
        Integer n = map.get(target);
        if (n != null && n > 1) {
            map.put(target, n - 1);
            return true;
        }
        return false;
    }
}
