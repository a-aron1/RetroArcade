package com.retroarcade.wordle;

import com.apps.util.Console;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Screens {

    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String RESET = "\u001B[0m";
    public static final String WHITE = "\u001B[37m";
    public static final String BLACK = "\u001B[30m";

    public static String instructTitle;
    public static String instructHeader;
    public static String instructBody;

    public static String winBanner;
    public static String lostBanner;

    static {
        try {
            winBanner = Files.readString(Path.of("resources/Banners/winBanner.txt")) ;
            lostBanner = Files.readString(Path.of("resources/Banners/lostBanner.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startScreen() throws IOException {
        welcomeBanner();
        getUserInput();
        chooseScreen();
    }

    public static void getUserInput() {
        System.out.println(WHITE + "                                          Choose an option:" + RESET);
        Console.blankLines(2);
        System.out.println(CYAN + "                        [P]lay Wordle Game now" + PURPLE + "  |  Read the [I]nstructions" + RESET);
        Console.blankLines(2);
    }

    public static void welcomeBanner() throws IOException {
        String welcome = Files.readString(Path.of("resources/Banners/welcome.txt"));
        String worlde = Files.readString(Path.of("resources/Banners/welcomeBanner.txt"));

        System.out.print(GREEN + welcome + RESET);
        Console.pause(1000);
        System.out.print(YELLOW + worlde + RESET);
        Console.blankLines(2);
    }

    public static void chooseScreen() {
        Scanner scan = new Scanner(System.in);
        System.out.println(CYAN + "Hit enter or type [p] to play, " + PURPLE + "or hit [i] to see the instructions..." + RESET);

        String menu = scan.nextLine();

        if (menu.equals("P") || menu.equals("p")){
            System.out.println("\nLet's get started!\n" + RESET);
        }

        if (menu.equals("I") || menu.equals("i")){
            try {
                instructions();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void instructions() throws IOException{
        instructTitle = Files.readString(Path.of("resources/Banners/instructionsTitle.txt"));
        instructHeader = Files.readString(Path.of("resources/Banners/instructionsHeading.txt"));
        instructBody = Files.readString(Path.of("resources/Banners/instructionsBody.txt"));

        System.out.println(BLUE + instructTitle + RESET);
        Console.pause(1000);
        Console.blankLines(2);
        System.out.println(YELLOW + instructHeader + RESET);
        Console.pause(1000);
        Console.blankLines(2);
        System.out.println(GREEN + instructBody + RESET);
        Console.pause(7700);
        Console.blankLines(2);
        System.out.println(PURPLE + "Hope that helped.  Now let's play!" + RESET);
        Console.blankLines(2);
    }
}