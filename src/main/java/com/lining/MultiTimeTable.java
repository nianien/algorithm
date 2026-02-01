package com.lining;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * @author : liyifei
 * @created : 23/1/2026, 星期五
 * Copyright (c) 2004-2029 All Rights Reserved.
 **/
public class MultiTimeTable {

    private static final Map<Character, Character> PAIRS = Map.of(
            ')', '(',
            ']', '[',
            '}', '{'
    );

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (PAIRS.containsKey(c)) {
                if (stack.isEmpty()) {
                    return false;
                }
                char left = stack.pop();
                if (PAIRS.get(c) != left) {
                    //not pair:
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();

//        Deque<Character> stack = new ArrayDeque<>();
//
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//
//            if (PAIRS.containsKey(c)) {
//                // c 是右括号
//                if (stack.isEmpty()) return false;
//
//                char left = stack.pop();
//                if (left != PAIRS.get(c)) return false;
//
//            } else {
//                // c 是左括号（假设输入合法只包含括号）
//                stack.push(c);
//            }
//        }
//
//        return stack.isEmpty();
    }
}
