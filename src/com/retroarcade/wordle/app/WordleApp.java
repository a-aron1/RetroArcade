package com.retroarcade.wordle.app;

import com.retroarcade.wordle.Board;
import com.retroarcade.wordle.Display;
import static com.retroarcade.wordle.Display.*;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

// TODO:  refactor with controller code

public class WordleApp {
    // statics
    private final static boolean DEBUG_MODE = true; // displays the answer for testing purposes
    private final static String HISTORY_PATH = "data/history.txt"; // path to the history file

    // initalize the WordleBoard and Scanner for user input.
    private final Board board = new Board("resources/words.txt", 6);
    private Scanner input = new Scanner(System.in); // reads console input
    private long startTime = System.currentTimeMillis();

    public WordleApp() throws IOException {
    }

    public void execute() throws Exception {
        welcome();
        runGame();
        clearBoard();
        updateBoard();
        showAnswer();
        recordGame(board);
        gameResult();
        //showStats();
        //goodbye();
    }

    private void welcome() {
        String title = "=== WELCOME TO:  W O R D L E! ===\n\n============== By RetroArcade ===\n";
        System.out.println("\n" + title);
    }

    private void runGame() {
        while (!board.isGameOver()) {
            Display.clear();
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
        Display.clear();
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
            System.out.println("\nNice work!\nYou found the answer in "
                    + ((endTime - startTime) / 1000)
                    + " seconds...\nAnd in just "
                    + tries + numTries + "Impressive!\n");
        }
        else {
            System.out.println("\nYou lost, and in only "
                    + ((endTime - startTime) / 1000)
                    + " seconds.  \n Bummer :(\nBetter luck next time!\n");
        }
        System.out.println("Start preparing:  The next Wordle comes out in "); //+ countDown());
    }
}