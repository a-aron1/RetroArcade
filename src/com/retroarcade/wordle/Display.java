package com.retroarcade.wordle;

public class Display {
    private static String title =
            "=== WELCOME TO:  W O R D L E! ===\n===== in java, By RetroArcade =====\n";
    private static String leftPadding = " ";

    private enum Color {
        GREEN("\033[0;102m"), // , "\u001B[32m"),
        YELLOW("\033[0;103m"), // , "\u001B[33m"),
        RED("\033[0;101m"), // , "\u001B[31m"),
        BLACK("\033[0;100m"), // , "\u001B[30m"),
        BLUE("\033[0;104m"), // , "\u001B[34m"),
        PURPLE("\033[0;105m"), // , "\u001B[35m"),
        CYAN("\033[0;106m"), // , "\u001B[36m"),
        WHITE("\033[0;107m");// , "\u001B[37m");

        private final String ansi_background;
        private final static String ANSI_RESET = "\u001B[0m";
        //ansi_foreground -- rename (add to ctor)

        private Color(String ansi_background) {
            this.ansi_background = ansi_background;
        }
    }

    // setters
    public static void setTitle(String str) {
        title = str;
    }

    public static void setLeftPadding(String str) {
        leftPadding = str;
    }

    // methods
    public static void print(Board board) {
        System.out.println(title);
        for (int i = 0; i < board.getHeight(); i++) {
            if (i < board.countGuesses()) {
                Display.printComparison(board.numGuess(i), board.getAnswer(), "|");
            }
            else {
                paddedPrint(" |".repeat(board.getWidth() - 1));
            }

            if (i != board.getHeight() - 1) {
                paddedPrint("-".repeat(board.getWidth() * 2 - 1));
            }
            else {
                paddedPrint("");
            }
        }
    }

    public static void promptForWord() {
        System.out.print("Enter your best guess (five-letter word):\n ");
    }

    public static void printAnswer(String str) {
        System.out.println("The answer is:\n " + str);
    }

    public static void clear() {
        System.out.print("\033\143");
        System.out.flush();
    }

    // print helpers
    private static void printComparison(String toPrint, String toCompare, String limit) {
        String output = "";
        for (int n = 0; n < toPrint.length(); n++) {
            char numChar = toPrint.charAt(n);

            if (n < toCompare.length() && numChar == toCompare.charAt(n)) {
                output += highlightText(Character.toString(numChar), Color.GREEN);
            }
            else if (toCompare.contains(String.valueOf(numChar))) {
                output += highlightText(Character.toString(numChar), Color.YELLOW);
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

    // ansi text coloring
    private static String highlightText(String text, Color toHighlight) {
        return toHighlight.ansi_background + text + Color.ANSI_RESET;
    }
}