package org.Joshua_Caleb.Server;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    String player;
    Integer guesses = 6;
    final WordHelper wordHelper;
    Request_Response_Handler requestResponseHandler;

    public Game() throws IOException {
        String word = loadWord();
        System.out.println(word);
        this.wordHelper = new WordHelper(word);
        this.requestResponseHandler = new Request_Response_Handler();
    }

    public Integer getGuesses() {
        return guesses;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public JsonObject checkGuess(JsonObject turn){
        JsonObject result;
        Character guess = turn.get("Guess").getAsCharacter();
        String updatedWord = wordHelper.getUpdatedWord();
        if (wordHelper.guessLetter(guess)){
             updatedWord = wordHelper.getUpdatedWord();
            if (!updatedWord.contains("_")){
                result = requestResponseHandler.winResponse(guesses,player,guess,updatedWord);
            }else
            {
                result = requestResponseHandler.rightResponse(guesses, player, guess, updatedWord);
            }
        }else {
            guesses = guesses - 1;
            result = requestResponseHandler.wrongResponse(guesses,player,guess,updatedWord);
        }
        return result;
    }

    public String loadWord() throws IOException {
        File file = new File("words.txt");
        Scanner scn = new Scanner(file);
        ArrayList<String> words = new ArrayList<>();
        while (scn.hasNext()){
            String word_item = scn.nextLine();
            words.add(word_item);
        }
        Random r = new Random();
        String word = words.get(r.nextInt(words.size() -1));
        return word;
    }
}
