package org.ivzh.binary.search;


// https://informatics.msk.ru/mod/statements/view.php?id=192&chapterid=111728#1
public class LowerUpperBoundBinarySearch {

    private int[] arr = {1, 2, 4, 7, 10, 11, 12, 15};

    private int lowerBinarySearch(int i) {
        int l = 0;
        int r = arr.length;

        while (r - l > 1) {
            int m = (l+r) / 2;

            if (arr[m] > i) {
                r = m;
            } else {
                l = m;
            }
        }

        return r;
    }

    private int upperBinarySearch(int i) {
        int l = 0;
        int r = arr.length;

        while (r - l > 1) {
            int m = (l+r) / 2;

            if (arr[m] >= i) {
                r = m;

            } else {
                l = m;
            }
        }

        return r+1;
    }

}
