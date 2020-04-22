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
        List<Island> neibors = new LinkedList<>();
        int num;

        public Island(int num) {
            this.num = num;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Island island = (Island) o;
            return num == island.num;
        }

        @Override
        public int hashCode() {
            return Objects.hash(num);
        }
    }

    Island[] islands;

    void readEdges(Scanner in, int count, Map<Island, Island> tunnelss) {
        for (int i = 0; i < count; i++) {
            int one = in.nextInt();
            int two = in.nextInt();
            tunnelss.put(new Island(one), new Island(two));
        }
    }

    void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int m = in.nextInt();

        Map<Island, Island> tunnels = new HashMap<>();


        readEdges(in, k, tunnels);

        int bridgesUsed = 0;





        out.println(bridgesUsed);
    }
}
