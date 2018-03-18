package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean grid[][];
    private int openSites;
    private int N;

    public Percolation(int N) {
    // create N-by-N grid, with all sites initially blocked
        openSites = 0;
        this.N = N;
        grid = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
    }

    public void open(int row, int col) {
    // open the site (row, col) if it is not open already
        if (!grid[row][col]) {
            grid[row][col] = true;
            openSites++;
        }
    }
    public boolean isOpen(int row, int col) {
    // is the site (row, col) open?
        return grid[row][col];

    }
    public boolean isFull(int row, int col) {
    // is the site (row, col) full?

    }
    public int numberOfOpenSites() {
    // number of open sites
        return openSites;
    }
    public boolean percolates() {
    // does the system percolate?
    }

    public int xyTo1D(int r, int c) {
        // change a row, column location into an int
        return (r * N) + c;
    }
    public static void main(String[] args) {
    // use for unit testing (not required)

    }
}

// need to use union on two ints union (xyto1d, xyto2d)