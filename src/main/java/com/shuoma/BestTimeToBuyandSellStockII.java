package com.shuoma;

import static com.shuoma.annotation.Tag.DataStructure.Subarray;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dss = Subarray, source = LeetCode)
public class BestTimeToBuyandSellStockII {
  public int maxProfit(int[] prices) {
    if (prices.length == 0)
      return 0;
    int prev = prices[0], ret = 0;
    for (int i = 1; i < prices.length; i++) {
      ret += Math.max(0, prices[i] - prev);
      prev = prices[i];
    }
    return ret;
  }
}
