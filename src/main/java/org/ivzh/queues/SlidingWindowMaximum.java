package org.ivzh.queues;


import java.util.ArrayDeque;

// https://leetcode.com/problems/sliding-window-maximum
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] arr, int windowSize) {
        int j=0;
        int[] result = new int[arr.length - windowSize + 1];
        int count = 0;
        ArrayDeque<Integer> deq=new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {

            // if elements in queue are smaller than current element in array we should drop each of them
            while(!deq.isEmpty() && deq.getLast()<arr[i]){
                deq.removeLast();
            }
            // add last as current minimum in window
            deq.addLast(arr[i]);


            if(i-j+1 >= windowSize) {
                // if we're bigger then window size , then we need to start printing window minimums
                result[count++] = deq.getFirst();

                // after printing current minimum , we just going to drop it as already used and increase window low index
                if(deq.getFirst()==arr[j]){
                    deq.removeFirst();
                }
                j++;
            }
        }
        return result;
    }
}
