package org.ivzh.graph;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=50&id_problem=641
public class LayingTiles {

    int n;
    int s;
    char[][] matrix;

    private void solve() {
        this.matrix = new  char[9][9];

        int k = 0;
        for (int i = 0; i < 8; i++) {
            char[] ch = nextToken().toCharArray();
            for (int j =0; j <8; j++) {
                matrix[i][j] = ch[j];
            }
        }


        // 'e' means empty
        for (int i = 0; i <= 8; i++) {
            matrix[0][i]='e';
            matrix[8][i]='e';
            matrix[i][0]='e';
            matrix[i][8]='e';
        }



        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j =0; j <8; j++) {
                if (matrix[i][j] != 'e') {
                    ++count;
                    dfs(i, j);
                }
            }
        }

        println(count);
    }

    private void dfs(Integer i, Integer j) {

        char c = matrix[i][j];
        matrix[i][j] = 'e';

        if ((c == 'W' && matrix[i+1][j] == 'B') || (c == 'B' && matrix[i+1][j] == 'W')) {
            dfs(i+1, j);
        }

        if ((c == 'W' && matrix[i-1][j] == 'B') || (c == 'B' && matrix[i-1][j] == 'W')) {
            dfs(i-1, j);
        }

        if ((c == 'W' && matrix[i][j+1] == 'B') || (c == 'B' && matrix[i][j+1] == 'W')) {
            dfs(i, j+1);
        }

        if ((c == 'W' && matrix[i][j-1] == 'B') || (c == 'B' && matrix[i][j-1] == 'W')) {
            dfs(i, j-1);
        }
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new LayingTiles().run();
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


    private int nextInt() {
        return parseInt(nextToken());
    }

    private long nextLong() {
        return parseLong(nextToken());
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
}
