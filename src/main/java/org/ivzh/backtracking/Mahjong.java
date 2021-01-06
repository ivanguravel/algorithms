package org.ivzh.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Mahjong {


    public static void main(String[] args) {
        String s = "1a 2a 3a 6a 7a 8a 5b 5b 5b 9c 8c 7c 1c 1c";
        new Mahjong().solve(s);
    }

    private void solve(String s) {
        String[] split = s.split(" ");
        Arrays.sort(split, Comparator.comparingInt((String o) -> o.charAt(1)).thenComparingInt(o -> o.charAt(0)));
        System.out.println(Arrays.toString(split));
        backtracking(Arrays.asList(split), false, 0);
    }

    private void backtracking(List<String> container, boolean isPairPresent, int tripletCount) {

        if (container.isEmpty() || (isPairPresent && tripletCount == 4)) {
            System.out.println("Tsumo");
            System.exit(0);
        }

        if (!isPairPresent) {
            int prev = 0;
            int i;
            for (i = 1; i < container.size(); i++) {
                if (container.get(prev).equalsIgnoreCase(container.get(i))) {
                    List<String> newContainer = new ArrayList<>();
                    newContainer.addAll(container);
                    newContainer.remove(i);
                    newContainer.remove(prev);
                    backtracking(newContainer, true, tripletCount);
                }
                prev = i;

            }
        }

        if (tripletCount < 4) {

            // get similar triplet
            int first = 0;
            int second = 1;
            for (int i = 2; i < container.size(); i++) {
                if (container.get(i).equalsIgnoreCase(container.get(second)) && container.get(first).equalsIgnoreCase(container.get(second))) {
                    ++tripletCount;
                    List<String> newContainer = new ArrayList<>();
                    newContainer.addAll(container);
                    newContainer.remove(i);
                    newContainer.remove(second);
                    newContainer.remove(first);

                    backtracking(newContainer, isPairPresent, tripletCount + 1);
                }
                first = i - 1;
                second = i;
            }


            // get sequential triplet

            for (int i = 0; i < container.size(); i++) {
                int firstElement = findNext(container, i);
                if (firstElement != -1) {
                    int secondElement = findNext(container, firstElement);
                    if (secondElement != -1) {
                        List<String> newContainer = new ArrayList<>();
                        newContainer.addAll(container);
                        newContainer.remove(secondElement);
                        newContainer.remove(firstElement);
                        newContainer.remove(i);
                        backtracking(newContainer, isPairPresent, tripletCount + 1);
                    }
                }
            }
        }

    }

    private int findNext(List<String> container, int currentPosition) {

        String current = container.get(currentPosition);

        for (int i = currentPosition +1; i < container.size(); i++) {
            String next = container.get(i);
            if (current.charAt(1) == next.charAt(1) && (int) current.charAt(0) + 1 == (int) next.charAt(0)) {
                return i;
            }

        }
        return -1;
    }
}
