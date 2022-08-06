package org.ivzh.queues; 

import java.util.*;


// https://leetcode.com/problems/last-stone-weight/
public class LastStoneWeight {
   public int lastStoneWeight(int[] stones) {
        if (stones.length == 0) {
            return 0;
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i : stones) {
            pq.add(i);
        }
        
        int loopIterationsCount = stones.length % 2 == 0 ? (stones.length / 2) : (stones.length /2 - 1);
        
        while (pq.size() > 1) {
            Integer first = pq.poll();
            Integer second = pq.poll();
            
            if (first == null || second == null) {
                break;
            }
            
            if (first == second) {
                continue;
            } else if (first > second) {
                pq.add(first - second);
            } else {
                pq.add(second - first);
            }
        }
        
        return pq.isEmpty() ? 0 : pq.poll();
    }

}
