package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private boolean grid[][];
    private int openSites;
    private int N;
    private WeightedQuickUnionUF WQU;


    public Percolation(int N) {

        WQU = new WeightedQuickUnionUF(N * N);
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

            // TODO: check all neighbors. if there are neighbors call union on open neighbors
            // WQU.union(convert into ints, other int)
        }
    }
    public boolean isOpen(int row, int col) {
    // is the site (row, col) open?
        return grid[row][col];

    }
    public boolean isFull(int row, int col) {
    // is the site (row, col) full?
        // check if the cell is full by seeing if any in the set of WQU are connected to the top

    }
    public int numberOfOpenSites() {
    // number of open sites
        return openSites;
    }
    public boolean percolates() {
    // does the system percolate?
        // check if one set in the WQU has a cell that's connected to the top and bottom
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