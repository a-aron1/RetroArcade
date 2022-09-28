package com.retroarcade.wordle;

public class Display {
    private static String title = "";
    private static String leftPadding = " ";

    private enum Color {
        BG_GREEN("\033[0;102m"),
        BG_YELLOW("\033[0;103m"),
        BG_RED("\033[0;101m"),
        BG_BLACK("\033[0;100m"),
        BG_BLUE("\033[0;104m"),
        BG_PURPLE("\033[0;105m"),
        BG_CYAN("\033[0;106m"),
        BG_WHITE("\033[0;107m"),
        FG_BLACK_BG_GREEN("\033[0;102m", "\033[0;107m"),
        FG_WHITE_BG_GREEN("\033[0;102m", "\033[0;100m"),
        FG_BLACK_BG_YELLOW("\033[0;103m", "\033[0;100m");

        private final String background;
        private String foreground;
        private final static String RESET = "\u001B[0m";
        //ansi_foreground -- rename (add to ctor)

        private Color(String background) {
            this.background = background;
        }

        private Color(String background, String foreground) {
            this(background);
            this.foreground = foreground;
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
    public static void render(Board board) {
        System.out.println(); // blank line in output
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
        System.out.print("Enter your best guess (five-letter word):\n ");
    }

    public static void printAnswer(String str) {
        System.out.println("The answer to today's Wordle is:\n " + str);
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
                output += highlightText(Character.toString(numChar), Color.BG_GREEN);
            }
            else if (toCompare.contains(String.valueOf(numChar))) {
                output += highlightText(Character.toString(numChar), Color.BG_YELLOW);
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

    // TODO - render foreground color

    // coloring
    private static String highlightText(String text, Color toHighlight) {
        return toHighlight.background + text + Color.RESET;
    }
}