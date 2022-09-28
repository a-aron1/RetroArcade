package com.retroarcade.wordle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Screens {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static String input;
    public static String instruct;
    private final static Timer TIMER = new Timer();

    public static String getInput() {
        return input;
    }

    public static void setInput(String input) {
        Screens.input = input;
    }

    public static TimerTask menuTimer = new TimerTask() {
        public void run(){
            System.out.println("");
            try {
                getUserInput();
                chooseScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }

        };
    };


    public static void startScreen() throws IOException {
        welcomeBanner();
        getUserInput();
        chooseScreen();
    }

    public static void getUserInput() throws IOException {
        System.out.println("\n" + ANSI_PURPLE + "                                       Enter an option from below");
        System.out.println(ANSI_PURPLE + "                     Enter: [P]: Play Game |  [L]: Leader board] |  [I]: Instructions\n");
        // prompter for user input
    }

    public static void welcomeBanner() throws IOException {
        String welcome = Files.readString(Path.of("resources/Banners/welcome.txt"));
        String worlde = Files.readString(Path.of("resources/Banners/welcomeBanner.txt"));
        System.out.print(welcome + ANSI_GREEN);
        System.out.print(worlde + ANSI_YELLOW);
        System.out.println("");
    }

    public static void chooseScreen() throws IOException {
        instruct = Files.readString(Path.of("resources/Banners/instructions.txt")).trim();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter: ");
        String menu = scan.next();

        if (menu.equals("I")){
            System.out.println(ANSI_BLUE + instruct);
            java.util.Timer t = new Timer();
            TIMER.scheduleAtFixedRate(menuTimer, 4000, 1000000);
            System.out.println("\n\n"); // adds space to output
        }

        if (menu.equals("P")){
            //Display.render(board);
            //playGame();
            System.out.println("Let's get started!");
        }

        if (menu.equals("S")){
            System.out.println("stats to be printed here");
            TIMER.scheduleAtFixedRate(menuTimer, 4000, 1000000);
            chooseScreen();
        }
    }
}