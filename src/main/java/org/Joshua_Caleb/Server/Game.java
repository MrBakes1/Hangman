package org.Joshua_Caleb.Server;

import com.google.gson.JsonObject;

public class Game {
    String player;
    Integer guesses = 6;
    WordHelper wordHelper;
    Request_Response_Handler requestResponseHandler;

    public Game(){
        this.wordHelper = new WordHelper("love");
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
        JsonObject result = new JsonObject();
        Character guess = turn.get("Guess").getAsCharacter();
        if (wordHelper.guessLetter(guess)){
            result = requestResponseHandler.wrightResponse(guesses,player,guess);
        }else {
            guesses = guesses - 1;
            result = requestResponseHandler.wrongResponse(guesses,player,guess);
        }
        return result;
    }
}
