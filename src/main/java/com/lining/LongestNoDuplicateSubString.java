package com.lining;

import java.util.HashMap;
import java.util.Map;

public class LongestNoDuplicateSubString {

    public static void main(String[] args) {

        System.out.println(longestNotDuplicateSubStr("abcbbdefb"));
    }
    private static String longestNotDuplicateSubStr(String str){
        int begin=0,end=0;
        int min=0, max=0;
        Map<Character,Integer> count=new HashMap();
        for(int i=0; i<str.length();i++){
            char ch= str.charAt(i);
            if(count.containsKey(ch)){
                if(end-begin>max-min){
                    max=end;
                    min=begin;
                }
                begin=count.get(ch)+1;
            }
            count.put(ch,i);
            end=i;
        }
        if(end-begin>max-min){
            max=end;
            min=begin;
        }
        return str.substring(min,max+1);

    }
}
