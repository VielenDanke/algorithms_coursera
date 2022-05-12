package part1.union_find.assignment;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double COEFFICIENT = 1.96;
    private final double[] experimentArray;
    private final int experiments;


    // perform independent computational experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        experiments = trials;
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException();
        }
        experimentArray = new double[experiments];
        for (int t = 0; t < experiments; t++) {
            int numOfOpens;
            Percolation pc = new Percolation(n);
            while (!pc.percolates()) {
                int i = StdRandom.uniform(1, n + 1);
                int j = StdRandom.uniform(1, n + 1);
                if (!pc.isOpen(i, j) && !pc.isFull(i, j)) {
                    pc.open(i, j);
                }
            }
            numOfOpens = pc.numberOfOpenSites();
            experimentArray[t] = (double) numOfOpens / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(experimentArray);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(experimentArray);
    }

    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return mean() - ((COEFFICIENT * stddev()) / Math.sqrt(experiments));
    }

    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return mean() + ((COEFFICIENT * stddev()) / Math.sqrt(experiments));
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        String confidence = String.format("%f, %f", ps.confidenceLo(), ps.confidenceHi());
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = [" + confidence + "]");
    }
}
