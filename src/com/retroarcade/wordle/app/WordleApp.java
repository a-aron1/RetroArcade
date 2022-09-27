package com.retroarcade.wordle.app;

import com.retroarcade.wordle.Board;
import com.retroarcade.wordle.Display;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

// TODO:  refactor with controller code

public class WordleApp {
    private final static boolean DEBUG_MODE = false; // This DEBUG_MODE will display the answer for testing purposes.
    private final static String HISTORY_PATH = "data/history.txt"; // This contains the path to the history text file.

    public static void main(String[] args) throws Exception {
        // initalize the WordleBoard and Scanner for user input.
        Board board = new Board("resources/words.txt", 6);
        Scanner input = new Scanner(System.in);

        while (!board.isGameOver()) {
            Display.clear();
            Display.print(board);

            if (DEBUG_MODE) {
                Display.printAnswer(board.getAnswer());
            }

            Display.promptForWord();
            board.guess(input.nextLine().toLowerCase());
        }

        Display.clear();
        Display.print(board);
        Display.printAnswer(board.getAnswer());
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