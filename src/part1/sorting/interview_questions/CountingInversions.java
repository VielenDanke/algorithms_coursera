package part1.sorting.interview_questions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class CountingInversions {

    public static int countInversions(Object[] arr, Comparator<Object> comparator) {
        int N = arr.length, inversions = 0;
        Object[] copyArr = new Object[N];
        System.arraycopy(arr, 0, copyArr, 0, N);
        Arrays.sort(arr, comparator);

        for (int i = 0; i < N; i++)
            if (comparator.compare(copyArr[i], arr[i]) < 0)
                inversions++;
        return inversions;
    }

    public static void main(String[] args) {
        Random random = new Random();
        Integer[] rndNumbers = new Integer[100];
        for (int i = 1; i <= 100; i++) {
            int rndNum = random.nextInt(i);
            rndNumbers[i-1] = rndNum;
        }
        int inversions = CountingInversions.countInversions(rndNumbers, (l, r) -> {
            Integer left = (Integer) l;
            Integer right = (Integer) r;
            return left - right;
        });
        System.out.println(inversions);
    }
}
