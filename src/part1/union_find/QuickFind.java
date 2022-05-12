package part1.union_find;

/**
 It's called Eager algorithm.
 Interpretation: p and q are connected iff (if and only if) they have the same id.

 Defect:
 1. Union is too expensive (N array access).
 2. Trees are flat, but too expensive to keep them flat.

 Ex. Takes O(N^2) array accesses to process sequence of N union commands on N objects.
 */
public class QuickFind implements UnionFind {

    private final int[] id;

    /**
     *
     * Complexity - O(N)
     * @param size - size of initial array
     */
    public QuickFind(int size) {
        id = new int[size];

        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    /**
     *
     * Complexity - O(N)
     * @param p - first index to union
     * @param q - second index to union
     */
    @Override
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++)
            if (id[i] == pid) id[i] = qid;
    }

    /**
     *
     * Complexity - O(1)
     * @param p - first index to check
     * @param q - second index to check
     * @return true if both indices are in the same group
     */
    @Override
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }
}
