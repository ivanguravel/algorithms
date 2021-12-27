package org.ivzh.median;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FindMedianFromDataStream {

    private static final double RADIX = 0.0;

    private final PriorityQueue<Integer> left;
    private final PriorityQueue<Integer> right;

    public FindMedianFromDataStream() {
        this.left = new PriorityQueue<>(Comparator.reverseOrder());
        this.right = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if(left.isEmpty() || num < left.peek()){
            left.add(num);
        } else {
            right.add(num);
        }

        if (left.size() >= right.size() +1){
            right.add(left.poll());
        }

        if (right.size() >= left.size() +1){
            left.add(right.poll());
        }
    }

    public double findMedian() {
        if (left.size() == right.size()) {
            int peeksSum = left.peek() + right.peek();
            return (peeksSum + RADIX) / 2;
        } else if (right.size() > left.size()) {
            return right.peek();
        } else {
            return left.peek();
        }
    }
}
