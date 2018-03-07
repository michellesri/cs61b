package byog.Core;

public class Hall {
    int height;
    int width;
    Position pos;

    public Hall(int height, int width, Position position) {
        this.height = height;
        this.width = width;
        this.pos = position;
    }

    public static Hall generateHorizontalHall(RectangularRoom room1, RectangularRoom room2) {
        int hallDistance = Math.abs(room1.roomCenter.x - room2.roomCenter.x);
        Position startPosition = new Position(room1.roomCenter.x, room1.roomCenter.y);
        return new Hall(1, hallDistance, startPosition);

    }

//    public static void insertHall(Position pos, Hall hall) {
//
//    }
}
