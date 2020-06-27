package org.ivzh.dynamic.programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;
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

    Scanner in;
    PrintWriter out;

    int n, m, crossLinesNumber;

    double[][] matrix;
    double[][] cache;
    boolean[][] crossLines;

    private void solve() {
        readData();


        double ans = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {

                double fromLowerCorner = matrix[i][j - 1] + 100.0;
                double fromPreviousLine = matrix[i - 1][j] + 100.0;

                double minimumWithoutHypotenuse = Math.min(fromLowerCorner, fromPreviousLine);

                if (crossLines[i][j]) {
                    double fromHypotenuse = matrix[i - 1][j - 1] + HYPOTENUSE ;
                    ans += Math.min(fromHypotenuse, minimumWithoutHypotenuse);
                } else {
                    ans += minimumWithoutHypotenuse;
                }
            }
        }
        out.println(ans / 2);
        out.flush();
    }


    private void readData() {

        in = new Scanner(System.in);
        out = new PrintWriter(System.out);

        n = in.nextInt();
        m = in.nextInt();
        crossLinesNumber = in.nextInt();

        matrix = new double[1005][1005];
        cache = new double[1005][1005];

        crossLines = new boolean[1001][1001];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                // every horizontal lines
                matrix[i][0] = i * 100;
                // every vertical lines
                matrix[0][j] = j * 100;
            }
        }



        in.nextLine();
        for (int i = 0; i < crossLinesNumber; i++) {
            String line = in.nextLine();
            String[] positions = line.split(" ");
            int positionOne = Integer.parseInt(positions[0]);
            int positionTwo = Integer.parseInt(positions[1]);
            crossLines[positionOne][positionTwo] = true;
        }
    }

    public static void main(String[] args) {
        new Metro().solve();
    }
}
