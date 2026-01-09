package com.lining;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : liyifei
 * @created : 2025/10/29, Wednesday
 * Copyright (c) 2004-2029 All Rights Reserved.
 **/
public class MaxSubString {

    /*给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
    示例 1:

    输入: s = "abcabcbb"
    输出: 3
    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。
    示例 2:

    输入: s = "bbbbb"
    输出: 1
    解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
    示例 3:

    输入: s = "pwwkew"
    输出: 3
    解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
    请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。


*/

    public static int lengthOfLongestSubstring(String str) {
        int n = str.length();
        int max=1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                String s = str.substring(i, j);
                if (s.indexOf(str.charAt(j)) != -1) {
                    if (j-i>max){
                        max=j-i;
                    }
                    break ;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
}
