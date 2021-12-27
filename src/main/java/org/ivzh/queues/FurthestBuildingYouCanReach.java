package org.ivzh.queues;

import java.util.PriorityQueue;

// https://leetcode.com/problems/furthest-building-you-can-reach/
public class FurthestBuildingYouCanReach {

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue();
        int lenght = heights.length - 1;
        Integer currentHeight;
        for (int i = 0; i < lenght; i++) {
            currentHeight = heights[i + 1] - heights[i];

            if (currentHeight > 0) {
                priorityQueue.add(currentHeight);

                if (priorityQueue.size() > ladders) {
                    bricks = bricks - priorityQueue.poll();
                }

                if (bricks < 0) {
                    return i;
                }
            }
        }

        return lenght;
    }
}
