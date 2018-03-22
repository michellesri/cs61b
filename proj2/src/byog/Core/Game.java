package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;


public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 100;
    public static final int HEIGHT = 30;
    public static final String SAVED_FILE_NAME = "saved-game.txt";


    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {

        displayMainMenu();
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char currentKey = Character.toLowerCase(StdDraw.nextKeyTyped());
                interpretMenuKeys(currentKey);
            }
        }
    }

    public void interpretMenuKeys(char input) {

        if (input == 'n') { // new game
            Random rand = new Random(askUserForSeed());
            TETile[][] world = generateWorld(rand);
            Player player = initializePlayer(world, rand);
            startGameWithKeyboard(world, player);
        } else if (input == 'l') { // load game
            loadGameWithKeyboard();
        } else if (input == 'q') { // quit
            terminateGame();
        }
    }

    public char readUserMenuSelection(String input) {
        return input.charAt(0);
    }

    public int readSeed(String input) {
        String[] inputs = input.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        return Integer.parseInt(inputs[1]); // return the seed
    }

    public String readUserMovements(String input) {
        String[] inputs = input.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        return inputs[2];
    }

    public void terminateGame() {
        System.exit(0);
    }

    public int askUserForSeed() {
        StdDraw.clear();
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        Font font = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH/2, HEIGHT/2, "Please enter a seed");

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.show();

        String seed = "";
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char currentKey = Character.toLowerCase(StdDraw.nextKeyTyped());
                if (currentKey == 's') {
                    break;
                }
                if (Character.isDigit(currentKey)) {
                    seed += currentKey;
                }
            }
        }
        return Integer.parseInt(seed);
    }

    public Player initializePlayer(TETile[][] world, Random rand) {
        Position position = new Position(rand.nextInt(WIDTH), rand.nextInt(HEIGHT));
        while (world[position.x][position.y] != Tileset.FLOOR) {
            position = new Position(rand.nextInt(WIDTH), rand.nextInt(HEIGHT));
        }

        Player player = new Player(position);

        world[player.pos.x][player.pos.y] = Tileset.PLAYER;

        return player;

    }

    public void startGameWithUserInput(TETile[][] world, String userMovements, Player player) {

        for (int i = 0; i < userMovements.length(); i++) {
            char currentKey = userMovements.charAt(i);
            if (currentKey == ':') {
                char nextKey = userMovements.charAt(i + 1);
                if (nextKey == 'q') {
                    break;
                }
            } else {
                world[player.pos.x][player.pos.y] = Tileset.FLOOR;
                player.movePlayer(currentKey, world, WIDTH, HEIGHT);
                world[player.pos.x][player.pos.y] = Tileset.PLAYER;
            }
        }

        saveGame(player, world);
    }

    public Player createPlayer(Position pos) {
        return new Player(pos);
    }

    public TETile[][] insertPlayerToWorld(TETile[][] world, Player player) {
        world[player.pos.x][player.pos.y] = Tileset.PLAYER;

        return world;
    }

    public void startGameWithKeyboard(TETile[][] world, Player player) {

        // initialize tiles
        ter.initialize(WIDTH, HEIGHT);
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
        saveGame(player, world);
        displayMainMenu();
    }

    public void loadGameWithKeyboard() {
        GameState loadedGame = readObjectFromFile(SAVED_FILE_NAME);
        startGameWithKeyboard(loadedGame.world, loadedGame.player);
    }

    public TETile[][] loadGameWithUserInput(String userMovements) {
        GameState loadedGame = readObjectFromFile(SAVED_FILE_NAME);
        startGameWithUserInput(loadedGame.world, userMovements, loadedGame.player); // needs to take userMovements
        return loadedGame.world;

    }

    public GameState readObjectFromFile(String filepath) {

        try {

            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();
            objectIn.close();
            return (GameState) obj;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void saveGame(Player player, TETile[][] world) {
        try {
            FileOutputStream fout = new FileOutputStream(SAVED_FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fout);

            GameState gameState = new GameState(player, world);
            oos.writeObject(gameState);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // we should figure out if there are other inputs, and then start from beginning
        // laasd
        // load game, start
    }

    public void displayMainMenu() {
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

        input = input.toLowerCase();

        char menuSelection = readUserMenuSelection(input);

        if (menuSelection == 'n') { // new game
            int userSeed = readSeed(input);
            String userMovements = readUserMovements(input);
            Random rand = new Random(userSeed);
            TETile[][] finalWorldFrame = generateWorld(rand);

            Player player = initializePlayer(finalWorldFrame, rand);
            startGameWithUserInput(finalWorldFrame, userMovements, player);
            return finalWorldFrame;

        } else if (menuSelection == 'l') { // load game
            String userMovements = input.substring(1);
            return loadGameWithUserInput(userMovements);

        } else if (menuSelection == 'q') { // quit
//            terminateGame();
            return null;
        }

        throw new RuntimeException();
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
