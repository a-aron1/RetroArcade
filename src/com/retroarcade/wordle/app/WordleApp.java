package com.retroarcade.wordle.app;

import com.apps.util.Console;
import com.retroarcade.wordle.Board;
import com.retroarcade.wordle.Display;
import static com.retroarcade.wordle.Display.*;
import static com.retroarcade.wordle.Screens.*;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Scanner;

public class WordleApp {
    // statics
    private final static boolean DEBUG_MODE = false; // displays the answer for testing purposes
    private final static String HISTORY_PATH = "data/history.txt";

    // fields
    private long startTime = System.currentTimeMillis();

    // initalize board and scanner for user input
    private final Board board = new Board("resources/words.txt", 6);
    private Scanner input = new Scanner(System.in);

    public WordleApp() throws IOException {
    }

    public void execute() throws Exception {
        welcome();
        playGame();
        clearBoard();
        updateBoard();
        showAnswer();
        recordGame(board);
        gameResult();
        exitGame();
    }

    private void welcome() throws IOException {
        startScreen();
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

    private void recordGame(Board board) throws IOException {
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
        if (board.hasWon()) {
            gameWon();
        }
        else {
            gameLost();
        }
    }

    private void gameWon() {
        int tries = board.countGuesses();
        long endTime = System.currentTimeMillis();
        String numTries = (tries < 2) ? " try!\n" : " tries!\n";

        System.out.println(GREEN + winBanner + RESET);
        Console.pause(1000);
        Console.blankLines(1);
        System.out.println(YELLOW + "Nice work!" + RESET);
        Console.pause(1000);
        Console.blankLines(1);
        System.out.println(CYAN + "You found the answer in " + ((endTime - startTime) / 1000) + " seconds." + RESET);
        Console.pause(1000);
        Console.blankLines(1);
        System.out.println(YELLOW + "And in just " + tries + numTries + RESET);
        Console.pause(1000);
        Console.blankLines(1);
        System.out.println(RED + "SERIOUSLY IMPRESSIVE!" + RESET);
        Console.pause(1000);
        Console.blankLines(1);
    }

    private void gameLost() {
        long endTime = System.currentTimeMillis();

        Console.blankLines(1);
        System.out.println(RED + lostBanner + RESET);
        Console.pause(1000);
        Console.blankLines(1);
        System.out.println(RED + "Now that was quick...  :(" + RESET);
        Console.pause(1000);
        Console.blankLines(1);
        System.out.println(CYAN + "You lost in " + ((endTime - startTime) / 1000) + " seconds." + RESET);
        Console.pause(1000);
        Console.blankLines(1);
        System.out.println(YELLOW + "Better luck next time!" + RESET);
        Console.pause(1000);
        Console.blankLines(1);
    }

    private void exitGame() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        ZoneId pst = ZoneId.of("America/Los_Angeles") ;
        ZonedDateTime timeNow = ZonedDateTime.now(pst) ; // today's time in CST zone
        LocalDate timeTomorrow = now.toLocalDate().plusDays( 1 ) ;
        ZonedDateTime tomorrowStart = timeTomorrow.atStartOfDay(pst) ; // tomorrow at midnight
        Duration timeDifference = Duration.between(now.toInstant(), tomorrowStart.toInstant()); // gets the time difference

        // reformats/parses the Duration into more readable format
        String timeToNextWordle = String.format(" %d Hours : %02d Minutes: %02d Seconds "
                , timeDifference.getSeconds() / 3600
                , (timeDifference.getSeconds() % 3600) / 60
                , (timeDifference.getSeconds() % 60));

        //System.out.println(PURPLE PURPLE+ "Start preparing now:  The next Wordle comes out at midnight!" + RESET);
        Console.pause(1000);
        Console.blankLines(1);

        if (board.hasWon()) {
            System.out.println(GREEN + "A new Wordle comes out in "
                    + PURPLE + timeToNextWordle
                    + GREEN + "- Hope to see you tomorrow!");
        }
        else {
            System.out.println(GREEN + "Chin up -- A new Worlde comes out in "
                    + PURPLE + timeToNextWordle
                    + GREEN + "- Hope to see you tomorrow!");
        }
        System.exit(0);
    }
}

/* TODO - showStats()

    private void showStats() throws IOException {
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
     */