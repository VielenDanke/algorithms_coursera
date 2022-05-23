package part1.sorting.interview_questions;

public class SwapByColor {

    private enum Color {
        RED, WHITE, BLUE;
    }
    public static void sortByColor(Color[][] buckets) {
        int N = buckets.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < buckets[i].length; j++) {
                Color color = color(buckets, i, j);
                if (color == Color.RED && i != 0) {
                    int badColorIdx = findBadColorIdx(buckets, 0);
                    if (badColorIdx == -1) {
                        continue;
                    }
                    swap(buckets, i, j, 0, badColorIdx);
                } else if (color == Color.BLUE && i != 1) {
                    int badColorIdx = findBadColorIdx(buckets, 1);
                    if (badColorIdx == -1) {
                        continue;
                    }
                    swap(buckets, i, j, 1, badColorIdx);
                } else if (color == Color.WHITE && i != 2) {
                    int badColorIdx = findBadColorIdx(buckets, 2);
                    if (badColorIdx == -1) {
                        continue;
                    }
                    swap(buckets, i, j, 2, badColorIdx);
                }
            }
        }
    }

    private static int findBadColorIdx(Color[][] buckets, int idx) {
        Color color;
        if (idx == 0) {
            color = Color.RED;
        } else if (idx == 1) {
            color = Color.BLUE;
        } else {
            color = Color.WHITE;
        }
        for (int i = 0; i < buckets[idx].length; i++) {
            if (buckets[idx][i] != color) {
                return i;
            }
        }
        return -1;
    }

    private static Color color(Color[][] buckets, int bucketIdx, int idx) {
        return buckets[bucketIdx][idx];
    }

    private static void swap(Color[][] bucket, int fromBucket, int fromIdx, int toBucket, int toIdx) {
        Color color = bucket[toBucket][toIdx];
        bucket[toBucket][toIdx] = bucket[fromBucket][fromIdx];
        bucket[fromBucket][fromIdx] = color;
    }

    public static void main(String[] args) {
        Color[][] buckets = {{Color.RED, Color.BLUE, Color.WHITE, Color.WHITE}, {Color.BLUE, Color.RED, Color.BLUE, Color.WHITE}, {Color.RED, Color.RED, Color.BLUE, Color.WHITE}};
        SwapByColor.sortByColor(buckets);
    }
}
