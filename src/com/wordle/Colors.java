package com.wordle;

/*
 * Ansi background colors
 */

enum Colors {
    BLACK("\033[0;100m"), // , "\u001B[30m"),
    RED("\033[0;101m"), // , "\u001B[31m"),
    GREEN("\033[0;102m"), // , "\u001B[32m"),
    YELLOW("\033[0;103m"), // , "\u001B[33m"),
    BLUE("\033[0;104m"), // , "\u001B[34m"),
    PURPLE("\033[0;105m"), // , "\u001B[35m"),
    CYAN("\033[0;106m"), // , "\u001B[36m"),
    WHITE("\033[0;107m");// , "\u001B[37m");

    private final String ansi_background;
    private final static String ANSI_RESET = "\u001B[0m";

    private Colors(String ansi_background) {
        this.ansi_background = ansi_background;
    }
}


