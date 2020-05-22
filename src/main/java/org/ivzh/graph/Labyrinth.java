package org.ivzh.graph;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// https://acm.timus.ru/problem.aspx?space=1&num=1033
public class Labyrinth {

    int n = 0;
    char[][] map = new char[33][33];
    boolean[][] visited = new boolean[33][33];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new Labyrinth().solve(in, out);

        out.flush();
    }

    private void solve(Scanner in, PrintWriter out) {
        readInput(in, out);

        Queue<Character> q = new LinkedList<>();

        for (int i =0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                q.add(map[i][j]);
            }
        }

        int answer = 0;
        while (!q.isEmpty()) {
            Character poll = q.poll();
            if (poll == '#') {
                ++answer;
            }
        }

        System.out.println(answer * 9);
    }

    private void readInput(Scanner in, PrintWriter out) {
        this.n = in.nextInt();
        in.nextLine();
        for (int i =0; i < n; i++) {
            String s = in.nextLine();
            char[] ch = s.toCharArray();
            for (int j = 0; j < n; j++) {
                map[i][j] = ch[j];
            }
        }
        System.out.println(map);
    }
}
