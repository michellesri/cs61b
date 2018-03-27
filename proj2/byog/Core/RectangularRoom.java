package byog.Core;

import java.util.Random;

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
}
