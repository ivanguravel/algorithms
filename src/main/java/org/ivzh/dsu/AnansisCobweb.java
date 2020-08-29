package org.ivzh.dsu;

import java.io.PrintWriter;
import java.util.*;

public class AnansisCobweb {
    int n;
    int m;
    int q;
    List<Tuple> vertexes;
    List<Integer> breaks;

    DSU dsu;

    Set<Integer> connectedComponentsAfterBreaks = new TreeSet<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new AnansisCobweb().solve(in, out);
        out.flush();
    }

    private void solve(Scanner in, PrintWriter out) {
        readData(in);

        for (Tuple vertex : vertexes) {
            if (!vertex.isBreakPresent) {
                dsu.join(vertex.one, vertex.two);
            }
        }

        Tuple tuple;
        for (int i = q -1; i >=0; i--) {
            this.connectedComponentsAfterBreaks.add(this.dsu.count);
            tuple = this.vertexes.get(this.breaks.get(i));
            this.dsu.join(tuple.one, tuple.two);
        }

        List<Integer> result = new ArrayList<>(this.connectedComponentsAfterBreaks);
        Collections.reverse(result);

        for (int i = result.size() -1; i>=0; i--) {
            out.print(String.format("%s ", result.get(i)));
        }
    }

    void readData(Scanner in) {
        this.n = in.nextInt();
        this.m = in.nextInt();

        this.dsu = new DSU(n);
        this.vertexes = new ArrayList<>(m+1);

        for (int i = 0; i < m; i++) {
            this.vertexes.add(new Tuple(in.nextInt(), in.nextInt()));
        }

        this.q = in.nextInt();

        this.breaks = new ArrayList<>(q+1);

        for(int i=0; i < q; i++) {
            breaks.add(in.nextInt() -1);
            this.vertexes.get(breaks.get(i)).isBreakPresent = true;
        }
        this.connectedComponentsAfterBreaks = new HashSet<>();
    }

    static class Tuple {
        int one;
        int two;
        Boolean isBreakPresent = false;

        public Tuple(int one, int two) {
            this.one = one;
            this.two = two;
        }
    }


    static class DSU {
        int[] parents = new int[10_0001];
        int[] dsuSize = new int[10_0001];

        int count;

        public DSU(int n) {
            for (int i = 0; i <= n; i++) {
                dsuSize[i] = 1;
                parents[i] = i;
            }
            this.count = n;
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

        private void swap(Integer a, Integer b) {
            int temp = a;
            a = b;
            b = temp;
        }
    }
}
