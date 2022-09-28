package com.retroarcade.wordle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

// Wrapper class to initialize a list of words from a txt.
public class WordCheck {
    private final List<String> words;

    // INIT OVERLOADS
    WordCheck(String filePath) throws IOException {
        this.words = Files.readAllLines(Paths.get(filePath)).stream()
                .filter(word -> !(word.contains(".") || word.contains("-") || word.contains(",") || word.contains("'")))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    // WordLength (int) will filter out words of any other length.
    WordCheck(String filePath, int wordLength) throws IOException {
        this.words = Files.readAllLines(Paths.get(filePath)).stream()
                .filter(word -> word.length() == wordLength
                        && !(word.contains(".") || word.contains("-") || word.contains(",") || word.contains("'")))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public boolean containsWord(String word) {
        return words.contains(word);
    }

    public String randomWord() {
        return words.get(new Random().nextInt(words.size()));
    }
}