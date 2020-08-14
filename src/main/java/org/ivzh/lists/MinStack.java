package org.ivzh.lists;

import java.util.LinkedList;


// TODO
// https://leetcode.com/problems/min-stack/
class MinStack {
    
    LinkedList<Integer> list = new LinkedList<>();
    Integer minimum = Integer.MAX_VALUE;
    
     /** initialize your data structure here. */
    public MinStack() {

    }
    
    public void push(int x) {
        if (x <= minimum) {
            minimum = x;
        }
        list.add(x);
    }

    public void pop() {
        Integer last = list.getLast();
        list.removeLast();
        if (minimum.equals(last)) {
            minimum = Integer.MAX_VALUE;
            setMin();
        }
    }

    public int top() {
        return list.getLast();
    }

    public int getMin() {
        return minimum;
    }

    private void setMin() {
        for (Integer i : list) {
            if (i < minimum) {
                minimum = i;
            }
        }
    }
}
