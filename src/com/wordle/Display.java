package com.wordle;

public class Display {
    private static String title =
            "WELCOME!! TO\nWORDLE CLONE FOR JAVA\nBrought To You By\nYour Favorite Developers at\nRetroArcade";
    private static String leftPadding = " ";

    // statics
    public static void setTitle(String str) {
        title = str;
    }

    public static void setLeftPadding(String str) {
        leftPadding = str;
    }

    // methods
    public static void  print(Board board) {
        System.out.println(title);
        for (int i = 0; i < board.getBoardHeight(); i++) {

            if (i < board.getGuessCount()) {
                Display.printComparison(board.getGuessNum(i), board.getAnswer(), "|");
            }
            else {
                paddedPrint(" |".repeat(board.getBoardWidth() - 1));
            }

            if (i != board.getBoardHeight() - 1) {
                paddedPrint("-".repeat(board.getBoardWidth() - 1));
            }
            else {
                paddedPrint("");
            }
        }
    }

    public static void promptForWord() {
        System.out.println("Enter a word:\n");
    }

    public static void printAnswer(String str) {
        System.out.println("The answer is:\n" + str);
    }

    public static void clear() {
        System.out.println("\033\143");
        System.out.flush();
    }

    // printers TODO * convert to StringBuilder??
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

    // Ansi text coloring
    private static String highlightText(String text, Color toHighlight) {
        return toHighlight.ansi_background + text + Color.ANSI_RESET;
    }
}