package org.ivzh.graph;
import java.io.PrintWriter;
import java.util.*;

// https://acm.timus.ru/problem.aspx?space=1&num=1033
public class Labyrinth {

    int n = 0;
    char[][] graph = new char[33][33];
    boolean[][] visited = new boolean[33][33];

    Deque<Integer> qx = new ArrayDeque<>();
    Deque<Integer> qy = new ArrayDeque<>();

    int enterOne[] = {1,0,1,0};
    int enterTwo[] = {0,1,0,1};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new Labyrinth().solve(in, out);

        out.flush();
    }

    private void solve(Scanner in, PrintWriter out) {
        readInput(in, out);

        int result = 9*(bfs(1, 1) + bfs(n -1 , n - 1));

        System.out.println(result);
    }

    int bfs(int x,int y) {
        if(visited[x][y]) {
            return 0;
        }

        qx.add(x);
        qy.add(y);
        visited[x][y] = true;
        int ans = 0;
        while(!qx.isEmpty())
        {
            Integer currentX = qx.pollLast();
            Integer currentY = qy.pollLast();
            // look at the 4 walls
            for(int i = 0; i < 4; i ++)
            {
                int nextx = currentX + enterOne[i];
                int nexty = currentY + enterTwo[i];
                if(!visited[nextx][nexty] && graph[nextx][nexty]=='.') {
                    visited[nextx][nexty] = true;
                    qx.add(nextx);
                    qy.add(nexty);
                }
                if(graph[nextx][nexty] == '#') {
                    ans++;
                }
            }
        }
        // because of 4 walls around #
        return ans;
    }

    private void readInput(Scanner in, PrintWriter out) {
        fillGraph();
        fillVisited();
        this.n = in.nextInt();
        in.nextLine();
        for (int i =0; i < n; i++) {
            String s = in.nextLine();
            char[] ch = s.toCharArray();
            for (int j = 0; j < n; j++) {
                graph[i][j] = ch[j];
            }
        }
        // as per conditions
        graph[0][1] = graph[1][0] = graph[n][n+1] = graph[n+1][n] = '!';
    }

    private void fillGraph() {
        for (char[] ch : graph) {
            // because of all filled in outside
            Arrays.fill(ch, '#');
        }
    }

    private void fillVisited() {
        for (boolean[] b : visited) {
            Arrays.fill(b, false);
        }
    }
}
