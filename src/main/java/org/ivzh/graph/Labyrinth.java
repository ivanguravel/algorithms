package org.ivzh.graph;

import java.io.PrintWriter;
import java.util.Scanner;

// https://acm.timus.ru/problem.aspx?space=1&num=1033
public class Labyrinth {

    int n = 0;
    char[][] matrix = new char[35][35];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new Labyrinth().solve(in, out);

        out.flush();
    }

    private void solve(Scanner in, PrintWriter out) {
        readInput(in, out);




        System.out.println(9 * (count() - 2));
    }

    public int count() {
        Integer result = 0;
        for (int i = 0; i <= n; ++i ) {
            for ( int j = 0; j <= n; ++j) {
                if (matrix[i][j] == '#') {
                    if (matrix[i+1][j] == '.' || i+1 <= n)
                        result++;
                    if (matrix[i-1][j] == '.' || i-1 >= 0)
                        result++;
                    if (matrix[i][j+1] == '.' || j+1 <= n)
                        result++;
                    if (matrix[i][j-1] == '.' || j-1 >=0)
                        result++;
                }
            }
        }
        return result;
    }

    private void readInput(Scanner in, PrintWriter out) {
        this.n = in.nextInt();
        in.nextLine();
        for (int i =0; i < n; i++) {
            String s = in.nextLine();
            char[] ch = s.toCharArray();
            for (int j = 0; j < n; j++) {
                matrix[i][j] = ch[j];
            }
        }
    }
}
