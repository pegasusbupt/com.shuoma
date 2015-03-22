package com.shuoma;
import java.util.*;
public class MaximumGap {
    public static void main(String[] args) {
        System.out.println(maximumGap(new int[]{3,2,1,100}));
    }

    public static int maximumGap(int[] num) {
        if (num == null || num.length < 2) return 0;
        int n = num.length;

        // first pass to get min and max
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, num[i]);
            max = Math.max(max, num[i]);
        }

        // second pass: bucketisize and get the max and min in each bucket
        int bucketGap = (int) Math.ceil((max - min + .0) / (n - 1));
        int[] maxB = new int[n];
        int[] minB = new int[n];
        Arrays.fill(maxB, Integer.MIN_VALUE);
        Arrays.fill(minB, Integer.MAX_VALUE);

        for (int e : num) {
            int bIdx = (e - min) / bucketGap;
            maxB[bIdx] = Math.max(maxB[bIdx], e);
            minB[bIdx] = Math.min(minB[bIdx], e);
        }
        //System.out.println(Arrays.toString(maxB));
        //System.out.println(Arrays.toString(minB));

        // third pass: calcualte the maxGap
        int maxGap = 0, prev = min;
        for (int i = 0; i < n; i++) {
            if (minB[i] == Integer.MAX_VALUE && maxB[i] == Integer.MIN_VALUE) continue;
            maxGap = Math.max(maxGap, minB[i] - prev);
            prev = maxB[i];
        }
        return maxGap;
    }
}