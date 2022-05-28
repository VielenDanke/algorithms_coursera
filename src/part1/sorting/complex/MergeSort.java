package part1.sorting.complex;

import part1.sorting.simple.Sort;

import java.util.Random;

import static part1.sorting.Helper.less;

public class MergeSort {

    /**
     * Length of subarray below which we are able to use insertionSort
     */
    private static final int CUTOFF = 7;

    public static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        sort(a, aux, 0, N - 1);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo + CUTOFF - 1) {
            // perform insertion sort on small enough array
            Sort.insertionSortRange(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        // Optimization: if array is already sorted - simply return from recursion
        if (!less(a[mid + 1], a[mid])) return;
        merge(a, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 0; i + 1 < a.length; i++) {
            if (less(a[i+1], a[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Random random = new Random();
        Integer[] integers = new Integer[100];
        for (int i = 0; i < 100; i++) {
            integers[i] = random.nextInt(100);
        }
        MergeSort.sort(integers);
        assert isSorted(integers);
    }
}
