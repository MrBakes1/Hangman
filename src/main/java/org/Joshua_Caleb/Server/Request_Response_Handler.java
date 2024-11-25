package org.Joshua_Caleb.Server;

import com.google.gson.JsonObject;

public class Request_Response_Handler {
    private String result;
    private Integer guesses_left;

    public Request_Response_Handler(String result, Integer guesses_left){
        this.result = result;
        this.guesses_left = guesses_left;
    }

    public Request_Response_Handler(){

    }

    public JsonObject createReq(){
        JsonObject request = new JsonObject();
        request.addProperty("Result",this.result);
        request.addProperty("Guesses_Left",this.guesses_left);
        return request;
    }

    public JsonObject basic_response(JsonObject json){
        String name = json.get("Name").getAsString();
        String guess = json.get("Guess").getAsString();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Guess",guess);
        jsonObject.addProperty("From",name);
        return jsonObject;
    }

    public JsonObject wrongResponse(Integer guesses_left,String name,Character guess, String updatedWord){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Guess",guess);
        jsonObject.addProperty("From",name);
        jsonObject.addProperty("Result","Wrong");
        jsonObject.addProperty("Guesses Left",guesses_left);
        jsonObject.addProperty("Current Answer",updatedWord);

        return jsonObject;
    }

    public JsonObject rightResponse(Integer guesses_left, String name, Character guess, String updatedWord){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Guess",guess);
        jsonObject.addProperty("From",name);
        jsonObject.addProperty("Result","Wright");
        jsonObject.addProperty("Guesses Left",guesses_left);
        jsonObject.addProperty("Current Answer",updatedWord);
        return jsonObject;
    }

    public JsonObject winResponse(Integer guesses_left, String name, Character guess, String updatedWord){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Guess",guess);
        jsonObject.addProperty("From",name);
        jsonObject.addProperty("Result","Win");
        jsonObject.addProperty("Guesses Left",guesses_left);
        jsonObject.addProperty("Current Answer",updatedWord);
        return jsonObject;
    }
}