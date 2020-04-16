package org.ivzh.dynamic.programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.StringTokenizer;
import java.math.BigDecimal;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

// https://acm.timus.ru/problem.aspx?space=1&num=1119
public class Metro {


    private static final double HYPOTENUSE = 141.42;

        private void solve() {

            int n = nextInt();
            int m = nextInt();

            int[][] matrix = new int[n][m];

            int crossLines = nextInt();

            int[][] crossLinesNumbers = new int[crossLines][m];

            for (int i = 0; i < crossLines; i++) {
                for (int j = 0; j < m; j++) {
                    crossLinesNumbers[i][j] = nextInt();
                }
            }

            int[][][] dp = new int[n+2][m+2][6];

            dp[1][1][0] = 100;
            dp[1][1][1] = 100;
            dp[1][1][2] = 100;
            dp[1][1][3] = 100;

            dp[1][1][4] = isCrossLine(crossLines, m, crossLinesNumbers, 1, 1) ? (int) HYPOTENUSE : -1;

            dp[1][1][5] = min(dp[1][1][0] + dp[1][1][1], dp[1][1][2] + dp[1][1][3]);
            dp[1][1][5] = min(dp[1][1][5], dp[1][1][4]);

            for (int i = 2; i < n; i++) {
                for (int j =2; j < m; j++) {
                    dp[i][j][0] = dp[i - 1][j - 1][0];
                    dp[i][j][1] = dp[i - 1][j - 1][1];
                    dp[i][j][2] = dp[i - 1][j - 1][2];
                    dp[i][j][3] = dp[i - 1][j - 1][3];

                    int crossLine = 0;//isCrossLine();

                    //
                    int firstMin = min(dp[i-1][j-1][5] + crossLine, dp[i-1][j][5] + 100);
                    dp[i][j][5] =  + min(firstMin, dp[i][j-1][5] + 100);

                }
            }
            sout(dp[n][m][5]);
         }

         private static boolean isCrossLine(int crossLines, int m, int[][] crossLinesNumbers, int x, int y) {
             for (int i = 0; i < crossLines; i++) {
                 for (int j = 0; j < m; j++) {
                     if (crossLinesNumbers[i][j] == x && crossLinesNumbers[i][j] == y) {
                         return true;
                     }
                 }
             }
             return false;
         }



        public static void main(String[] args) {
            new Metro().run();
        }

        private BufferedReader reader;
        private StringTokenizer tokenizer;
        private PrintWriter writer;

        private void run() {
            try {
                reader = new BufferedReader(new InputStreamReader(System.in));
                tokenizer = null;
                writer = new PrintWriter(System.out);
                solve();
                reader.close();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        private int nextInt() {
            return parseInt(nextToken());
        }

        private long nextLong() {
            return parseLong(nextToken());
        }

        private double nextDouble() {
            return parseDouble(nextToken());
        }

        private int[] nextArr(int size) {
            return stream(new int[size]).map(c -> nextInt()).toArray();
        }

        private long[] nextArrL(int size) {
            return stream(new long[size]).map(c -> nextLong()).toArray();
        }

        private double[] nextArrD(int size) {
            return stream(new double[size]).map(c -> nextDouble()).toArray();
        }

        private char[][] nextCharMatrix(int n) {
            return range(0, n).mapToObj(i -> nextToken().toCharArray()).toArray(char[][]::new);
        }

        private int[][] nextIntMatrix(final int n, final int m) {
            return range(0, n).mapToObj(i -> nextArr(m)).toArray(int[][]::new);
        }

        private double[][] nextDoubleMatrix(int n, int m) {
            return range(0, n).mapToObj(i -> nextArr(m)).toArray(double[][]::new);
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

        private void souf(String format, Object... args) {
            writer.printf(format, args);
        }

        private void sout(Object o) {
            writer.print(o);
        }

        private void newLine() {
            writer.println();
        }

        private void soutnl(Object o) {
            sout(o);
            newLine();
        }

        private int max(int a, int b) {
            return Math.max(a, b);
        }

        private int min(int a, int b) {
            return Math.min(a, b);
        }


}
