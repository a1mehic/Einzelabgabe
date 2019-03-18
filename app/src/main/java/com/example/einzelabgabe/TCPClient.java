package com.example.einzelabgabe;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

class TCPClient{

    String output;

    public String getOutput() {
        return output;
    }

    public void sendToServer(String sentence) throws Exception{
        //InputStream erzeugen
        BufferedReader inFormUser= new BufferedReader(new InputStreamReader(System.in));

        //Client socket ertsellen und verbindung zum Server herstellen
        Socket clientSocket = new Socket ("se2-isys.aau.at",53212);

        //Mit Socket verbundenen Ausgabestrom ertsellen
        DataOutputStream outToServer= new DataOutputStream(clientSocket.getOutputStream());

        //Eingansstrom erstellen, der am Socket angehängt ist
        BufferedReader inFromServer= new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        //Sende Zeile zum Server
        outToServer.writeBytes(sentence + '\n');

        // Zeile vom Server lesen
        output = inFromServer.readLine();

        if(output != null){

        }else{
            System.out.println("Der Inhalt ist leer!");
        }

        System.out.println("FROM SERVER: " + output);

        clientSocket.close();
    }
}