package com.company;

import java.util.Arrays;

public class WashingMachine {

    public static void main(String[] args){

        int machines[] = {1,0,5,6};
        System.out.println(findMinMoves2(machines));
    }

    public static int findMinMoves(int[] machines) {
        int tot = Arrays.stream(machines).sum();
        int n = machines.length;
        if (tot % n != 0) {
            return -1;
        }
        int avg = tot / n;
        int ans = 0, sum = 0;
        for (int num : machines) {
            num -= avg;
            sum += num;
            ans = Math.max(ans, Math.max(Math.abs(sum), num));
        }
        return ans;
    }

    public static int findMinMoves2(int[] machines) {
        // 先求出总和，然后判断是否能均分
        int sum = 0;
        int len = machines.length;
        for (int i = 0; i < len; i++) {
            sum += machines[i];
        }
        // 不能均分则返回 -1
        if (sum%len != 0) {
            return -1;
        }

        // res：最终结果，balance：当前位置到达平衡所需要移动的次数，avg：平均值
        int res = 0, balance = 0, avg = sum / len;
        for (int i = 0; i < len; i++) {
            // 在前面位全部到达平均的前提下，当前位置达到平衡所需要切换的次数
            balance += machines[i] - avg;
            // 选出变化值最大的一次
            // 仍要考虑machines[i]-avg是因为当前洗衣机可能衣服很多，是一个向两边输出衣服的角色
            // 那么这时最大变化就是machines[i]-avg了
            res = Math.max(res, Math.max(machines[i] - avg, Math.abs(balance)));
        }
        return res;
    }
}
