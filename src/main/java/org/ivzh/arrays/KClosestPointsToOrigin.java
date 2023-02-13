package org.ivzh.arrays;

import java.util.PriorityQueue;


// https://leetcode.com/problems/k-closest-points-to-origin/description/
public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Point> q = new PriorityQueue<>();
        for (int[] p : points) {
            q.add(new Point(p[0], p[1]));
        }

        int[][] result = new int[k][2];
        int count = 0;

        Point p;
        while (k-- > 0) {
            p = q.poll();
            int[] arr = new int[2];
            arr[0] = p.x;
            arr[1] = p.y;
            result[count++] = arr;
        }

        return result;
    }

    static class Point implements Comparable<Point>{
        public int x, y;
        double distance;

        public Point(int x, int y) {
            this.x = x;
            this.y=y;

            double pow = Math.pow(x, 2) + Math.pow(y, 2);
            this.distance = Math.sqrt(pow);
        }

        public int compareTo(Point p) {
            return Double.compare(this.distance, p.distance);
        }
    }
}
