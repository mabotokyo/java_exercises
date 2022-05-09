package com.company;

public class WrappingRainWater {

    public static void main(String[] args){
        int height[] = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));
    }

    public static int trap(int[] height) {
        int res = 0, h1 = 0, h2 = 0;
        for (int i = 0, j = height.length - 1; i < height.length && j >= 0; i++, j--) {
            h1 = Math.max(h1, height[i]);
//            System.out.print(h1);
//            System.out.println(height[i]);
            h2 = Math.max(h2, height[j]);
//            System.out.print(h2);
//            System.out.println(height[j]);
            res += h1 + h2 - height[i]; // 红色面积 + 蓝色面积 - 黑色面积
        }
        return res - height.length * h1; // 再减去整个矩形面积
    }
}
