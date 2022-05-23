package part1.sorting;

import static part1.sorting.Helper.less;
import static part1.sorting.Helper.swap;

public class Sort {

    /**
     * N^2 constant time complexity
     *
     * @param array - Comparable array to sort in ascending order
     */
    public static void selectionSort(Comparable[] array) {
        int N = array.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++)
                if (less(array[j], array[min]))
                    min = j;
            swap(array, i, min);
        }
    }

    /**
     * 1/4 N^2 time average, x2 faster than selection sort
     * <p>
     * Partially sorted if the number of inversions is <= N
     * Inversion - pairs of wrong placed elements
     * Example: A E E L M O T R X P S
     * Inversions: T-R, T-P, T-S, R-P, X-P, X-S (Left element in pair has to be after right element of pair)
     * <p>
     * For partially sorted arrays insertionSort() runs in linear time
     *
     * @param array - Comparable array to sort in ascending order
     */
    public static void insertionSort(Comparable[] array) {
        int N = array.length;
        for (int i = 0; i < N; i++) {
            int j = i;
            while (j > 0 && less(array[j], array[j - 1])) {
                swap(array, j, --j);
            }
        }
    }

    /**
     * Idea: move entries more than one position at a time by 'h-sorting' the array
     * An 'h-sorted' array is h interleaved sorted subsequences
     * How to 'h-sort' array? Insertion sort, with stride length h
     * <p>
     * Worst case with 3x + 1 increments O(N^3/2)
     *
     * @param array - Comparable array to sort in ascending order
     */
    public static void shellSort(Comparable[] array) {
        final int constantIncrement = 3;
        final int N = array.length;
        int h = 1;
        while (h < N / constantIncrement) h = constantIncrement * h + 1; // 3x + 1 increment sequence
        while (h >= 1) {
            for (int i = h; i < N; i++) // Insertion sort
                for (int j = i; j >= h && less(array[j], array[j - h]); j -= h)
                    swap(array, j, j - h);
            h = h / constantIncrement; // move to next increment
        }
    }
}
