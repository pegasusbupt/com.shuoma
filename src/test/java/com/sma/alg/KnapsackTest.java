package com.sma.alg;

import com.sma.util.RandomUtil;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.junit.Test;

import java.util.Arrays;

import static com.sma.util.ArrayUtil.intToDoubleArray;
import static com.sma.util.RandomUtil.r;

public class KnapsackTest {

  @Test
  public void test() {
    Knapsack ins = new Knapsack();
    for (int i = 0; i < 10; i++) {
      int len = r.nextInt(10) + 3;
      int maxNumber = 10;
      int[] weights = RandomUtil.genRandomArray(len, maxNumber, true, false);
      int[] values = RandomUtil.genRandomArray(len, maxNumber, true, false);
      double multiplier = (5 + r.nextInt(5)) / 10.0;
      int W = (int) ((new Sum()).evaluate(intToDoubleArray(weights), 0, len) * multiplier);

      int[] res = new int[3];
      res[0] = packDummy(W, weights, values);
      res[1] = ins.itemMajorOrder(W, weights, values, false);
      res[2] = ins.capacityMajorOrder(W, weights, values);
      if (res[0] != res[1] || res[0] != res[2]) {
        System.out.println("capacity: " + W);
        System.out.println("weights: " + Arrays.toString(weights));
        System.out.println("values: " + Arrays.toString(values));
        System.out.println("max value: " + Arrays.toString(res));
        System.out.println();
      }
    }
  }

  int packDummy(int W, int[] weights, int[] values) {
    int[] maxV = new int[1];
    packDummyRecur(weights, values, W, 0, 0, 0, maxV);
    return maxV[0];
  }

  void packDummyRecur(int[] weights, int[] values, int w, int i, int totW, int totV, int[] maxV) {
    if (totW > w) return;
    if (totV > maxV[0]) maxV[0] = totV;
    if (i == weights.length) return;
    packDummyRecur(weights, values, w, i + 1, totW + weights[i], totV + values[i], maxV);
    packDummyRecur(weights, values, w, i + 1, totW, totV, maxV);
  }
}
