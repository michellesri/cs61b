package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Player {
    Position pos; // position of the current character

    public Player(Position pos) {
        this.pos = pos;
    }

    public void movePlayer(char userInput, TETile[][] world, int worldWidth, int worldHeight) {
        userInput = Character.toLowerCase(userInput);
        if (userInput == 'w') {
            if (worldHeight >= this.pos.y + 1) {
                if (world[this.pos.x][this.pos.y + 1] == Tileset.FLOOR) {
                    this.pos.y++;
                }
            }

        } else if ()
    }

    public boolean isPositionMovable(int x, int y, int worldWidth, int worldHeight, TETile[][] world) {
        return (x >= 0 && y >= 0 && x < worldWidth && y < worldHeight && world[x][y] == Tileset.FLOOR);
    }
}
