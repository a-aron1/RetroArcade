package com.wordle;

/*
 * Wrapper class to initialize list of words from text file
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;

public class LangCheck {
    private List<String> words;

    // Ctors
    public LangCheck(String filePath) throws IOException {
        this.words = Files.readAllLines(Paths.get(filePath)).stream()
                .filter(word -> !(word.contains(".")) || word.contains("-") || word.contains(",") || word.contains("'"))
                .map(word -> word.toLowerCase())
                .collect(Collectors.toList());
    }

    public LangCheck(String filePath, int length) throws IOException {
        this.words = Files.readAllLines(Paths.get(filePath)).stream()
                .filter(word -> word.length() == length
                        && !(word.contains(".")) || word.contains("-") || word.contains(",") || word.contains("'"))
                .map(word -> word.toLowerCase())
                .collect(Collectors.toList());
    }

    // Action methods
    public boolean isWordInList(String word) {
        return words.contains(word);
    }

    public String fetchRandomWord() {
        return words.get(new Random().nextInt(words.size()));
    }
}