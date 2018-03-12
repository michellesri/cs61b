package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.*;
import java.util.List;


public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 100;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {

        StdDraw.setCanvasSize(40 * 16, 40 * 16);
        displayMainMenu();

        while (true) {}

        // TODO: save game here
    }

    public void startGame() {
        Random rand = new Random(200);
        ter.initialize(WIDTH, HEIGHT);
        // initialize tiles
        TETile[][] world = generateWorld(rand);

        Position position = new Position(rand.nextInt(WIDTH), rand.nextInt(HEIGHT));
        while (world[position.x][position.y] != Tileset.FLOOR) {
            position = new Position(rand.nextInt(WIDTH), rand.nextInt(HEIGHT));
        }

        Player player = new Player(position);

        world[player.pos.x][player.pos.y] = Tileset.PLAYER;

        ter.renderFrame(world);

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char currentKey = StdDraw.nextKeyTyped();
                if (currentKey == ':') {
                    while (!StdDraw.hasNextKeyTyped()) {
                    }
                    if (Character.toLowerCase(StdDraw.nextKeyTyped()) == 'q') {
                        break;
                    }
                } else {
                    world[player.pos.x][player.pos.y] = Tileset.FLOOR;
                    player.movePlayer(currentKey, world, WIDTH, HEIGHT);

                    world[player.pos.x][player.pos.y] = Tileset.PLAYER;
                    ter.renderFrame(world);
                }
            }
        }
    }

    public void displayMainMenu() {
        System.out.println(" in draw frame");
        StdDraw.clear();
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        Font font = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH/2, HEIGHT/2, "Quit (Q)");
        StdDraw.text(WIDTH/2, HEIGHT/2 + HEIGHT/10, "Load Game (L)");
        StdDraw.text(WIDTH/2, HEIGHT/2 + 2 * HEIGHT/10, "New Game (N)");

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.show();
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        Random rand = new Random(200);
        TETile[][] finalWorldFrame = generateWorld(rand);

        return finalWorldFrame;


    }

    public static TETile[][] generateWorld(Random rand) {

        int roomNum = rand.nextInt(50);
        List<RectangularRoom> existingRooms = new ArrayList<>();
        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        while (existingRooms.size() < roomNum) {
            RectangularRoom newRoom = RectangularRoom.generateRoom(rand, HEIGHT, WIDTH);

            if (checkRoomAgainstExistingRooms(world, newRoom, existingRooms)) {
                // if the room doesn't overlap, then we add the room.
                existingRooms.add(newRoom);
            }
        }

        List<Hall> halls = new ArrayList<>();

        for (int i = 0; i < existingRooms.size(); i += 2) {
            RectangularRoom currentRoom = existingRooms.get(i);
            RectangularRoom targetRoom;
            if (i == existingRooms.size() - 1) {
                targetRoom = existingRooms.get(0);
            } else {
                targetRoom = existingRooms.get(i + 1);
            }

            halls.addAll(Hall.generateHalls(rand, currentRoom, targetRoom));
        }

        for (RectangularRoom room : existingRooms) {
            insertRoomToWorld(world, room);
        }

        for (Hall hall : halls) {
            insertHallToWorld(world, hall);
        }

        return world;
    }

    public static boolean checkRoomAgainstExistingRooms(TETile[][] world, RectangularRoom newlyGeneratedRoom,
                                                     List<RectangularRoom> existingRooms) {
        // returns true if the room already exists. we need to make a new room.
        for (RectangularRoom room : existingRooms) {
            if (roomOverlapCheck(world, newlyGeneratedRoom, room)) {
                return false;
            }
        }
        return true;
    }

    public static void insertRoomToWorld(TETile[][] world, RectangularRoom newRoom) {
        for (int x = 0; x < newRoom.width; x += 1) {
            for (int y = 0; y < newRoom.height; y += 1) {
                world[newRoom.pos.x + x][newRoom.pos.y + y] = Tileset.TREE;
            }
        }

        for (int x = 1; x < newRoom.width -1; x += 1) {
            for (int y = 1; y < newRoom.height - 1; y+= 1) {
                world[newRoom.pos.x + x][newRoom.pos.y + y] = Tileset.FLOOR;

            }
        }
    }

    public static void insertHallToWorld(TETile[][] world, Hall newHall) {
        if (newHall.isHorizontal) {
            if (newHall.length < 0) {
                for (int x = 0; x >= newHall.length; x -= 1) {
                    setWall(world, newHall.pos.x + x, newHall.pos.y + 1);
                    world[newHall.pos.x + x][newHall.pos.y] = Tileset.FLOOR;
                    setWall(world, newHall.pos.x + x, newHall.pos.y - 1);
                }
            } else {
                for (int x = 0; x <= newHall.length; x += 1) {
                    setWall(world, newHall.pos.x + x, newHall.pos.y + 1);
                    world[newHall.pos.x + x][newHall.pos.y] = Tileset.FLOOR;
                    setWall(world, newHall.pos.x + x, newHall.pos.y - 1);
                }
            }
        } else {
            if (newHall.length < 0) {
                for (int y = 0; y >= newHall.length; y -= 1) {
                    setWall(world, newHall.pos.x + 1, newHall.pos.y + y);
                    world[newHall.pos.x][newHall.pos.y + y] = Tileset.FLOOR;
                    setWall(world, newHall.pos.x - 1, newHall.pos.y + y);
                }
            } else {
                for (int y = 0; y <= newHall.length; y += 1) {
                    setWall(world, newHall.pos.x + 1, newHall.pos.y + y);
                    world[newHall.pos.x][newHall.pos.y + y] = Tileset.FLOOR;
                    setWall(world, newHall.pos.x - 1, newHall.pos.y + y);
                }
            }
        }
    }

    public static void setWall(TETile[][] world, int x, int y) {
        if (world[x][y] == Tileset.FLOOR) return;
        world[x][y] = Tileset.TREE;
    }

    public static boolean roomOverlapCheck(TETile[][] world, RectangularRoom room1, RectangularRoom room2) {
        // returns true if rooms overlap

        int room1startX = room1.pos.x;
        int room1endX = room1.pos.x + room1.width;

        int room1startY = room1.pos.y;
        int room1endY = room1.pos.y + room1.width;

        int room2startX = room2.pos.x;
        int room2endX = room2.pos.x + room2.width;

        int room2startY = room2.pos.y;
        int room2endY = room2.pos.y + room2.width;

        for (int x = room1startX; x < room1endX; x += 1) {
            if (x < room2endX && x > room2startX) {
                for (int y = room1startY; y < room1endY; y += 1) {
                    if (y < room2endY && y > room2startY) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
