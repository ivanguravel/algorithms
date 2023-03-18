package org.ivzh.intervals;

import java.util.Arrays;
import java.util.LinkedList;


// https://leetcode.com/problems/merge-intervals/
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (one, two) -> one[0] - two[0]);
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        int[][] result = new int[merged.size()][2];
        int count =0;
        for (int[] i : merged) {
            result[count++] = i;
        }
        return result;
    }
}
