package org.ivzh.sort;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Customs {

    int n;
    int k;
    long mxw;
    long mxp;
    long a;
    double b;
    List<Pair> holder;
    List<CustomInformationHolder> info;
    List<CustomInformationHolder> sortedInfo;

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
        provisionData();

        // change k places

        println(info.stream().map(i -> i.calculatedTax).reduce(Long::sum).get());

        int i =0;
        while (n-- >= 1) {
            System.out.println(info.get(i++));
        }
    }

    private void readData() {
        this.n = nextInt();
        this.k = nextInt();
        this.mxw = nextLong();
        this.mxp = nextLong();

        this.a = nextLong();


        this.b = nextInt();

        this.holder = new ArrayList<>(n+1);

        for (int i = 0; i < n ; i++) {
            holder.add(new Pair(nextLong(), nextLong()));
        }
    }


    private void provisionData() {
        info = new ArrayList<>(n+1);

       // for (Map.Entry<Long, Long> e : holder.entrySet()) {
        //    info.add(new CustomInformationHolder(e.getKey(), e.getValue(), mxw, mxp, a, b));
        //}

        Collections.sort(info);
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

        private static final String id = UUID.randomUUID().toString();

        private static final char MAX_OCTET_NUMBER = '9';

        private long taxBeforeHack;
        private long taxAfterHack = Integer.MIN_VALUE;

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

            calculateTax(weight, price);
            calculateTaxAfterHack(weight, price);

            calculatedTax = Math.max(taxBeforeHack, taxAfterHack);

        }

        private void calculateTax(long weight, long price) {
            long tax = 0;

            if (weight > mxw) {
               long overweight =  weight - mxw;
               tax = tax + (overweight * a);
            }

            if (price > mxp) {
                long overprice = price - mxp;
                tax = tax + (overprice - mxp) * (a / 100);
            }



            this.taxBeforeHack = tax;


        }


        private void calculateTaxAfterHack(long weight, long price) {
            long hackedWeight = changeNumberToBigger(weight);
            long hackedPrice = changeNumberToBigger(price);


//            if () {
//
//            } else {
//
//            }

            taxAfterHack = Math.max(hackedWeight - weight, hackedPrice - price);
        }

        private long changeNumberToBigger(long n) {
            String s = Long.toString(n);
            int c =0;
            for (char ch : s.toCharArray()) {

                if (ch != MAX_OCTET_NUMBER ) {
                   break;
                }
                ++c;
             }
            StringBuilder builder = new StringBuilder(s);
            builder.setCharAt(c, MAX_OCTET_NUMBER);

            return Long.parseLong(builder.toString());
        }

        @Override
        public int compareTo(CustomInformationHolder o) {
            return Long.compare(this.taxAfterHack, o.taxAfterHack);
        }
    }

    static class Pair {
        long weight;
        long price;

        public Pair(long weight, long price) {
            this.weight = weight;
            this.price = price;
        }
    }

}
