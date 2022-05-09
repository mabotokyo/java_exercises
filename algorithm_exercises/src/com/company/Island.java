package com.company;

import java.util.Arrays;

public class Island {
    static int daoyu[][] =
            {
                    { 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 1, 0, 0, 0, 0, 0 },
                    { 0, 1, 1, 1, 0, 0, 0, 0 },
                    { 0, 0, 1, 0, 0, 1, 1, 0 },
                    { 0, 0, 0, 0, 0, 1, 1, 0 },
                    { 0, 0, 1, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 1 },
            };

    public static void main(String[] args) {
        for(int i=0;i<daoyu.length;i++) {
            System.out.println(Arrays.toString(daoyu[i]));
        }
        //System.out.println(Arrays.deepToString(daoyu));
        int count = 0;
        for (int i = 0; i < daoyu.length; i++)
            for (int j = 0; j < daoyu[i].length; j++)
                if (daoyu[i][j] == 1) {
                    count++;
                    lj(i, j);
                }
        //System.out.println(Arrays.deepToString(daoyu));
        System.out.println();
        for(int i=0;i<daoyu.length;i++)
        {
            //System.out.println(daoyu[i]);
            System.out.println(Arrays.toString(daoyu[i]));
            //Arrays.stream(daoyu[i]).forEach(System.out::print);
            //System.out.println(Arrays.asList(daoyu[i]));
        }
        System.out.println("岛屿数量为:" + count);
    }
    //递归修改附近数值
    static void lj(int i, int j) {
        daoyu[i][j] = 2;
        // 上边
        if (i - 1 >= 0 && daoyu[i - 1][j] == 1)
            lj(i - 1, j);
        // 下边
        if (i + 1 < daoyu.length && daoyu[i + 1][j] == 1)
            lj(i + 1, j);
        // 左边
        if (j - 1 >= 0 && daoyu[i][j - 1] == 1)
            lj(i, j - 1);
        // 右边
        if (j + 1 < daoyu[i].length && daoyu[i][j + 1] == 1)
            lj(i, j + 1);
    }
}
