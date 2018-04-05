package hw4.puzzle;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Returns the string representation of the board.
      * Uncomment this method. */

public class Board implements WorldState {
    private int[][] tiles;
    private int size;

    public Board(int[][] tiles) {
        this.tiles = tiles;
        this.size = tiles.length;

    }
    public int tileAt(int i, int j) {
        return tiles[i][j];
    }
    public int size() {
        return size;
    }

    private int[][] copyTiles(int[][] tiles) {
        int[][] copy = new int[tiles.length][tiles.length];

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                copy[i][j] = tiles[i][j];
            }
        }

        return copy;
    }
    public Iterable<WorldState> neighbors() {
        // check around the zero tile
            // see if any of them around it are out of bounds (ignore if they are)
        // the tile at the row -1 of zeroTile moves down
        // the tile at col -1 of zeroTile moves to the right
        // the tile at row + 1 moves up
        // tile at col + 1 moves to the left

        //loop through current tiles and move one tile up
            // put this in my new double nested array
                // send through board constructor
            // have up to 4 of these
            // put all these double nested arrays into one list / arraylist
        //return arrayList

        int zeroTileRow = -1;
        int zeroTileCol = -1;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (tileAt(i, j) == 0) {
                    zeroTileRow = i;
                    zeroTileCol = j;
                }
            }
        }

        int[][] surroundingZeroTiles = new int[4][2];

        int aboveZeroTile[] = new int[2];
        surroundingZeroTiles[0] = aboveZeroTile;

        int belowZeroTile[] = new int[2];
        surroundingZeroTiles[1] = aboveZeroTile;

        int leftZeroTile[] = new int[2];
        surroundingZeroTiles[3] = aboveZeroTile;

        int rightZeroTile[] = new int[2];
        surroundingZeroTiles[4] = aboveZeroTile;


        aboveZeroTile[0] = zeroTileRow - 1;
        aboveZeroTile[1] = zeroTileCol;

        belowZeroTile[0] = zeroTileRow + 1;
        belowZeroTile[1] = zeroTileCol;

        leftZeroTile[0] = zeroTileRow;
        leftZeroTile[1] = zeroTileCol - 1;

        rightZeroTile[0] = zeroTileRow;
        rightZeroTile[1] = zeroTileCol + 1;

        List<WorldState> neighborBoards = new ArrayList<>();

        //go through all neighbors of 0 tile
        for (int i = 0; i < surroundingZeroTiles.length; i++) {
            // check if any are out of bounds
            int neighborRow = surroundingZeroTiles[i][0];
            int neighborCol = surroundingZeroTiles[i][1];
            if (neighborRow < 0 || neighborCol < 0 || neighborRow > tiles.length - 1 ||
                    neighborCol > tiles.length - 1) {
                continue;
            }

            int[][] tileCopy = copyTiles(tiles);
            tileCopy[zeroTileRow][zeroTileCol] = tiles[neighborRow][neighborCol];
            tileCopy[neighborRow][neighborCol] = tiles[zeroTileRow][zeroTileCol];

            Board neighborBoard = new Board(tileCopy);
            neighborBoards.add(neighborBoard);

        }

        return neighborBoards;
    }

    public int hamming() {
        // loop through double nested array
        // keep a counter when the things are at the right position
        // subtract one at the end
        // return counter
        int counter = 0;
        int positionCounter = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                positionCounter++;
                if (tiles[i][j] == 0) {
                    continue;
                }
                if (tiles[i][j] != positionCounter) {
                    counter++;
                }
            }
        }
        return counter;

    }
    public int manhattan() {
        int manhattanCounter = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                int currentTile = tiles[i][j];
                if (currentTile == 0) {
                    continue;
                }

                int targetRow = (currentTile - 1) / tiles.length;
                int targetCol = (currentTile - 1) % tiles.length;

                manhattanCounter += Math.abs(targetRow - i);
                manhattanCounter += Math.abs(targetCol - j);
            }
        }

        return manhattanCounter;

    }
    public int estimatedDistanceToGoal() {
        return manhattan();

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(tiles);
    }

    public boolean equals(Object y) {
        if (y instanceof Board) {
            Board otherBoard = (Board) y;

            if (otherBoard.size != this.size) {
                return false;
            }

            for (int i = 0; i < otherBoard.size; i++) {
                for (int j = 0; j < otherBoard.size; j++) {
                    if (otherBoard.tiles[i][j] != this.tiles[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    public static void main(String[] args) {
        int[][] grid = { { 5, 8, 7}, {1, 4, 6}, {3, 0, 2} };
        Board board = new Board(grid);
        board.manhattan();
    }
}


