package part1.algorithm_analysis;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class ThreeSum {

    /*
    Power Law - a * N ^ b where b - complexity and a - coefficient
    For example:
    1. If input - 8000 - and it runs for 51.0 seconds - 51.0 = a * 8000 ^ 3, a = 0.998 * 10^(-10)
    2. If input - 16000, we know 'a', which means Time = 0.998 * 10^(-10) * 16000^3 (Allows us calculating the result without any experiment)
     */
    public static int countBruteForce(int[] array) {
        int length = array.length;
        int count = 0;

        for (int i = 0; i < length; i++)
            for (int j = i + 1; j < length; j++)
                for (int k = j + 1; k < length; k++)
                    if (array[i] + array[j] + array[k] == 0)
                        count++;
        return count;
    }

    public static int countWithPointers(int[] array) {
        Arrays.sort(array);
        int count = 0;
        int n = array.length;

        for (int i = 0; i < n; i++) {
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = array[i] + array[left] + array[right];
                if (sum == 0) {
                    count++;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return count;
    }

    public static int count(int[] array) {
        Arrays.sort(array);

        int n = array.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int numToFind = -(array[i] + array[j]);
                if (binarySearch(array, numToFind)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean binarySearch(int[] array, int num) {
        int left = 0, right = array.length - 1;

        while (left <= right) {
            int middle = (left + right) / 2;
            if (array[middle] == num) {
                return true;
            } else if (array[middle] < num) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] ints = StdIn.readAllInts();
        Stopwatch stopwatch = new Stopwatch();
        StdOut.println(ThreeSum.countBruteForce(ints));
        double time = stopwatch.elapsedTime();
        System.out.println(time);
    }
}
