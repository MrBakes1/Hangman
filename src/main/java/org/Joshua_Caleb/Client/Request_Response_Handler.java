package org.Joshua_Caleb.Client;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import netscape.javascript.JSObject;

public class Request_Response_Handler {
    private String name;
    private String guess;

    public Request_Response_Handler(String name,String guess){
        this.name = name;
        this.guess = guess;
    }

    public JsonObject createReq(){
        JsonObject request = new JsonObject();
        request.addProperty("Name",this.name);
        request.addProperty("Guess",this.guess);
        return request;
    }

    public void chooseDisplay(JsonObject res){
        Integer guesses_left = res.get("Guesses Left").getAsInt();
        String answer = res.get("Current Answer").getAsString();
        drawHangman(guesses_left,answer);
    }

    public void drawHangman(int lives,String answer) {
        System.out.println("Updated word: " + answer);
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
