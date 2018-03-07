package byog.Core;

import byog.TileEngine.TETile;

import java.util.Random;
import java.util.Set;

public class RectangularRoom {
    int height;
    int width;
    Position pos;

    public RectangularRoom(int height, int width, Position pos) {
        this.height = height;
        this.width = width;
        this.pos = pos;
    }

    public static RectangularRoom generateRoom(Random rand, int gameHeight, int gameWidth) {
        int roomHeight = rand.nextInt(gameHeight / 4 - 3) + 3;
        int roomWidth = rand.nextInt(gameWidth / 4 - 3) + 3;

        int positionX = rand.nextInt(gameWidth - roomWidth);
        int positionY = rand.nextInt(gameHeight - roomHeight);

        Position newPosition = new Position(positionX, positionY);

        RectangularRoom myRoom = new RectangularRoom(roomHeight, roomWidth, newPosition);

        return myRoom;
    }
//
//    public static RectangularRoom generateHorizontalHall(RectangularRoom room1, RectangularRoom room2) {
//        // given two rooms, generate a hall for them.
//
//        Position roomCenter = new Position(roomWidth / 2, 1);
//
//
//        int minDistance = Math.abs(room2.pos.x - room1.pos.x); //min distance needed to reach other room
//        int xDistance = Math.abs(room1.roomCenter.x - room2.roomCenter.x);
//        int yDistance = Math.abs(room1.roomCenter.y - room2.roomCenter.y);
//        new RectangularRoom(1, xDistance, room1.roomCenter,
//    }
}
