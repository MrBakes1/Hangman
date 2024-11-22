package org.Joshua_Caleb.Server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler extends Thread{
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket socket;
    final Game game;

    public ClientHandler(Socket socket, DataOutputStream dos, DataInputStream dis,Game game){
        this.socket = socket;
        this.dos = dos;
        this.dis = dis;
        this.game = game;
    }

    @Override
    public void run(){
        String name;
        try{
            name = dis.readUTF();
            dos.writeUTF("Welcome " + name);
            game.setPlayer(name);
            while (true){
                String request = dis.readUTF();
                JsonObject jsonObject = JsonParser.parseString(request).getAsJsonObject();

                JsonObject result = game.checkGuess(jsonObject);
                System.out.println(result);
                Request_Response_Handler response = new Request_Response_Handler();
                JsonObject tosend = response.basic_response(jsonObject);
                dos.writeUTF(tosend.toString());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
