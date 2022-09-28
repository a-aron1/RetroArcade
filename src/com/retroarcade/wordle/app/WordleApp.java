package com.retroarcade.wordle.app;

import com.retroarcade.wordle.Board;
import com.retroarcade.wordle.Display;
import static com.retroarcade.wordle.Display.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

// TODO:  refactor with controller code

public class WordleApp {
    // initalize the WordleBoard and Scanner for user input.
    private final Board board = new Board("resources/words.txt", 6);
    private Scanner input = new Scanner(System.in); // reads console input

    // statics
    private final static boolean DEBUG_MODE = false; // displays the answer for testing purposes
    private final static String HISTORY_PATH = "data/history.txt"; // path to the history file

    public WordleApp() throws IOException {
    }

    public void execute() throws Exception {
        /* TODO if time allows
        welcome();
        promptForName();

        mainMenu();
        runGame();
            while
                clear()
                showBoard();
                debugMode(); // showAnswer()
                clearBoard();
                promptForWord(); // both prompt and board.guess(...)
        clear()
        showBoard()
        recordResults()
        winScreen()
        loseScreen()
        showAnswer()
        timer()
        showStats()
        exit()
         */

        while (!board.isGameOver()) {
            Display.clear();
            Display.print(board);

            if (DEBUG_MODE) {
                printAnswer(board.getAnswer());
            }

            Display.promptForWord();
            board.guess(input.nextLine().toUpperCase());
        }

        Display.clear();
        Display.print(board);
        printAnswer(board.getAnswer());
        recordGame(board);

        // TODO:  ask user if they would like to play again or exit the game
    }

    // ARGS: (WordleBoard) Takes a board to record it's game.
    // The games played counter at the HISTORY_PATH is incremented. If the board was
    // completed successfully that is also recorded along with the winning word.
    public static void recordGame(Board board) throws Exception {
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
}