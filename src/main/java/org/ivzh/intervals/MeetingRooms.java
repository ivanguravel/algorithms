package org.ivzh.intervals;


import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/meeting-rooms
public class MeetingRooms {

    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        int[] prev = null;
        for (int[] interval : intervals) {
            if (prev == null) {
                prev = interval;
            } else {
                if (prev[1] >interval[0]) {
                    return false;
                }
                prev = interval;
            }
        }

        return true;
    }
}
