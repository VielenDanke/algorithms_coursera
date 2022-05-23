package part1.sorting;

import java.util.Random;

import static part1.sorting.Helper.swap;

public class Shuffle {

    public static void knuthShuffle(Comparable[] array) {
        final int N = array.length;
        final Random random = new Random();
        for (int i = 0; i < N; i++) {
            int rndIdx = random.nextInt(i + 1);
            swap(array, i, rndIdx);
        }
    }
}
