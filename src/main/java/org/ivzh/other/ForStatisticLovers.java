package org.ivzh.other;

import java.io.PrintWriter;
import java.util.*;


// https://acm.timus.ru/problem.aspx?space=1&num=1613&locale=ru
public class ForStatisticLovers {

    int n;
    Map<Long, Set<Integer>> queryContainer = new HashMap<>();
    int q;
    List<QueryRequest> queries = new LinkedList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new ForStatisticLovers().solve(in, out);
    }

    private void solve(Scanner in, PrintWriter out) {
        readData(in);
        StringBuilder builder = new StringBuilder();
        Set<Integer> set;
        for (QueryRequest request : queries) {
            set = queryContainer.get(request.count);
            if (set == null) {
                builder.append("0");
                continue;
            }
            boolean result = false;
            for (int i = request.from; i <= request.to; i++) {
                if (set.contains(i)) {
                    result = true;
                    break;
                }
            }
            builder.append(result ? "1" : "0");
        }
        out.println(builder.toString());
        out.flush();
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
        this.q = in.nextInt();
        count = 1;
        while (count <= q) {
            queries.add(new QueryRequest(in.nextInt(), in.nextInt(), in.nextLong()));
            ++count;
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
