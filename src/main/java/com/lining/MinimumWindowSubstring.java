package com.lining;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : liyifei
 * @created : 2026/1/13, Tuesday
 * Copyright (c) 2004-2029 All Rights Reserved.
 **/
public class MinimumWindowSubstring {

    public static String minWindow(String s, String t) {
        if (s == null || t == null || t.length() == 0 || s.length() < t.length()) return "";

        // needs: required counts in t
        // window: current counts in sliding window
        Map<Character, Integer> needs = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        int missing = t.length();

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }

        int l = 0;
        int b = 0, e = 0;

        for (int i = 0; i < s.length(); i++) {
            char v = s.charAt(i);

            if (needs.containsKey(v)) {
                int newCnt = window.getOrDefault(v, 0) + 1;
                window.put(v, newCnt);

                if (newCnt <= needs.get(v)) {
                    missing--;
                }
            }

            while (missing == 0) {
                // update best window
                if (e == 0 || i - l < e - b) {
                    b = l;
                    e = i + 1;
                }

                char c = s.charAt(l);
                if (needs.containsKey(c)) {
                    int newCnt = window.get(c) - 1;
                    window.put(c, newCnt);

                    if (newCnt < needs.get(c)) {
                        missing++;
                    }
                }
                l++;
            }
        }

        return s.substring(b, e);
    }
}
