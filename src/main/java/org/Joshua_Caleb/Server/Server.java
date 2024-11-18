package org.Joshua_Caleb.Server;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5050);
        System.out.println("Listening on port 5050");

        while (!ss.isClosed()){
            Socket socket = null;

            try {
                socket = ss.accept();
            }catch (IOException e){
                e.printStackTrace();
            }
            if (socket != null){
                System.out.println("New Client Connected");

                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                DataInputStream dis = new DataInputStream(socket.getInputStream());

                Thread thread = new ClientHandler(socket,dos,dis);
                thread.start();
            }
        }
    }
}