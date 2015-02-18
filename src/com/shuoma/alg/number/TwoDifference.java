package com.shuoma.alg.number;

import java.util.Arrays;

/**
 * Given a sorted array, find two elements i and j, num[i] - num[j] = target.
 * Space:O(1), Time: O(N)
 */
public class TwoDifference {
  
  public static void main(String[] args){
    TwoDifference finder = new TwoDifference();
    int[] num = new int[]{1,3,4,7,10,12};
    int[] pair = finder.twoDifference(4, num);
    System.out.println(Arrays.toString(pair));  //{3,1}
  }

  int[] twoDifference(int target, int[] num) {
    int first = num.length - 1;
    int second = num.length - 1;
    while(first >= 0 && second >= 0){
      int result = num[first] - num[second];
      if(result == target) return new int[]{first, second};
      else if(result < target) second--;
      else first --;
    }
    return new int[]{-1,-1};
  }
}
