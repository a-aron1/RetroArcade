package com.retroarcade.wordle;

import com.apps.util.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Screens {

    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static String instruct;
    public static String winBanner = "resources/Banners/winBanner.txt";
    public static String lostBanner;

    public static void startScreen() throws IOException {
        welcomeBanner();
        getUserInput();
        chooseScreen();
    }

    static {
        try {
            winBanner = Files.readString(Path.of("resources/Banners/winBanner.txt")) ;
            lostBanner = Files.readString(Path.of("resources/Banners/lostBanner.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getUserInput() throws IOException {
        System.out.println("\n" + PURPLE + "                                       Enter choose an option:\n");
        System.out.println(CYAN + "                        [P]lay Wordle Game now" + PURPLE + "  |  Review the [I]nstructions\n");
        // prompter for user input
    }

    public static void welcomeBanner() throws IOException {
        String welcome = Files.readString(Path.of("resources/Banners/welcome.txt"));
        String worlde = Files.readString(Path.of("resources/Banners/welcomeBanner.txt"));
        System.out.print(welcome + GREEN);
        System.out.print(worlde + YELLOW);
        System.out.println("");
    }

    public static void chooseScreen() throws IOException {
        instruct = Files.readString(Path.of("resources/Banners/instructions.txt")).trim();
        Scanner scan = new Scanner(System.in);
        System.out.println(PURPLE + "Hit enter or type " + CYAN + "[p]" + PURPLE + " to play or [i] to see the instructions: " + RESET);

        //System.out.printf("");

        String menu = scan.nextLine();

        if (menu.equals("I") || menu.equals("i")){
            System.out.println(BLUE + instruct + RESET);
            Console.pause(10000);
            Console.blankLines(2);
            System.out.println("Hope that helped.  Now let's play!"); // adds space to output
            Console.blankLines(2);
        }

        if (menu.equals("P") || menu.equals("p")){
            System.out.println("\nLet's get started!\n" + RESET);
        }
    }
}