package part1.unionfind;

public class WeightedQuickUnion implements UnionFind {

    private final int[] id;
    private final int[] sz;

    /**
     *
     * Complexity - O(N)
     * @param size - size of initial array
     */
    public WeightedQuickUnion(int size) {
        id = new int[size];
        sz = new int[size];

        for (int i = 0; i < size; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    /**
     *
     * Complexity - O(logN) - with path compression
     * @param i - find root of index i (keep iterating till we find a root index e.g. i == id[i])
     */
    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    /**
     *
     * Complexity - O(logN) includes cost of finding roots
     * @param p - first index to union
     * @param q - second index to union
     */
    @Override
    public void union(int p, int q) {
        int pid = root(p);
        int qid = root(q);
        if (pid == qid) return;
        if (sz[pid] < sz[qid]) {
            id[pid] = qid;
            sz[qid] += sz[pid];
        } else {
            id[qid] = pid;
            sz[pid] += sz[qid];
        }
    }

    /**
     *
     * Complexity - O(logN)
     * @param p - first index to union
     * @param q - second index to union
     */
    @Override
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
}
