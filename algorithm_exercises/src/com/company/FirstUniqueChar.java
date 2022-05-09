package com.company;

/*
Given a string, find the first non-repeating character in it and return it’s index. If it doesn’t exist, return -1.

Examples:

s = “leetcode”
return 0.

s = “loveleetcode”
return 2.
 */

import java.util.Arrays;

public class FirstUniqueChar {

    public static void main(String[] args){
        String s = "loveleetcode";
        System.out.println(firstUniqChar(s));
    }

    public static int firstUniqChar(String s) {
        int freq [] = new int[26];
        for(int i = 0; i < s.length(); i ++)
            freq [s.charAt(i) - 'a'] ++;
        Arrays.stream(freq).forEach(System.out::println);
        for(int i = 0; i < s.length(); i ++)
            if(freq [s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }
}
