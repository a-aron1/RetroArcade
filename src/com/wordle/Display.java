package com.wordle;

/*
 *
 */

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
                //Display.printComparison(board.getGuessNum(i), board.getAnswer(), "|");
            }
            else {
                //paddedPrint(" |".repeat(board.getBoardWidth() - 1));
            }

            if (i != board.getBoardHeight() - 1) {
                //paddedPrint("-".repeat(board.getBoardWidth() - 1));
            }
            else {
                //paddedPrint("");
            }
        }
    }

    public static void promptForWord() {

    }

}