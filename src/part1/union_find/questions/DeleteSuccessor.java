package part1.union_find.questions;

import java.util.ArrayList;
import java.util.List;

public class DeleteSuccessor {

    private static class Pair {
        int idx;
        boolean isFound;

        private Pair(int idx, boolean isFound) {
            this.idx = idx;
            this.isFound = isFound;
        }
    }

    private final List<Integer> list;

    /**
     *
     * Complexity - O(N)
     *
     * @param size - construct set of number of size
     */
    public DeleteSuccessor(int size) {
        list = new ArrayList<>();

        for (int i = 0; i < size; i++) list.add(i);
    }

    /**
     *
     * Complexity - O(logN)
     *
     * @param n - find successor for number n
     * @return successor of number n
     */
    public int successor(int n) {
        Pair pair = findIdx(n);
        if (pair.isFound) {
            if (pair.idx + 1 >= list.size()) return -1;
            return list.get(pair.idx + 1);
        }
        return list.get(pair.idx);
    }

    /**
     *
     * Complexity - O(logN)
     *
     * @param n - number to remove
     */
    public void remove(int n) {
        Pair pair = findIdx(n);
        if (pair.isFound) list.remove(pair.idx);
    }

    /**
     *
     * Complexity - O(logN)
     *
     * @param n - number to find
     * @return Pair of found idx and sign number is found or not
     */
    public Pair findIdx(int n) {
        int left = 0, right = list.size() - 1;

        while (left <= right) {
            int middle = (left + right) / 2;

            if (list.get(middle) == n) return new Pair(middle, true);
            else if (list.get(middle) > n) right = middle - 1;
            else left = middle + 1;
        }
        return new Pair(left, false);
    }
}
