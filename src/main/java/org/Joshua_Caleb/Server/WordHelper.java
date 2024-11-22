package org.Joshua_Caleb.Server;

import java.util.ArrayList;

public class WordHelper {
    private String currentWord;
    private ArrayList<Character> correctGuesses = new ArrayList<>();
    private ArrayList<Character> incorrectGuesses = new ArrayList<>();

    public WordHelper(String word) {
        initWord(word);
    }

    private void initWord(String word) {
        this.currentWord = word;
        for (Character ch: word.toCharArray()){
            correctGuesses.add('_');
        }
    }

    public boolean guessLetter(Character guess){
        if (currentWord.contains(guess.toString())){
            updateCorrect(guess);
            return true;
        }
        updateIncorrect(guess);
        return false;
    }

    private void updateIncorrect(Character guess) {
        this.incorrectGuesses.add(guess);
    }

    private void updateCorrect(Character guess) {
        for (int i = 0; i < currentWord.length(); i++) {
            if (currentWord.toCharArray()[i] == guess){
                this.correctGuesses.set(i,guess);
            }
        }
    }

    private ArrayList<Character> getCorrectGuesses() {
        return this.correctGuesses;
    }

    private ArrayList<Character> getIncorrectGuesses() {
        return this.incorrectGuesses;
    }

    private boolean isWordComplete(){
        if (correctGuesses.contains('_')){
            return false;
        }
        return true;
    }

    private boolean alreadyGuessed(Character guess){
        if (incorrectGuesses.contains(guess)){
            return true;
        }
        return false;
    }
}
