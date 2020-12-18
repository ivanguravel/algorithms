package org.ivzh.other;

import java.io.PrintWriter;
import java.util.*;


// https://acm.timus.ru/problem.aspx?space=1&num=1613&locale=ru
public class ForStatisticLovers {

    int n;
    Map<Long, Set<Integer>> queryContainer = new HashMap<>();
    int m;
    List<QueryRequest> queries = new LinkedList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new ForStatisticLovers().solve(in, out);
    }

    private void solve(Scanner in, PrintWriter out) {
        readData(in);
    }

    private void readData(Scanner in) {
        this.n = in.nextInt();
        int count = 1;
        Set<Integer> cities;
        while (count <= n) {
            long value = in.nextLong();
            cities = queryContainer.get(value);
            if (cities == null) {
                cities = new HashSet<>();
            }
            cities.add(count++);
            queryContainer.put(value, cities);
        }
    }


    static class QueryRequest {
        int from;
        int to;
        long count;

        public QueryRequest(int from, int to, long count) {
            this.from = from;
            this.to = to;
            this.count = count;
        }
    }
}
