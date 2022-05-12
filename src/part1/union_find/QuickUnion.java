package part1.union_find;

/**
 It's called Lazy algorithm.
 Interpretation: id[i] is parent of i.
 Root of i is id[id[id[...id[i]...]]].

 Defect:
 1. Trees can get tall.
 2. Find too expensive (could be N array accesses).
 */
public class QuickUnion implements UnionFind {

    private final int[] id;

    /**
     *
     * Complexity - O(N)
     * @param size - size of initial array
     */
    public QuickUnion(int size) {
        id = new int[size];

        for (int i = 0; i < id.length; i++) id[i] = i;
    }

    /**
     *
     * Complexity - O(N) - worst (we could end with long tree where each object point to a next)
     * @param i - find root of index i (keep iterating till we find a root index e.g. i == id[i])
     */
    private int root(int i) {
        while (i != id[i]) i = id[i];
        return i;
    }

    /**
     *
     * Complexity - O(N) includes cost of finding roots
     * @param p - first index to union
     * @param q - second index to union
     */
    @Override
    public void union(int p, int q) {
        int pid = root(p);
        int qid = root(q);
        id[pid] = qid;
    }

    /**
     *
     * Complexity - O(N)
     * @param p - first index to union
     * @param q - second index to union
     */
    @Override
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
}
