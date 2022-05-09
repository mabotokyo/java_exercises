package com.company;

import java.util.Arrays;

public class MinimumWindowSubstring {

    public static void main(String[] args){

        String S ="ADOBECODEBANC";
        String T ="ABC";
        System.out.println(minWindow(S,T));
    }

    public static String minWindow(String S, String T) {
        int[] map = new int[128];
        //init map, 记录T中每个元素出现的次数
        for(int i = 0; i < T.length(); i++) {
            map[T.charAt(i)]++;
        }
        Arrays.stream(map).forEach(System.out::print);

        // begin end两个指针指向窗口的首位，d记录窗口的长度， counter记录T中还有几个字符没被窗口包含
        int begin = 0, end = 0, d = Integer.MAX_VALUE, counter = T.length(), head = 0;
        // end指针一直向后遍历
        while(end < S.length()) {
            // map[] > 0 说明该字符在T中出现，counter-- 表示对应的字符被包含在了窗口，counter--, 如果s中的字符没有在T中出现，则map[]中对应的字符-1后变为负值
            if(map[S.charAt(end++)]-- > 0) {
                counter--;
            }
            System.out.println();
            Arrays.stream(map).forEach(System.out::print);
            System.out.println();
            // 当counter==0时，说明窗口已经包含了T中的所有字符
            while (counter == 0) {
                if(end - begin < d) {
                    d = end - (head = begin);
                }
                if(map[S.charAt(begin++)]++ == 0) {  // begin开始后移，继续向后寻找。如果begin后移后指向的字符在map中==0，表示是在T中出现的，如果没有出现，map[]中的值会是负值。
                    counter++;                      // 在T中的某个字符从窗口中移除，所以counter++。
                }
            }
        }
        return d==Integer.MAX_VALUE ? "" : S.substring(head, head+d);
    }

}
