package part1.unionfind.assignment;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final int[][] DIR = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private static final int TOP = 0;
    private final boolean[][] opened;
    private final int size;
    private final int bottom;
    private int openSites;
    private final WeightedQuickUnionUF qf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1) throw new IllegalArgumentException();
        opened = new boolean[n][n];
        size = n;
        openSites = 0;
        bottom = size * size + 1;
        qf = new WeightedQuickUnionUF(size * size + 2);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isInRange(row, col)) {
            throw new IllegalArgumentException();
        }
        if (row == 1) {
            qf.union(getIndexQF(row, col), TOP);
        }
        if (row == size) {
            qf.union(getIndexQF(row, col), bottom);
        }
        if (!isOpen(row, col)) {
            openSites++;
            opened[row - 1][col - 1] = true;
        }
        for (int[] direction : DIR) {
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];
            if (isInRange(nextRow, nextCol) &&
                    isOpen(nextRow, nextCol)) {
                qf.union(getIndexQF(row, col), getIndexQF(nextRow, nextCol));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!isInRange(row, col)) {
            throw new IllegalArgumentException();
        }
        return opened[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isInRange(row, col)) {
            throw new IllegalArgumentException();
        }
        return qf.find(getIndexQF(row, col)) == qf.find(TOP);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return qf.find(TOP) == qf.find(bottom);
    }

    private boolean isInRange(int row, int col) {
        return row >= 1 && row <= size && col >= 1 && col <= size;
    }

    private int getIndexQF(int row, int col) {
        return (row - 1) * size + col;
    }
}
