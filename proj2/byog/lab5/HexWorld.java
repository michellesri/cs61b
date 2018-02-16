package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;

    public static void addHexagon(TETile[][] world, int s, int positionX, int positionY) {
//        int counter = 0;

        drawLine(drawLine(world, s, positionX, positionY), s, positionX--, positionY--);
//        positionX -= 1;
//        positionY -= 1;
//        counter++;

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
        ter.renderFrame(world);

    }
}


