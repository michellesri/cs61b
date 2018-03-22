package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    int[] openSitesPerT;
    // array of number of open sites in each test

    public PercolationStats(int N, int T, PercolationFactory pf) {

        openSitesPerT = new int[T];
        // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        while (T > 0) {
            Percolation percolation = pf.make(N);
            StdRandom.setSeed(System.currentTimeMillis());

            while (!percolation.percolates()) {

                int randomRow = StdRandom.uniform(N);
                int randomCol = StdRandom.uniform(N);

                percolation.open(randomRow, randomCol);
            }
            openSitesPerT[T - 1] = percolation.numberOfOpenSites();
            T--;
        }

    }

    public double mean() {
        // sample mean of percolation threshold
        int count = 0;
        for (int i : openSitesPerT) {
            count += i;
        }

        return count / openSitesPerT.length;
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        double mean = mean();

        int total = 0;

        for (int i : openSitesPerT) {
            total += Math.pow((i - mean), 2);
        }

        return total / openSitesPerT.length - 1;
    }

    public double confidenceLow() {
        // low endpoint of 95% confidence interval
        double mean = mean();
        double stddev = stddev();

        return mean - 1.96 * stddev / Math.sqrt(openSitesPerT.length);
    }

    public double confidenceHigh() {
        // high endpoint of 95% confidence interval
        double mean = mean();
        double stddev = stddev();

        return mean + 1.96 * stddev / Math.sqrt(openSitesPerT.length);
    }

    public static void main(String[] args) {
        PercolationFactory percolationFactory = new PercolationFactory();

        double totalTime = 0;
        int counter = 100;

        for (int i = 0; i < counter; i++) {
            Stopwatch stopwatch = new Stopwatch();
            PercolationStats percolationStats = new PercolationStats(200, 50, percolationFactory);
            double time = stopwatch.elapsedTime();
            totalTime += time;
        }

        System.out.println("average time " + totalTime + " / " + counter + " = " + totalTime / counter);
    }
}
