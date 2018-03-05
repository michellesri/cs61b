//package byog.Core;
//
//import byog.TileEngine.TETile;
//
//import java.util.Random;
//
//
//public class Hall {
//
//    int length;
//    int positionX;
//    int positionY;
//
//    public Hall(int length, int positionX, int positionY) {
//
//        this.length = length;
//        this.positionX = positionX;
//        this.positionY = positionY;
//    }
//
//    public static Hall generateHall(Random rand, int positionX, int positionY) {
//        final int hallHeight = 1;
//        int hallLength = rand.nextInt(gameWidth / 4);
//
//        int positionX = rand.nextInt(gameWidth - roomWidth);
//        int positionY = rand.nextInt(gameHeight - roomHeight);
//
//        RectangularRoom myRoom = new RectangularRoom(roomHeight, roomWidth, positionX, positionY);
//        return myRoom;
//    }
//}
