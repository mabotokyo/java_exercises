package com.company;

import java.util.*;
class CountCharTreeMap
{
    public static void main(String[] args)
    {
        String str = "abcedsadgsadfasdfaa";
        TreeMap<Character,Integer> tm = Pross(str);
        System.out.println(tm);
    }
    public static TreeMap<Character,Integer> Pross(String str)
    {
        char[] charArray = str.toCharArray();

        TreeMap<Character,Integer> tm = new TreeMap<Character,Integer>();

        for (int x = 0; x < charArray.length; x++)
        {
            if(!tm.containsKey(charArray[x]))
            {
                tm.put(charArray[x],1);
            }
            else
            {
                int count = tm.get(charArray[x])+1;
                tm.put(charArray[x],count);
            }
        }
        return tm;
    }
}
