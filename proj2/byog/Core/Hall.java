package byog.Core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hall {
    Position pos; // the starting position inside one room
    boolean isHorizontal;
    int length;

    public Hall(Position position, boolean isHorizontal, int length) {
        this.pos = position;
        this.isHorizontal = isHorizontal;
        this.length = length;
    }

    public static List<Hall> generateHalls(Random rand, RectangularRoom room1, RectangularRoom room2) {
        // return a list of halls that connects these two rooms

        int room1PositionX = rand.nextInt(room1.width - 2) + room1.pos.x + 1;
        int room1PositionY = rand.nextInt(room1.height - 2) + room1.pos.y + 1;

        int room2PositionX = rand.nextInt(room2.width - 2) + room2.pos.x + 1;
        int room2PositionY = rand.nextInt(room2.height - 2) + room2.pos.y + 1;

        Position startPositionX = new Position(room1PositionX, room1PositionY);
        int hallDistanceX = room2PositionX - room1PositionX;

        Position startPositionY = new Position(room1PositionX + hallDistanceX, room1PositionY);
        int hallDistanceY = room2PositionY - room1PositionY;

        Hall horizontalHall = new Hall(startPositionX, true, hallDistanceX);
        Hall verticalHall = new Hall(startPositionY, false, hallDistanceY);

        List<Hall> hallList = new ArrayList<>();
        if (horizontalHall.length != 0) {
            hallList.add(horizontalHall);
        }
        if (verticalHall.length != 0) {
            hallList.add(verticalHall);

        }
        return hallList;

    }

//    public static void insertHall(Position pos, Hall hall) {
//
//    }
}
