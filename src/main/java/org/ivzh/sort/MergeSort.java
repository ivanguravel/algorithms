package org.ivzh.sort;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//
public class MergeSort {

    public static void main(String[] args) {
        //List<Integer> r = new MergeSort().merge(Arrays.asList(1, 2, 8), Arrays.asList(2, 4, 9, 11));

        List<Integer> r = new MergeSort().mergeSort(Arrays.asList(2, 0, 7, 5, 12, 32));

        for (Integer i : r) {
            System.out.println(i);
        }
    }


    public List<Integer> mergeSort(List<Integer> forSort) {
        List<Integer> result;
        if (forSort.size() <= 1) {
            return forSort;
        } else {


            int middle = forSort.size() / 2;

            List<Integer> leftBound = new ArrayList<>(middle);
            List<Integer> rightBound = new ArrayList<>(middle);

            for (int i = 0; i < middle; i ++) {
                leftBound.add(i, forSort.get(i));
            }

            for (int i = middle; i < forSort.size(); i ++) {
                rightBound.add(i - middle, forSort.get(i));
            }

            List<Integer> reducedLeft = mergeSort(leftBound);
            List<Integer> reducedRight = mergeSort(rightBound);
            result = merge(reducedLeft, reducedRight);
        }
        return result;
    }


    public List<Integer> merge(List<Integer> a, List<Integer> b) {
        int max = a.size() + b.size();
        List<Integer> result = new ArrayList<>(max + 1);
        int i = 0;
        int j = 0;
        while (i < a.size() || j < b.size()) {
            if (i >= a.size()) {
                result.add(b.get(j));
                ++j;
            } else if (j >= b.size()) {
                result.add(a.get(i));
                ++i;
            } else {
                if (a.get(i) >= b.get(j)) {
                    result.add(b.get(j));
                    ++j;
                } else {
                    result.add(a.get(i));
                    ++i;
                }
            }
        }

        return result;
    }
}
