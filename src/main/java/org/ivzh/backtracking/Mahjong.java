package org.ivzh.backtracking;

import java.util.*;

// https://acm.timus.ru/problem.aspx?space=1&num=2151
public class Mahjong {


    private static boolean IS_TENPAI = false;


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //String s = in.nextLine();
        //String s1 = in.nextLine();
       // String v = String.join(" ", s, s1);
        new Mahjong().solve(in.nextLine());
    }

    private void solve(String s) {
        String[] split = s.split(" ");
        Arrays.sort(split, Comparator.comparingInt((String o) -> o.charAt(1)).thenComparingInt(o -> o.charAt(0)));
       // System.out.println(Arrays.toString(split));
        backtracking(Arrays.asList(split), false, 0);
        if (IS_TENPAI) {
            System.out.println("Tenpai");
        } else {
            System.out.println("Noten");
        }
    }

    private void backtracking(List<String> container, boolean isPairPresent, int tripletCount) {



        if (container.isEmpty()) {
            System.out.println("Tsumo");
            System.exit(0);
        }


        if (((!isPairPresent && tripletCount == 4) || (isPairPresent && tripletCount == 3)) && isTenpaiPresent(container)) {
            IS_TENPAI = true;
        }


        if (!isPairPresent) {
            int prev = 0;
            int i;
            for (i = 1; i < container.size(); i++) {
                if (container.get(prev).equalsIgnoreCase(container.get(i))) {


//                    if ((i < (container.size() - 1) && !container.get(i).equalsIgnoreCase(container.get(i+1)))) {
//                        i = i+2;
//                        prev = i+1;
//                        continue;
//                    }
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

    private boolean isTenpaiPresent(List<String> container) {
        if (container.size() == 2) {
            return true;
        }


        if (container.get(0).equalsIgnoreCase(container.get(1))
                || container.get(1).equalsIgnoreCase(container.get(2))
                || container.get(0).equalsIgnoreCase(container.get(2))) {
            return true;
        }

        int firstTriplet = (container.get(1).charAt(0) - container.get(0).charAt(0));
        int secondTriplet = (container.get(2).charAt(0) - container.get(0).charAt(0));
        int thirdTriplet = (container.get(2).charAt(0) - container.get(1).charAt(0));


        if ( firstTriplet <= 2 && firstTriplet > 0 &&
                container.get(0).charAt(1) == container.get(1).charAt(1) ||

                secondTriplet <= 2 && secondTriplet > 0 &&
                        container.get(2).charAt(1) == container.get(0).charAt(1) ||

                thirdTriplet <= 2 && thirdTriplet > 0 &&
                        container.get(2).charAt(1) == container.get(1).charAt(1)

        ) {
            return true;
        }

        return false;
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
