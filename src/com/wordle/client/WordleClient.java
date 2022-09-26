package com.wordle.client;

import com.wordle.Board;
import com.wordle.Display;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

class WordleClient {
    private final static boolean DEBUG_MODE = false; // shows answer
    private final static String HISTORY_PATH = "history.txt";

    // TODO
    public static void main(String[] args) throws Exception {
        Board board = new Board("words.txt", 6);

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
        input.close();
        record(board);
    }

    // record method
    private static void record(Board board) throws Exception {
        Scanner reader = new Scanner(new File(HISTORY_PATH));
        String gamesPlayed = reader.nextLine();
        String gamesWon = reader.nextLine();
        String winningWord = "";

        while (reader.hasNextLine()) {
            winningWord += reader.nextLine() + '\n';
        }
        reader.close();

        int numGamesPlayed = Integer.parseInt(gamesPlayed
                .substring(gamesPlayed.indexOf(": ") + 2).trim() + 1);
        int numGamesWon = Integer.parseInt(gamesWon
                .substring(gamesWon.indexOf(": ") + 2).trim());

        if (board.hasWon()) {
            numGamesWon += 1;
            winningWord += board.getAnswer();
        }

        Files.writeString(Path.of(HISTORY_PATH),
                String.format("GamesPlayed: %d\n GamesWon: %d\n WinningWord: %s"
                        , numGamesPlayed, numGamesWon, winningWord));
    }
}