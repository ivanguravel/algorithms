package org.ivzh.other;

import java.io.PrintWriter;
import java.util.Scanner;

// https://acm.timus.ru/problem.aspx?space=1&num=1491
public class UnrealStory {

    int n;
    int[][] data;
    int[] result;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new UnrealStory().solve(in, out);
    }

    // O(n^2)
    void solve(Scanner in, PrintWriter out) {
        readData(in);

        for (int[] d : data) {
            for (int j = d[0]; j <= d[1]; j++) {
                result[j] = result[j] + d[2];
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < n+1; i++) {
            builder.append(result[i] + " ");
        }

        out.println(builder.toString());
        out.flush();
    }

    private void readData(Scanner in) {
        this.n = in.nextInt();
        this.data = new int[n+1][3];
        this.result = new int[n+1];
        in.nextLine();
        for (int i = 0; i < n+1; i++) {
            String[] line = in.nextLine().split(" ");
            this.data[i][0] = Integer.parseInt(line[0]);
            this.data[i][1] = Integer.parseInt(line[1]);
            this.data[i][2] = Integer.parseInt(line[2]);
        }
    }
}
