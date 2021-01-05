package org.ivzh.sort;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Customs {

    int n;
    int k;
    long mxw;
    long mxp;
    long a;
    int b;
    Map<Long, Long> holder;

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new Customs().run();
    }

    private void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out))) {
            this.reader = reader;
            this.writer = writer;
            this.tokenizer = null;
            solve();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void solve() {
        readData();
    }

    private void readData() {
        this.n = nextInt();
        this.k = nextInt();
        this.mxw = nextLong();
        this.mxp = nextLong();

        this.a = nextLong();


        this.b = nextInt();

        this.holder = new HashMap<>();

        for (int i = 0; i < n ; i++) {
            holder.put(nextLong(), nextLong());
        }
    }


    private void provisionData() {

    }

    private long nextLong() {
        return parseLong(nextToken());
    }

    private int nextInt() {
        return parseInt(nextToken());
    }

    private String nextToken() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    private void println(Object o) {
        print(o);
        println();
    }

    private void print(Object o) {
        writer.print(o);
    }

    private void flush() {
        writer.flush();
    }

    private void println() {
        writer.println();
    }

    static class CustomInformationHolder implements Comparable<CustomInformationHolder> {

        private static final int MAX_OCTET_NUMBER = 9;

        private long taxBeforeHack;
        private long taxAfterHack;

        private long calculatedTax;


        long mxw;
        long mxp;
        long a;
        int b;

        public CustomInformationHolder(long weight, long price, long mxw, long mxp, long a, int b) {
            this.mxp = mxp;
            this.mxw = mxw;
            this.a = a;
            this.b = b;
        }

        private void calculateTaxBeforeHack(long weight, long price, long mxw, long mxp) {
            long tax = 0;

            if (weight > mxw) {
                //tax = tax + ()
            }
        }

        @Override
        public int compareTo(CustomInformationHolder o) {
            return Long.compare(this.calculatedTax, o.calculatedTax);
        }
    }

}
