package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Arithmetic;
import static com.shuoma.annotation.Tag.Algorithm.Greedy;
import static com.shuoma.annotation.Tag.DataStructure.Array;

import com.shuoma.annotation.Tag;
import com.shuoma.util.CollectionsUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Tag(algs = {Arithmetic, Greedy}, dss = Array)
public class PrimeNumber {
  public static void main (String[] args) {
    PrimeNumber ins = new PrimeNumber();
    //CollectionsUtil.printMap(ins.factorization((int) Math.pow(2, 31) - 1));

    for (int i = 2; i < 1000; i++) {
      Map<Integer, Integer> factors = ins.factorization(i);
      System.out.println(i + " " + ins.sumOfFactors(factors) + " " + ins.sumOfPrimeFactors(factors));
    }
  }

  List<Integer> allFactors(Map<Integer, Integer> factors) {
    List<Integer> factor = new LinkedList<>();
    List<Integer> exponential = new LinkedList<>();
    for (Integer f : factors.keySet()) {
      factor.add(f);
      exponential.add(factors.get(f));
    }
    System.out.println(factor + " " + exponential);
    return bottomUpRecursion(factor, exponential, factors.size());
  }

  List<Integer> bottomUpRecursion(List<Integer> factor, List<Integer> exponential, int i) {
    List<Integer> ret = new LinkedList<>();
    if (i == 0) {
      ret.add(1);
      return ret;
    }
    for (Integer f : bottomUpRecursion(factor, exponential, i - 1)) {
      for (int j = 0; j <= exponential.get(i - 1); j++) {
        ret.add(f * (int) Math.pow(factor.get(i - 1), j));
      }
    }
    return ret;
  }

  // Given n, return the primes from 1 to n.
  List<Integer> allPrimesSmallerThan(int n) {

    // we do not need to sieve even numbers
    // so isPrime[i] represents (2i + 3) is prime or not.
    List<Integer> primes = new ArrayList<>(); // stores the primes from 1 to n.
    if (n < 2) {
      return primes;
    }
    primes.add(2);
    int indexMax = (n - 3) >> 1;
    boolean isPrime[] = new boolean[indexMax + 1];
    Arrays.fill(isPrime, true);
    for (int i = 0; i <= indexMax; i++) {
      if (isPrime[i]) {
        int p = (i << 1) + 3;
        primes.add(p);
        // Sieving from p^2, whose index is 2i^2 + 6i + 3
        // index increase by p since even numbers are skipped
        //for (int j = ((i * i) << 1) + 6 * i + 3; j <= indexMax; j += p) {
        for (int j = p * p; j <= n; j += 2 * p) {
          isPrime[(j - 3) >> 1] = false;
        }
      }
    }
    return primes;
  }

  Map<Integer, Integer> factorization(int n) {
    Map<Integer, Integer> factors = new HashMap<>();
    for (int i = 2; i <= n; i++) {
      if (i > 2 && (i & 1) == 0) {
        continue;
      }
      int cnt = 0;
      while (n % i == 0) {
        n /= i;
        cnt++;
      }
      if (cnt > 0) {
        CollectionsUtil.increaseMapCounter(factors, i, cnt);
        //CollectionsUtil.printMap(factors);
      }
    }
    return factors;
  }

  int numOfFactors(Map<Integer, Integer> factors) {
    int noOfFactors = 1;
    for (int factor : factors.keySet()) {
      noOfFactors *= factors.get(factor) + 1;
    }
    return noOfFactors;
  }

  int sumOfFactors(Map<Integer, Integer> factors) {
    int sumOfFactors = 1;
    for (int factor: factors.keySet()) {
      sumOfFactors *= (Math.pow(factor, factors.get(factor) + 1) - 1) / (factor - 1);
    }
    return sumOfFactors;
  }

  int sumOfPrimeFactors(Map<Integer, Integer> factors) {
    int sumOfPrimeFactors = 0;
    for (int factor: factors.keySet()) {
      sumOfPrimeFactors += factor;
    }
    return sumOfPrimeFactors;
  }

  /** Twin numbers are a pair of numbers that the sum of one's prime factors equals to the other. */
  List<List<Integer>> twinNumbersSmallerThan(int n) {
    List<List<Integer>> twins = new LinkedList<>();
    for (int i = 2; i <= n; i++) {
      for (int j = i; j <= n; j++) {
        if (sumOfPrimeFactors(factorization(i)) == j && sumOfPrimeFactors(factorization(j)) == i) {
          List<Integer> twin = new ArrayList<>(2);
          twin.add(i);
          twin.add(j);
          twins.add(twin);
        }
      }
    }
    return twins;
  }
}
