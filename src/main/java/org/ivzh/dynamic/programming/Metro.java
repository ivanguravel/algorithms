package org.ivzh.dynamic.programming;

import java.io.PrintWriter;
import java.util.Scanner;

// https://acm.timus.ru/problem.aspx?space=1&num=1119
public class Metro {


    private static final double HYPOTENUSE = 141.42;

    Scanner in;
    PrintWriter out;

    int n, m, crossLinesNumber;

    double[][] matrix;
    boolean[][] crossLines;

    private void solve() {
        readData();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                double fromLowerCorner = matrix[i][j - 1] + 100.0;
                double fromPreviousLine = matrix[i - 1][j] + 100.0;

                double minimumWithoutHypotenuse = Math.min(fromLowerCorner, fromPreviousLine);
                matrix[i][j] = minimumWithoutHypotenuse;
                // if hypotenuse present
                if (crossLines[i][j]) {
                    double fromHypotenuse = matrix[i - 1][j - 1] + HYPOTENUSE ;
                    matrix[i][j] = Math.min(fromHypotenuse, minimumWithoutHypotenuse);
                }
            }
        }
        out.println(Math.round(matrix[n][m]));
        out.flush();
    }


    private void readData() {

        in = new Scanner(System.in);
        out = new PrintWriter(System.out);

        n = in.nextInt();
        m = in.nextInt();
        crossLinesNumber = in.nextInt();

        matrix = new double[n + 2][m + 2];

        crossLines = new boolean[n + 2][m + 2];

        matrix[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // every horizontal lines
                matrix[i][0] = i * 100.0;
                // every vertical lines
                matrix[0][j] = j * 100.0;
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
