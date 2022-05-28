package part1.sorting.interview_questions;

import part1.sorting.simple.Sort;

import java.util.HashMap;
import java.util.Map;

public class Permutation {

    public boolean isPermutations(Comparable[] original, Comparable[] permutation) {
        Sort.shellSort(original);
        Sort.shellSort(permutation);
        int N = original.length;

        for (int i = 0; i < N; i++) {
            if (original[i].compareTo(permutation[i]) != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isPermutationsMap(int[] original, int[] permutation) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int num : permutation) {
            m.put(num, m.getOrDefault(num, 0) + 1);
        }
        for (int num : original) {
            if (!m.containsKey(num)) {
                return false;
            }
            m.put(num, m.get(num) - 1);
        }
        for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }
        return true;
    }
}
