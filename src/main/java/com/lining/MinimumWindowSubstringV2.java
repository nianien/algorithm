package com.lining;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : liyifei
 * @created : 2026/1/13, Tuesday
 * Copyright (c) 2004-2029 All Rights Reserved.
 **/
public class MinimumWindowSubstringV2 {

    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> needs = new HashMap<>();
        for (char c : t.toCharArray()) {
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> window = new HashMap<>();
        int missing = t.length();
        int b = 0, e = 0, l = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (needs.containsKey(ch)) {
                window.put(ch, window.getOrDefault(ch, 0) + 1);
                if (window.get(ch) <= needs.get(ch)) {
                    missing--;
                }
            }
            while (missing == 0) {
                if (e == 0 || i - l < e - b) {
                    b = l;
                    e = i + 1;
                }
                char p = s.charAt(l);
                if (window.containsKey(p)) {
                    window.put(p, window.get(p) - 1);
                    if (window.get(p) < needs.get(p)) {
                        missing++;
                    }
                }
                l++;
            }
        }
        return s.substring(b, e);
    }
}
