package org.ivzh.queues;

import java.util.Stack;

public class QueueVia2Stacks {


    public static void main(String[] args) {
        QueueVia2Stacks queue = new QueueVia2Stacks();

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

    Stack<Integer> s1, s2;

    public QueueVia2Stacks() {
        this.s1 = new Stack<>();
        this.s2 = new Stack<>();
    }

    public int poll() {
        return s2.pop();
    }

    public void offer(int v) {
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }

        s1.push(v);

        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
    }
}
