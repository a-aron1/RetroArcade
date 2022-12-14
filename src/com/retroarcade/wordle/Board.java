package com.retroarcade.wordle;

import java.io.IOException;
import java.util.ArrayList;

public class Board extends Screens {
    private int rowCount;
    private int colCount;
    private ArrayList<String> guesses = new ArrayList<>();
    private String answer;
    private WordCheck wordList;

    /*
     * Initial overloads.
     * The filePath (String) should point to a txt containing words for the WordleBoard
     * Size (int) and wordlength (int) allow for various size boards
     */
    public Board(String filePath) throws IOException {
        this.rowCount = 6;
        this.colCount = 5;
        this.wordList = new WordCheck(filePath, 5);
        this.answer = wordList.randomWord();
    }

    public Board(String filePath, int size) throws IOException {
        this.rowCount = size;
        this.colCount = 5;
        this.wordList = new WordCheck(filePath, 5);
        this.answer = wordList.randomWord();
    }

    public Board(String filePath, int size, int wordLength) throws IOException {
        this.rowCount = size;
        this.colCount = wordLength;
        this.wordList = new WordCheck(filePath, wordLength);
        this.answer = wordList.randomWord();
    }

    // getters
    public int getHeight() {
        return rowCount;
    }

    public int getWidth() {
        return colCount;
    }

    public final String getAnswer() {
        return answer;
    }

    // derived
    public int countGuesses() {
        return guesses.size();
    }

    public final String numGuess(int num) {
        return guesses.get(num);
    }

    public boolean isGameOver() {
        return hasWon() || countGuesses() == getHeight();
    }

    public boolean hasWon() {
        return guesses.contains(getAnswer());
    }

    public void guess(String str) {

        if (str.length() == 5 && str.chars().allMatch(Character::isLetter) && !wordList.containsWord(str)) {
            System.out.println(Screens.GREEN + str + Screens.RED
                    + " is not in the dictionary.  Please try again" + Screens.RESET);
        }

        if (str.length() == 5 && str.chars().allMatch(Character::isLetter)) {

            if (str.length() == getWidth() && wordList.containsWord(str)) {
                guesses.add(str);
            }
        }
        else {
            System.out.println("Please enter a 5 letter word\n");
        }

    }
}