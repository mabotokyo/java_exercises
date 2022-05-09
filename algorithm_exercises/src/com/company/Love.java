package com.company;
/*
Check if set of characters exists in a string or not - Improvement

Two English words are similar if they only contain the same alphabetical letters. For example, food and good are not similar, but dog and good are similar. (If A is similar to B, then all letters in A are contained in B, and all letters in B are contained in A.)

Given a word W and a list of words L, find all words in L similar to W. Print the word count to standard output.
 */

import java.util.TreeSet;

public class Love {

    static TreeSet<Character> getSet(String s){
        TreeSet<Character> set = new TreeSet<Character>();
        char[] array = s.toCharArray();
        for(Character c: array){
            if (!set.contains(c)){
                set.add(c);
            }
        }
        return set;
    }

    static int checkSimilar(String[] arr, String s)
    {
        int result = 0;
        TreeSet<Character> sset = getSet(s);

        for(String a: arr){
            TreeSet<Character>  aset = getSet(a);
            if (aset.equals(sset)){
                System.out.println(a);
//                System.out.println(aset);
//                System.out.println(s);
//                System.out.println(sset);
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"velo", "low", "vole", "lovee", "volvell", "lowly", "lower", "lover", "levo", "loved", "love",
                "lovee", "lowe", "lowes", "lovey", "lowan", "lowa", "evolve", "loves", "volvelle", "lowed", "love"};
        String s = "love";

        System.out.println(checkSimilar(arr,s));
    }
}
