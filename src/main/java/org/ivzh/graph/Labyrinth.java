package org.ivzh.graph;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// https://acm.timus.ru/problem.aspx?space=1&num=1033
public class Labyrinth {

    int n = 0;
    char[][] map = new char[40][40];
    boolean[][] visited = new boolean[40][40];
    int tox[] = {1,0,-1,0};
    int toy[] = {0,1,0,-1};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new Labyrinth().solve(in, out);

        out.flush();
    }

    private void solve(Scanner in, PrintWriter out) {
        readInput(in, out);

        // as per conditions
        map[0][1] = map[1][0] = map[n][n+1] = map[n+1][n] = '!';

        int result = 9*( bfs(2,2) + bfs(n, n));

        System.out.println(result);
    }

    int bfs(int x,int y) {
        if(visited[x][y]) return 0;

        Queue<Integer> quex = new LinkedList<>();
        Queue<Integer> quey = new LinkedList<>();
        quex.add(x); quey.add(y);
        visited[x][y] = true;
        int ans = 0;
        while(!quex.isEmpty())
        {
            Integer nowx = quex.poll();
            Integer nowy = quey.poll();
            // look at the 4 walls
            for(int i = 0; i < 4; i ++)
            {
                int nextx = nowx + tox[i];
                int nexty = nowy + toy[i];
                if(!visited[nextx][nexty] && map[nextx][nexty]=='.')
                {
                    visited[nextx][nexty] = true;
                    quex.add(nextx);
                    quey.add(nexty);
                }
                else if(map[nextx][nexty] == '#') ans ++;
            }
        }
        return ans;
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
    }
}
