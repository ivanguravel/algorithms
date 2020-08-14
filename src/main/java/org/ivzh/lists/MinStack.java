package org.ivzh.lists;

import java.util.LinkedList;
import java.util.List;


// TODO
// https://leetcode.com/problems/min-stack/
public class MinStack {

    List<Integer> list = new LinkedList();
    Integer minimum = Integer.MAX_VALUE;
    Integer top;

    /** initialize your data structure here. */
    public MinStack() {

    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();

        minStack.push(2);
        minStack.push(0);
        minStack.push(3);
        minStack.push(0);

        System.out.println(minStack.getMin());
        minStack.pop();

        System.out.println(minStack.getMin());
        minStack.pop();

        System.out.println(minStack.getMin());
        minStack.pop();
    }

    public void push(int x) {
        if (x < minimum) {
            minimum = x;
        }
        list.add(x);
        top = x;
    }

    public void pop() {
        list.remove(top);
        minimum = Integer.MAX_VALUE;
        top = list.get(list.size() -1);
        setMin();
    }

    public int top() {
        return top;
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
