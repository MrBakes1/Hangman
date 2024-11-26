package org.Joshua_Caleb.Client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) throws IOException{
        String ip = args[0];
        if (!isValidIpAddress(ip)){
            System.out.println("Invalid IP address");
            System.exit(0);
        }
        try {
            Socket clientSocket = new Socket(ip, 5050);

            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());

            Scanner scn = new Scanner(System.in);
            String name;

            System.out.println("Welcome to Hangman\nWhats your name: ");
            name = scn.nextLine();
            dos.writeUTF(name);
            System.out.println(dis.readUTF());

            while (clientSocket.isConnected()){
                System.out.println("Enter a guess: ");
                Scanner s = new Scanner(System.in);
                String guess = s.nextLine();

                Request_Response_Handler request = new Request_Response_Handler(name,guess);
                JsonObject toSend = request.createReq();
                dos.writeUTF(toSend.toString());
//                System.out.println(dis.readUTF());
                String response = dis.readUTF();
                JsonObject result = JsonParser.parseString(response).getAsJsonObject();
                if (result.get("Result").getAsString().equals("Win")){
                    System.out.println("You win");
                    break;
                }
                request.chooseDisplay(result);
            }
            dis.close();
            dos.close();
            clientSocket.close();
        } catch (EOFException a) {
            System.out.println("_____\n|   |\n|   O\n|  /|\\\n|  / \\\n|___");
            System.out.println("You lose!! Welaa!!");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static boolean isValidIpAddress(String ipAddress) {
        String[] ipNum = ipAddress.split("\\.");
        if (ipNum.length != 4) {
            return false;
        }
        try {
            for (String num : ipNum) {
                int ip = Integer.parseInt(num);
                if (ip < 0) {
                    return false;
                }
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}