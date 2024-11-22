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
}
