package org.ivzh.queues;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;


// https://algocademy.com/app/#problem/smallest-k
public class SmallestKIntegers {


    public static void main(String[] args) {
        int[] n = {5, 9, 3, 6, 2, 1, 3, 2, 7, 5};
        System.out.println(Arrays.toString(new SmallestKIntegers().kSmallest(n, 4)));
    }

    public int[] kSmallest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Integer::compareTo);
        for (int i : nums) {
            priorityQueue.add(i);
        }
        int[] result = new int[k];
        int count = 0;
        while (count < k) {
            result[count++] = priorityQueue.poll();
        }
        return result;
    }
}
