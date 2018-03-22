package byog.Core;

import byog.TileEngine.TETile;

import java.io.Serializable;

public class GameState implements Serializable {
    Player player;
    TETile[][] world;

    public GameState(Player player, TETile[][] world) {
        this.player = player;
        this.world = world;
    }

}
