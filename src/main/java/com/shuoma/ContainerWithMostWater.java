package com.shuoma;
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int res = 0;
        while(i < j)
        {
            int temp = Math.min(height[i], height[j]) * (j - i);
            if(temp > res)
                res = temp;
            if(height[i] <= height[j])
                i++;
            else
                j--;
        }
        return res;
    }
    
    
    //O(n^2) TLE
    public int maxAreaTLE(int[] height) {
       int n=height.length;
       if(n<1) return 0;
       int ret=0;
       for(int i=0;i<n;i++)
        for(int j=i+1;j<n;j++){
            if(ret<(j-i)*Math.min(height[i], height[j]) )
                ret=(j-i)*Math.min(height[i], height[j]);
        }
        return ret;
    }
}