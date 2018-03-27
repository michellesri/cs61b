package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private boolean[][] grid;
    private int openSites;
    private int N;
    private WeightedQuickUnionUF wquCheckFull;
    private WeightedQuickUnionUF wquPercolation;

    private final int topSentinel;
    private final int bottomSentinel;

    public Percolation(int N) {

        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        topSentinel = N * N;
        bottomSentinel = N * N + 1;

        wquPercolation = new WeightedQuickUnionUF(N * N + 2);
        wquCheckFull = new WeightedQuickUnionUF(N * N + 1);
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

    private void connectToTop(int currentCell) {
        wquPercolation.union(topSentinel, currentCell);
        wquCheckFull.union(topSentinel, currentCell);

    }

    private void connectToBottom(int currentCell) {
        wquPercolation.union(bottomSentinel, currentCell);
    }

    public void open(int row, int col) {
    // open the site (row, col) if it is not open already
        if (!grid[row][col]) {
            grid[row][col] = true;
            openSites++;
            if (row == 0) {
                connectToTop(xyTo1D(row, col));
            }

            if (row == N - 1) {
                connectToBottom(xyTo1D(row, col));

            }

            // check all neighbors. if there are neighbors call union on open neighbors
            mergeOpenNeighbors(row, col);
        }
    }

    private void mergeOpenNeighbors(int row, int col) {
        int topNeighborRow = row - 1;
        int bottomNeighborRow = row + 1;

        int leftNeighborCol = col - 1;
        int rightNeighborCol = col + 1;

        boolean topNeighborOpen = isOpen(topNeighborRow, col);
        boolean bottomNeighborOpen = isOpen(bottomNeighborRow, col);

        boolean leftNeighborOpen = isOpen(row, leftNeighborCol);
        boolean rightNeighborOpen = isOpen(row, rightNeighborCol);

        int centerCell = xyTo1D(row, col);

        if (topNeighborOpen) {
            wquPercolation.union(xyTo1D(topNeighborRow, col), centerCell);
            wquCheckFull.union(xyTo1D(topNeighborRow, col), centerCell);

        }

        if (bottomNeighborOpen) {
            wquPercolation.union(xyTo1D(bottomNeighborRow, col), centerCell);
            wquCheckFull.union(xyTo1D(bottomNeighborRow, col), centerCell);

        }

        if (rightNeighborOpen) {
            wquPercolation.union(xyTo1D(row, rightNeighborCol), centerCell);
            wquCheckFull.union(xyTo1D(row, rightNeighborCol), centerCell);

        }

        if (leftNeighborOpen) {
            wquPercolation.union(xyTo1D(row, leftNeighborCol), centerCell);
            wquCheckFull.union(xyTo1D(row, leftNeighborCol), centerCell);

        }
    }

    private boolean isValidCoordinate(int row, int col) {
        return row < N && row >= 0 && col < N && col >= 0;
    }
    public boolean isOpen(int row, int col) {
    // is the site (row, col) open?

        if (!isValidCoordinate(row, col)) {
            return false;
        }

        return grid[row][col];

    }
    public boolean isFull(int row, int col) {
    // is the site (row, col) full?
        // check if the cell is full by seeing if any in the set of WQU are connected to the top
        int currentCell = xyTo1D(row, col);
        return wquCheckFull.connected(currentCell, topSentinel);


    }
    public int numberOfOpenSites() {
    // number of open sites
        return openSites;
    }
    public boolean percolates() {
    // does the system percolate?
        // check if one set in the WQU has a cell that's connected to the top and bottom
        return wquPercolation.connected(topSentinel, bottomSentinel);
    }

    private int xyTo1D(int r, int c) {
        // change a row, column location into an int
        return (r * N) + c;
    }
    public static void main(String[] args) {
    // use for unit testing (not required)

    }


}
