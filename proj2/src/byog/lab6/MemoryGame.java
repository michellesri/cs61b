package byog.lab6;

import edu.princeton.cs.introcs.StdAudio;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);

        game.startGame();
    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();


        // Initialize random number generator
        this.rand = new Random(seed);

    }

    public String generateRandomString(int n) {
        //Generate random string of letters of length n
        String randomString = "";
        int counter = 0;
        while (counter < n) {
            randomString += CHARACTERS[rand.nextInt(CHARACTERS.length)];
            counter++;
        }
        return randomString;
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
        System.out.println(s + " in draw frame");
        StdDraw.clear();
        Font font = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(20, 20, s);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        //Display each character in letters, making sure to blank the screen between letters
        String[] lettersArr = letters.split("");
        for (String letter : lettersArr) {
            System.out.println(letter + " in flash sequence");

            drawFrame(letter);
            StdDraw.pause(1000);
            StdDraw.clear();
            StdDraw.show();
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        //Read n letters of player input
        String constructedString = "";
        while (constructedString.length() < n) {
            if (StdDraw.hasNextKeyTyped()) {
                constructedString += StdDraw.nextKeyTyped();
                drawFrame(constructedString);
            }
        }

        return constructedString;
    }

    public void startGame() {
        //Set any relevant variables before the game starts
        round = 0;

        //Establish Game loop
        while (true) {
            round++;
            String randomString = generateRandomString(round);
            System.out.println(randomString + " random string");
            String roundStr = Integer.toString(round);
            drawFrame("Round: " + roundStr);
            StdDraw.pause(2000);


            flashSequence(randomString); //display the random string one letter at a time
            if (!solicitNCharsInput(round).equals(randomString)) {
                break;
            }

        }
        String gameOverString = "Game Over! You made it to round: " + round;
        drawFrame(gameOverString);

    }

}
