package com.company;

import java.util.Arrays;
//随机产生10个100到200之间的正整数，按照从小到大的顺序的输出。
public class Random {
    public static void main(String[] args){

        int[] arr = new int[10];
        int i;
        for (i = 0;i< arr.length; i++){

            arr[i]=(int)(Math.random()*101+100);
            System.out.println(arr[i]);
        }
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
