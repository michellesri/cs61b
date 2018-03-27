package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;

public class Player implements Serializable {
    Position pos; // position of the current character

    public Player(Position pos) {
        this.pos = pos;
    }

    public void movePlayer(char userInput, TETile[][] world, int worldWidth, int worldHeight) {
        userInput = Character.toLowerCase(userInput);
        if (userInput == 'w') {
            if (isPositionMovable(this.pos.x, this.pos.y + 1, worldWidth, worldHeight, world)) {
                this.pos.y++;
            }
        } else if (userInput == 'a') {
            if (isPositionMovable(this.pos.x - 1, this.pos.y, worldWidth, worldHeight, world)) {
                this.pos.x--;
            }
        } else if (userInput == 's') {
            if (isPositionMovable(this.pos.x, this.pos.y - 1, worldWidth, worldHeight, world)) {
                this.pos.y--;
            }
        } else if (userInput == 'd') {
            if (isPositionMovable(this.pos.x + 1, this.pos.y, worldWidth, worldHeight, world)) {
                this.pos.x++;
            }
        }
    }

    public boolean isPositionMovable(int x, int y, int worldWidth, int worldHeight, TETile[][] world) {
        return (x >= 0 && y >= 0 && x < worldWidth && y < worldHeight &&
                world[x][y].character() == Tileset.FLOOR.character());
    }
}
