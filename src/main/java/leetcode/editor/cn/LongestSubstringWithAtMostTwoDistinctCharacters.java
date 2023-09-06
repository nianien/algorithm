//159. longest-substring-with-at-most-two-distinct-characters
//Input: s = "eceba"
//Output: 3
//Explanation: The substring is "ece" which its length is 3.
//Input: s = "ccaabbb"
//Output: 5
//Explanation: The substring is "aabbb" which its length is 5.
package leetcode.editor.cn;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : liyifei
 * @created : 2023/10/26, 星期四
 * Copyright (c) 2004-2029 All Rights Reserved.
 **/
public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public static void main(String[] args) {
        LongestSubstringWithAtMostTwoDistinctCharacters.Solution solution = new LongestSubstringWithAtMostTwoDistinctCharacters().new Solution();
        System.out.println(solution.longestSubstringTwoDistinct("aaghjbbbbbbbbbbbccadd", 3));
        System.out.println(solution.longestSubstringTwoDistinct("abbccddeeff", 3));
        System.out.println(solution.longestSubstringTwoDistinct("aabbccbbaaddd", 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestSubstringTwoDistinct(String s, int limit) {
            if (s == null || s.length() <= limit) {
                return s;
            }
            //存储每个字符的最新索引位置, 通过LinkedHashMap获取索引位置最小的字符
            Map<Character, Integer> char2Index = new LinkedHashMap<>(3, 0.75f, true);
            //最长字符串的起始位置
            int lbegin = 0;
            //最长字符串的截止位置
            int lend = 0;
            int start = 0;
            char[] chs = s.toCharArray();
            int n = s.length();
            for (int i = 0; i < n; i++) {
                char2Index.put(chs[i], i);
                if (char2Index.size() > limit) {
                    Iterator<Map.Entry<Character, Integer>> it = char2Index.entrySet().iterator();
                    //获取索引位置最小的字符, 如当前访问记录为:aabbccbbaa,此时索引位置最小的字符为c
                    //此时应该从移除c,然后开始计算长度
                    Map.Entry<Character, Integer> entry = it.next();
                    it.remove();
                    if (i - start > lend - lbegin) {
                        lbegin = start;
                        lend = i;
                    }
                    start = entry.getValue() + 1;
                }
            }
            if (n - start > lend - lbegin) {
                lbegin = start;
                lend = n;
            }
            return s.substring(lbegin, lend);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
