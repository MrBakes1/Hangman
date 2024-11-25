package org.Joshua_Caleb.Server;

import com.google.gson.JsonObject;

public class Game {
    String player;
    Integer guesses = 6;
    final WordHelper wordHelper;
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

    public void drawHangman(int lives) {
        switch (lives) {
            case 5:
                System.out.println("_____\n|   |\n|   O\n|\n|\n|___");
                break;
            case 4:
                System.out.println("_____\n|   |\n|   O\n|   |\n|\n|___");
                break;
            case 3:
                System.out.println("_____\n|   |\n|   O\n|  /|\n|\n|___");
                break;
            case 2:
                System.out.println("_____\n|   |\n|   O\n|  /|\\\n|\n|___");
                break;
            case 1:
                System.out.println("_____\n|   |\n|   O\n|  /|\\\n|  /\n|___");
                break;
            case 0:
                System.out.println("_____\n|   |\n|   O\n|  /|\\\n|  / \\\n|___");
                break;
            default:
                System.out.println("_____\n|   |\n|\n|\n|\n|___");
                break;
        }
    }

}
