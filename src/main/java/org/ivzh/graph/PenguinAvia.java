package org.ivzh.graph;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;


// https://acm.timus.ru/problem.aspx?space=1&num=1709
public class PenguinAvia {

    int n, d, a;
    int[][] graph;


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new PenguinAvia().solve(in, out);
    }

    void solve(Scanner in, PrintWriter out) {
        readData(in);
        Deque<Integer> q = new ArrayDeque<>();


        while (!q.isEmpty()) {

        }
    }

    private void readData(Scanner in) {
        this.n= in.nextInt();
        this.d= in.nextInt();
        this.a= in.nextInt();

        this.graph = new int[n+1][n+1];

        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                graph[i][j] = in.nextInt();
            }
        }
    }

}
