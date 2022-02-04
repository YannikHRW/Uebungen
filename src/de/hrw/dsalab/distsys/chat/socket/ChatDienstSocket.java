package de.hrw.dsalab.distsys.chat.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class ChatDienstSocket extends Thread {

    static int anzahl = 0;
    static ArrayList zuClients = new ArrayList();

    int nr = 0;
    Socket s;
    BufferedReader vomClient;
    PrintWriter zumClient;
    PrintWriter nextClient;

    public ChatDienstSocket(Socket s) {

        try {
            this.s = s;
            nr = ++anzahl;
            vomClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
            zumClient = new PrintWriter(s.getOutputStream(), true);
            zuClients.add(zumClient);

        } catch (IOException e) {
            System.out.println("IO-Error bei Client " + nr);
            e.printStackTrace();
        }

    }

    public void run() {

        try {
            zumClient.println("***\n" + "Server: Moin Chef! Mit LOGOUT kannst du dich ausloggen!\n" + "***");
            while (true) {
                String nachricht = vomClient.readLine();
                if (nachricht.contains("LOGOUT")) {
                    for (int i = 0; i < zuClients.size(); i++) {
                        nextClient = (PrintWriter) zuClients.get(i);
                        nextClient.println("***\n" + "Server: Client " + nachricht.substring(nachricht.indexOf("<"), (nachricht.indexOf(">") + 1)) + " hat sich ausgeloggt\n" + "***");
                    }
                    zumClient.println("Eigentlich müsste hier noch der Socket geschlossen werden :-(");
                    break;
                }
                for (int i = 0; i < zuClients.size(); i++) {
                    nextClient = (PrintWriter) zuClients.get(i);
                    nextClient.println(nachricht);
                }
            }
            s.close();

        } catch (IOException e) {
            System.out.println("IO-Error bei Client " + nr);
        }
        System.out.println("Client " + nr + " hat sich ausgeloggt");
        --anzahl;
        if (anzahl == 1) {
            System.out.println("Es ist noch " + anzahl + " Client online");
        } else {
            System.out.println("Es sind noch " + anzahl + " Clients online");
        }
    }

}
