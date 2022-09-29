package com.retroarcade.wordle;

import com.apps.util.Console;

public class Display {
    private static String leftPadding = " ";

    private enum Color {
        FG_BLACK_BG_GREEN("\033[0;102m", "\u001B[30m"),
        FG_BLACK_BG_YELLOW("\033[0;103m", "\u001B[30m"),
        FG_WHITE_BG_GREEN("\033[0;102m", "\u001B[37m"),
        FG_WHITE_BG_YELLOW("\033[0;103m", "\u001B[37m"),
        BG_GREEN("\033[0;102m"),
        BG_YELLOW("\033[0;103m"),
        BG_RED("\033[0;101m"),
        BG_BLACK("\033[0;100m"),
        BG_BLUE("\033[0;104m"),
        BG_PURPLE("\033[0;105m"),
        BG_CYAN("\033[0;106m"),
        BG_WHITE("\033[0;107m");

        private final static String RESET = "\u001B[0m";
        public static final String BOLD = "\u001B[1m";

        private final String background;
        private String foreground;

        private Color(String background) {
            this.background = background;
        }

        private Color(String background, String foreground) {
            this(background);
            this.foreground = foreground;
        }
    }

    public static void render(Board board) {
        Console.blankLines(2);
        for (int i = 0; i < board.getHeight(); i++) {
            if (i < board.countGuesses()) {
                Display.printComparison(board.numGuess(i), board.getAnswer(), " | ");
            }
            else {
                paddedPrint("  | ".repeat(board.getWidth() - 1));
            }

            if (i != board.getHeight()) {
                paddedPrint("--".repeat(board.getWidth() * 2 - 2) + "-\n");
            }
            else {
                paddedPrint("");
            }
        }
    }

    public static void promptForWord() {
        System.out.print("Enter your best guess (five-letter word):  ");
        Console.blankLines(1);
    }

    public static void printAnswer(String str) {
        System.out.println("The answer to today's Wordle is:  " + str);
        Console.blankLines(1);
    }

    // print helpers
    private static void printComparison(String toPrint, String toCompare, String limit) {
        String output = "";
        for (int n = 0; n < toPrint.length(); n++) {
            char numChar = toPrint.charAt(n);

            if (n < toCompare.length() && numChar == toCompare.charAt(n)) {
                output += highlightText(Character.toString(numChar), Color.FG_BLACK_BG_GREEN);
            }
            else if (toCompare.contains(String.valueOf(numChar))) {
                output += highlightText(Character.toString(numChar), Color.FG_BLACK_BG_YELLOW);
            }
            else {
                output += numChar;
            }

            if (n != toPrint.length() - 1) {
                output += limit;
            }
        }
        paddedPrint(output);
    }

    private static void paddedPrint(String str) {
        System.out.println(leftPadding + str);
    }

    // coloring
    private static String highlightText(String text, Color toHighlight) {
        return toHighlight.background + toHighlight.foreground + Color.BOLD + text + Color.RESET;
    }
}