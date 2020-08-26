package org.ivzh.dsu;


import java.io.PrintWriter;
import java.util.*;

// https://acm.timus.ru/problem.aspx?space=1&num=1160&locale=ru
public class Network {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new Network().solve(in, out);

        out.flush();
    }

    private void solve(Scanner in, PrintWriter out) {

        int n = in.nextInt();
        int m = in.nextInt();

        DSU dsu = new DSU(n);

        List<Tuple> container = new LinkedList<>();

        for (int i = 0; i < m; i++) {

            int x = in.nextInt();
            int y = in.nextInt();
            int z = in.nextInt();

            container.add(new Tuple(x, y, z));
        }

        Collections.sort(container, Comparator.comparingInt(o -> o.three));

        int maxEdgeSize = Integer.MIN_VALUE;

        int nConnectionSize = 0;

        for (Tuple e : container) {

            dsu.join(e.one, e.two);
            ++nConnectionSize;
            if (dsu.count == 0) {
                maxEdgeSize = e.three;
                break;
            }
        }

        out.println(maxEdgeSize);
        out.println(nConnectionSize);

        for (Tuple e : container) {
            out.println(String.format("%s %s", e.one, e.two));
            --nConnectionSize;
            if (nConnectionSize == 0) {
                break;
            }
        }

        out.flush();
    }


    static class Tuple {
        int one;
        int two;
        int three;

        public Tuple(int one, int two, int three) {
            this.one = one;
            this.two = two;
            this.three = three;
        }
    }


    static class DSU {
        int[] parents = new int[1002];
        int[] dsuSize = new int[1002];

        int count;

        public DSU(int n) {
            for (int i = 0; i <= n; i++) {
                dsuSize[i] = 1;
                parents[i] = i;
            }
            this.count = n - 1;
        }

        public void join(int a, int b) {
            int parentA = get(a);
            int parentB = get(b);

            if (parentA == parentB) {
                return;
            }

            if (dsuSize[parentA] > dsuSize[parentB]) {
                swap(parentA, parentB);
            }


            dsuSize[parentB] = dsuSize[parentB] + dsuSize[parentA];

            parents[parentA] = parentB;
            --this.count;
        }

        public int get(int vertex) {
            int parent = parents[vertex];
            if (vertex == parent) {
                return vertex;
            } else {
                return parents[vertex] = get(parent);
            }
        }

        private void swap(int a, int b) {
            int temp = a;
            a = b;
            b = temp;
        }
    }
}
