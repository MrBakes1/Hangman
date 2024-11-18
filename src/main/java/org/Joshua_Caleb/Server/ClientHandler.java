package org.Joshua_Caleb.Server;

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

    public ClientHandler(Socket socket, DataOutputStream dos, DataInputStream dis){
        this.socket = socket;
        this.dos = dos;
        this.dis = dis;
    }

    @Override
    public void run(){
        String name;
        try{
            name = dis.readUTF();
            dos.writeUTF("Welcome " + name);

            while (true){
                String response = dis.readUTF();
                System.out.println(response);
                dos.writeUTF(response + " received");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
