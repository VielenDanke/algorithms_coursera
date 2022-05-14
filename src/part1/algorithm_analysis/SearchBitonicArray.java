package part1.algorithm_analysis;

import java.util.Arrays;

public class SearchBitonicArray {

    public static boolean search(int[] nums, int target) {
        int n = nums.length;

        int maxIdx = maxIndex(nums);

        return nums[maxIdx] == target ||
                binarySearch(Arrays.copyOfRange(nums, 0, maxIdx), target) ||
                binarySearch(Arrays.copyOfRange(nums, maxIdx, n), target);
    }

    private static boolean binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] == target) {
                return true;
            } else if ((nums[left] < nums[right] && target < nums[mid]) || (nums[left] > nums[right] && target > nums[mid])) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    private static int maxIndex(int[] nums) {
        int n = nums.length, start = 0, end = n - 1;

        while (start < end) {
            int mid = (start + end) / 2;

            if (nums[mid] < nums[mid + 1]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}
