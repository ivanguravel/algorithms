package org.ivzh.sort;

public class QuickSortAnother {

    public static void main(String []args){
        int[] a = {2, 7 , 0 , 3, 9 ,6, 4, 5};
        sort(a, 0, a.length-1);
        System.out.println(java.util.Arrays.toString(a));
    }


    public static void sort(int []a, int l, int r) {
        if (l >= r) {
            return;
        } else {
            int pivot = partition(a, l, r);
            sort(a, l, pivot-1);
            sort(a, pivot+1, r);
        }
    }

    public static int partition(int []a, int l, int r){
        int pivot = a[r];
        int i = l-1;
        for (int m = l; m <= r; m++) {

            if (a[m] <= pivot) {
                swap(a, ++i, m);
            }
        }
        return i;

    }


    public static void swap(int []a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;

    }
}
