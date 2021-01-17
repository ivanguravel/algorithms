package org.ivzh.backtracking;

import java.io.PrintWriter;
import java.util.*;

// https://acm.timus.ru/problem.aspx?space=1&num=1535
public class HobbitThereAndBackAgain {

    int n;

    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    LinkedList<Integer> minList;
    LinkedList<Integer> maxList;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        PrintWriter p = new PrintWriter(System.out);
        new HobbitThereAndBackAgain().solve(s, p);
    }

    private void solve(Scanner s, PrintWriter printWriter) {
        this.n = s.nextInt();
        generateSeq();

        printWriter.println(concatAsString(minList));
        printWriter.println(concatAsString(maxList));
        printWriter.flush();

    }

    private void generateSeq() {
        int[] arr = new int[n];
        for (int i = 1; i <= n; i++) {
            arr[i-1] = i;
        }
        heapPermutation(arr, n);
    }


    void heapPermutation(int a[], int size) {

        if (size == 1) {
            resultCalculator(a);
        } else {
            for (int i = 1; i < size; i++) {

                heapPermutation(a, size - 1);

                if (size % 2 == 1) {
                    swap(a, 1, size - 1);
                } else {
                    swap(a, i, size - 1);
                }
            }
        }
    }

    private void resultCalculator(int[] a) {
        Integer prev = a[0];
        Integer buffer = 0;
        for (int j = 1; j < a.length; j++) {
            buffer = buffer + (prev * a[j]);
            prev = a[j];
        }

        buffer = buffer + (a[0] * a[a.length - 1]);
        if (min >= buffer) {
            min = buffer;
            minList = toList(a);
        }

        if (max < buffer) {
            max = buffer;
            maxList = toList(a);
        }
    }

    private void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] =tmp;
    }

    private LinkedList<Integer> toList(int[] a) {
        int[] copy = Arrays.copyOf(a, a.length);
        LinkedList<Integer> l = new LinkedList<>();
        for (int i : copy) {
            l.add(i);
        }
        return l;
    }

    private String concatAsString(LinkedList<Integer> list) {
        StringBuilder result = new StringBuilder();
        for (Integer i : list) {
            result.append(i).append(" ");
        }
        return result.toString();
    }
}
