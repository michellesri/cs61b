package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;

    public static void addHexagon(TETile[][] world, int s, int positionX, int positionY) {

        int x = positionX;
        int y = positionY;
        int size = s;

        for (int i = 0; i < s; i++) {
            drawLine(world, size, x, y);
            if (i < s - 1) {
                x -= 1;
                y += 1;
                size += 2;
            } else {
                y += 1;
            }
        }

        for (int i = 0; i < s; i++) {
            drawLine(world, size, x, y);
            x += 1;
            y += 1;
            size -= 2;
        }


    }

    public static TETile[][] drawLine(TETile[][] world, int s, int positionX, int positionY) {
        // helper function that draws one line given a position
        for (int i = 0; i < s; i++) {
            world[positionX + i][positionY] = Tileset.TREE;
        }
        return world;
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.GRASS;
            }
        }

        addHexagon(world, 3, 10, 20);
        addHexagon(world, 2, 5, 10);

        ter.renderFrame(world);

    }
}


