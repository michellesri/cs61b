package hw2;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private double[] openSitesPerT;
    // array of number of open sites in each test

    public PercolationStats(int N, int T, PercolationFactory pf) {

        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        openSitesPerT = new double[T];
        // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        while (T > 0) {
            Percolation percolation = pf.make(N);
            StdRandom.setSeed(System.nanoTime());

            while (!percolation.percolates()) {

                int randomRow = StdRandom.uniform(N);
                int randomCol = StdRandom.uniform(N);

                if (!percolation.isOpen(randomRow, randomCol)) {
                    percolation.open(randomRow, randomCol);
                }

            }
            openSitesPerT[T - 1] = percolation.numberOfOpenSites() / (double) (N * N);
            T--;
        }

    }

    public double mean() {
        // sample mean of percolation threshold
        double count = 0;
        for (double i : openSitesPerT) {
            count += i;
        }

        return count / openSitesPerT.length;
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        double mean = mean();

        double total = 0;

        for (double i : openSitesPerT) {
            total += Math.pow((i - mean), 2);
        }

        return Math.sqrt(total / (openSitesPerT.length - 1));
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

//    public static void main(String[] args) {
//        PercolationFactory percolationFactory = new PercolationFactory();
//
//        double totalTime = 0;
//        int counter = 100;
//
//        for (int i = 0; i < counter; i++) {
//            Stopwatch stopwatch = new Stopwatch();
//            PercolationStats percolationStats = new PercolationStats(200, 50, percolationFactory);
//            double time = stopwatch.elapsedTime();
//            totalTime += time;
//        }
//
//        System.out.println("average time " + totalTime + " / " +
//              counter + " = " + totalTime / counter);
//    }
}
