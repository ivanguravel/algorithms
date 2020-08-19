package org.ivzh.other;

import java.io.PrintWriter;
import java.util.*;

// https://acm.timus.ru/problem.aspx?space=1&num=1491
public class UnrealStory {

    int n;
    int[] result;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new UnrealStory().scanLine(in, out);
    }

    void scanLine(Scanner in, PrintWriter out) {
        readData(in);

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            result[i] = result[i] + result[i - 1];
            builder.append(String.format("%s ", this.result[i]));
        }

        out.println(builder.toString());
        out.flush();
    }

    private void readData(Scanner in) {
        this.n = in.nextInt();
        this.result = new int[n+3];

        in.nextLine();

        String[] line;
        Integer key1;
        Integer key2;
        Integer val;
        for (int i = 0; i < n+1; i++) {
            line = in.nextLine().split(" ");
            key1 = Integer.parseInt(line[0]);
            key2 = Integer.parseInt(line[1]);
            val = Integer.parseInt(line[2]);


            result[key1] = result[key1] + val;
            result[key2 + 1] = result[key2 + 1] - val;
        }
    }
}
