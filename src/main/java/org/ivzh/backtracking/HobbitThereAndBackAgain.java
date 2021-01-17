package org.ivzh.backtracking;

import java.util.*;

public class HobbitThereAndBackAgain {

    int n;
    List<LinkedList<Integer>> sequences;

    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    LinkedList<Integer> minList;
    LinkedList<Integer> maxList;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        new HobbitThereAndBackAgain().solve(s);
    }

    private void solve(Scanner s) {
        this.n = s.nextInt();
        this.sequences = new ArrayList<>();
        generateSeq();

        for (LinkedList<Integer> i : sequences) {
            Integer prev = i.get(0);
            Integer buffer = 0;
            for (int j = 1; j < i.size(); j++) {
                buffer = buffer + (prev * i.get(j));
                prev = i.get(j);
            }
            if (min > buffer) {
                min = buffer;
                minList = i;
            }

            if (max < buffer) {
                max = buffer;
                maxList = i;
            }
        }

        System.out.println(concatAsString(minList));
        System.out.println(concatAsString(maxList));

    }

    private void generateSeq() {
        int[] arr = new int[n];
        for (int i = 1; i <= n; i++) {
            arr[i-1] = i;
        }
        heapPermutation(arr, n);
        
    }

    private String concatAsString(LinkedList<Integer> list) {
        StringBuilder result = new StringBuilder();
        for (Integer i : list) {
            result.append(i).append(" ");
        }
        return result.toString();
    }

    void heapPermutation(int a[], int size) {

        if (size == 1) {
            int[] copy = Arrays.copyOf(a, a.length);
            LinkedList<Integer> l = new LinkedList<>();
            for (int i : copy) {
                l.add(i);
            }
            sequences.add(l);
        } else {
            for (int i = 0; i < size; i++) {

                heapPermutation(a, size - 1);

                if (size % 2 == 1) {
                    swap(a, 0, size - 1);
                } else {
                    swap(a, i, size - 1);
                }
            }
        }
    }

    private void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] =tmp;
    }
}
