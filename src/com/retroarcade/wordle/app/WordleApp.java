package com.retroarcade.wordle.app;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.retroarcade.wordle.Board;
import com.retroarcade.wordle.Display;
import static com.retroarcade.wordle.Display.*;
import static com.retroarcade.wordle.Screens.*;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Timer;

public class WordleApp {
    // statics
    private final static boolean DEBUG_MODE = true; // displays the answer for testing purposes
    private final static String HISTORY_PATH = "data/history.txt"; // path to the history file
    private final static Timer TIMER = new Timer();

    // fields
    private long startTime = System.currentTimeMillis();

    // initalize the WordleBoard and Scanner for user input.
    private final Board board = new Board("resources/words.txt", 6);
    private Scanner input = new Scanner(System.in); // reads console input
    Prompter prompter = new Prompter(new Scanner(System.in));

    public WordleApp() throws IOException {
    }

    public void execute() throws Exception {
        // menuTimer();
        welcome(); // chooseScreen();
        playGame();  // runGame();
        clearBoard();
        updateBoard();
        showAnswer();
        recordGame(board);
        gameResult();
        //showStats();  // goodbye();
    }

    private void welcome() throws IOException {
        startScreen();
        //playGame();
    }

    private void playGame() {
        runGame();
    }

    private void runGame() {
        while (!board.isGameOver()) {
            clearBoard();
            Display.render(board);

            if (DEBUG_MODE) {
                printAnswer(board.getAnswer());
                System.out.println(); // empty line in output
            }

            Display.promptForWord();
            board.guess(input.nextLine().toUpperCase());
        }
    }

    private void clearBoard() {
        Console.clear();
    }

    private void updateBoard() {
        Display.render(board);
    }

    private void showAnswer() {
        printAnswer(board.getAnswer());
    }

    /*
     * WordleBoard - Takes a board to record game results
     * The gamesPlayed counter at the HISTORY_PATH increments
     * If won, gamesWon increments and the winning word is recorded
     */
    private void recordGame(Board board) throws Exception {
        Scanner reader = new Scanner(new File(HISTORY_PATH));
        String gamesPlayed = reader.nextLine();
        String gamesWon = reader.nextLine();
        String winningWord = "";
        while (reader.hasNextLine()) {
            winningWord += reader.nextLine() + '\n';
        }
        reader.close();

        int numGamesPlayed = Integer.parseInt(gamesPlayed
                .substring(gamesPlayed.indexOf(": ") + 2).trim()) + 1;
        int numGamesWon = Integer.parseInt(gamesWon
                .substring(gamesWon.indexOf(": ") + 2).trim());

        if (board.hasWon()) {
            numGamesWon += 1;
            winningWord += board.getAnswer();
        }

        Files.writeString(Path.of(HISTORY_PATH),
                String.format("GamesPlayed: %d\nGamesWon: %d\n%s"
                        , numGamesPlayed, numGamesWon, winningWord));
    }

    private void gameResult() {
        int tries = board.countGuesses();
        long endTime = System.currentTimeMillis();
        String numTries = (tries < 2) ? " try!\n" : " tries!\n";

        if (board.hasWon()) {
            System.out.println(ANSI_GREEN+winBanner );
            System.out.println("\nNice work!\nYou found the answer in "
                    + ((endTime - startTime) / 1000)
                    + " seconds...\nAnd in just "
                    + tries + numTries + "Impressive!\n");
            System.out.println(ANSI_RESET);
        }
        else {
            System.out.println(ANSI_RED+lostBanner);
            System.out.println("\nYou lost, in "
                    + ((endTime - startTime) / 1000)
                    + " seconds.  \n  \nBetter luck next time!\n");
            System.out.println(ANSI_RESET);
        }
        System.out.println("Start preparing:  The next Wordle comes out in "); //+ countDown());
    }
}