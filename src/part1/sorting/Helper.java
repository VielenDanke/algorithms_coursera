package part1.sorting;

public class Helper {

    public static void swap(Comparable[] nums, int i, int j) {
        Comparable temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

}
