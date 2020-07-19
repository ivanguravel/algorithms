package org.ivzh.queues;


import java.util.*;

// https://leetcode.com/problems/queue-reconstruction-by-height/
public class QueueReconstructionbyHeight {

    public static void main(String[] args) {
        int[][] queue = new int[6][2];

        queue[0][0] = 7;
        queue[0][1] = 0;

        queue[1][0] = 4;
        queue[1][1] = 4;

        queue[2][0] = 7;
        queue[2][1] = 1;

        queue[3][0] = 5;
        queue[3][1] = 0;

        queue[4][0] = 6;
        queue[4][1] = 1;

        queue[5][0] = 5;
        queue[5][1] = 2;


       int[][] ans =  new QueueReconstructionbyHeight().reconstructQueue(queue);

       for (int[] a : ans) {
           System.out.print("[" + a[0] + "-" + a[1] + "] ");
       }
    }

    public int[][] reconstructQueue(int[][] queue) {
        Queue<Person> personQueue = new PriorityQueue<>(queue.length + 1);

        Person person;
        for (int i = 0; i < queue.length; i++) {
            person = new Person(queue[i][0], queue[i][1]);
            personQueue.add(person);
        }
        List<int[]> answer = new ArrayList<>(10_000);
        Person p;
        while (!personQueue.isEmpty()) {
            p = personQueue.poll();
            int[] filled = new int[2];
            filled[0] = p.h;
            filled[1] = p.k;
            answer.add(p.k, filled);
        }
        return answer.toArray(new int[answer.size()][2]);
    }


    static class Person implements Comparable<Person> {
        int h;
        int k;

        public Person(int h, int k) {
            this.h = h;
            this.k = k;
        }

        @Override
        public int compareTo(Person o) {
            if (this.h == o.h) {
                return Integer.compare(this.k, o.k);
            } else {
                return Integer.compare(o.h, this.h);
            }
        }
    }
}
