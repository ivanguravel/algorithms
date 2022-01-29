package org.ivzh.binary.search;

// https://informatics.msk.ru/mod/statements/view.php?id=192&chapterid=2#1
public class ClosestElementToOrigin {

    private int[] arr = {1, 2, 4, 7, 10, 11, 12, 15};

    private int closestToNumberBinarySearch(int i) {
        int l = 0;
        int r = arr.length-1;

        while (r - l > 1) {
            int m = (l+r) / 2;

            if (arr[m] == i) {
                return arr[m];
            } else if (arr[m] > i){
                r = m;
            } else {
                l = m;
            }
        }

        boolean condition = Math.abs(i - arr[l]) <= Math.abs(i - arr[r]);
        return condition ? arr[l] : arr[r];
    }
}
