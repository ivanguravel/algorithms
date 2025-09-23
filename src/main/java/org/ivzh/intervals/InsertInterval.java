package org.ivzh.intervals;

import java.util.*;


// https://leetcode.com/problems/insert-interval/description/
public class MergeIntervals {

   // intervals array is sorted
  public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int i =0;
        LinkedList<int[]> res = new LinkedList<>();

        // add all intervals which are BEFORE newInterval and they are non-overlapping with newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]);
            i++;
        }

       // merge all overlaping intervals with newInterval. New interval contains all the intervals which are inside the same range as newInterval
        while (i < n && newInterval[1] >= intervals[i][0]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        // add newInterval
        res.add(newInterval);

       // add all intervals which are AFTER the newInterval and they are non-merged.
        while (i < n) {
            res.add(intervals[i++]);
        }
        
        return res.toArray(new int[res.size()][]);
    }
}
