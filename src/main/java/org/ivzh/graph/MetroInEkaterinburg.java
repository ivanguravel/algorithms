package org.ivzh.graph;

import java.io.PrintWriter;
import java.util.*;

// https://acm.timus.ru/problem.aspx?space=1&num=1272
public class MetroInEkaterinburg {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new MetroInEkaterinburg().solve(in, out);

        out.flush();
    }

    class Island {
        List<Island> tunnels = new LinkedList<Island>();
        List<Island> bridges = new LinkedList<Island>();
        boolean visited;

        List<Island> getList(boolean bridges) {
            return bridges ? this.bridges : this.tunnels;
        }
    }

    Island[] islands;

    void readEdges(Scanner in, int count, boolean bridges) {
        for (int i = 0; i < count; i++) {
            Island land1 = islands[in.nextInt() - 1];
            Island land2 = islands[in.nextInt() - 1];
            land1.getList(bridges).add(land2);
            land2.getList(bridges).add(land1);
        }
    }

    void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int m = in.nextInt();

        islands = new Island[n];
        for (int i = 0; i < n; i++) {
            islands[i] = new Island();
        }

        readEdges(in, k, false);
       // readEdges(in, m, true);

        int bridgesUsed = 0;


        out.println(bridgesUsed);
    }
}
