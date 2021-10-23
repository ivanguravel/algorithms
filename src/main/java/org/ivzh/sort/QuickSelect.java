package org.ivzh.sort;

public class QuickSelect {


    public static void main(String[] args) {
        int[] a = {2, 7 , 0 , 3, 9 ,6, 4, 5};
        System.out.println(qSelect(a, 0, a.length -1, 3));
    }


    public static int qSelect(int []a, int l, int r, int k){
        // find the partition
        int partition = partition(a, l, r);

        // if partition value is equal to the kth position,
        // return value at k.
        if (partition == k - 1)
            return a[partition];

            // if partition value is less than kth position,
            // search right side of the array.
        else if (partition < k - 1)
            return qSelect(a, partition + 1, r, k);

            // if partition value is more than kth position,
            // search left side of the array.
        else
            return qSelect(a, l, partition - 1, k);
    }

    public static int partition(int[] arr, int low,
                                int high)
    {
        int pivot = arr[high], pivotloc = low;
        for (int i = low; i <= high; i++) {
            // inserting elements of less value
            // to the left of the pivot location
            if (arr[i] < pivot) {
                int temp = arr[i];
                arr[i] = arr[pivotloc];
                arr[pivotloc] = temp;
                pivotloc++;
            }
        }

        // swapping pivot to the final pivot location
        int temp = arr[high];
        arr[high] = arr[pivotloc];
        arr[pivotloc] = temp;

        return pivotloc;
    }


    public static void swap(int []a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;

    }
}
