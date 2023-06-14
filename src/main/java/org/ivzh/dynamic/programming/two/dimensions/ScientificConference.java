package org.ivzh.dynamic.programming.two.dimensions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class ScientificConference {


    public static void main(String[] args) {
        TreeMap<Integer, Integer> schedule = new TreeMap<>();
        int n = 5;
        Integer[][] lectures = new Integer[n][2];

        lectures[0][0] = 3;
        lectures[0][1] = 4;

        lectures[1][0] = 1;
        lectures[1][1] = 5;

        lectures[2][0] = 6;
        lectures[2][1] = 7;

        lectures[3][0] = 4;
        lectures[3][1] = 5;

        lectures[4][0] = 1;
        lectures[4][1] = 3;

//        Comparator<Integer[]> comparator = (entry1, entry2) -> {
//            if (entry1[0].equals(entry2[0])) {
//                return entry2[1] - entry1[1];
//            }
//            return entry2[0] - entry1[0];
//        };
//
//        Arrays.sort(lectures, comparator.reversed());

        Integer start;
        Integer end;
        for (int i =0; i < n; i++ ) {
            start = lectures[i][0];
            end = lectures[i][1];

            Integer prev = schedule.floorKey(start), next = schedule.ceilingKey(start);

            if ((prev == null || schedule.get(prev) < start) && (next == null || schedule.get(next) < end)) {
                schedule.put(start, end);
            }
        }

        System.out.println(schedule.size());
    }
}
