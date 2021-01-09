/*
  Given a set of non-overlapping intervals, insert a new interval into the intervals
  (merge if necessary).
  You may assume that the intervals were initially sorted according to their start times.

  Example 1:

  Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
  Output: [[1,5],[6,9]]
  Example 2:

  Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
  Output: [[1,2],[3,10],[12,16]]
  Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
  Example 3:

  Input: intervals = [], newInterval = [5,7]
  Output: [[5,7]]
  Example 4:

  Input: intervals = [[1,5]], newInterval = [2,3]
  Output: [[1,5]]
  Example 5:

  Input: intervals = [[1,5]], newInterval = [2,7]
  Output: [[1,7]]
 */
package com.sma.alg;

import com.sma.annotation.Tag;
import com.sma.ds.misc.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.sma.annotation.Tag.DataStructure.IntervalT;
import static com.sma.annotation.Tag.Reference.LeetCode;

@Tag(dss = IntervalT, references = LeetCode)
public class InsertInterval {
  //second pass
  public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    List<Interval> ret = new ArrayList<>();

    if (newInterval == null)
      return intervals;
    if (intervals.size() == 0) {
      ret.add(newInterval);
      return ret;
    }

    //insert the newInterval to the right position using the start time
    int i;
    for (i = 0; i < intervals.size(); i++) {
      if (intervals.get(i).start >= newInterval.start)
        break;
    }
    intervals.add(i, newInterval);

    // merge intervals if necessary
    Interval open = intervals.get(0);
    for (int j = 1; j < intervals.size(); j++) {
      Interval next = intervals.get(j);
      if (next.start > open.end) {
        ret.add(open);
        open = next;
      } else {
        open.end = Math.max(open.end, next.end);
      }
    }
    ret.add(open);
    return ret;
  }

  //fist pass
  public ArrayList<Interval> insert1(ArrayList<Interval> intervals, Interval newInterval) {
    int i;
    for (i = 0; i < intervals.size(); i++) {
      if (intervals.get(i).start >= newInterval.start)
        break;
    }
    intervals.add(i, newInterval);

    int n = intervals.size();
    if (n < 2)
      return intervals;
    Collections.sort(intervals, new CustomComparator());

    Interval openInt = intervals.get(0);
    ArrayList<Interval> res = new ArrayList<>();

    for (i = 1; i < n; i++) {
      Interval cur = intervals.get(i);
      if (openInt.end < cur.start) {
        res.add(openInt);
        openInt = cur;
      } else {
        if (openInt.end < cur.end) {
          openInt.end = cur.end;
        }
      }
    }
    res.add(openInt);

    return res;
  }

  static public class CustomComparator implements Comparator<Interval> {
    @Override public int compare(Interval o1, Interval o2) {
      return o1.start - o2.start;
    }
  }
}
