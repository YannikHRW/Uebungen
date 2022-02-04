package de.hrw.dsalab.distsys.chat.socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServerSocket {

    public static void main(String[] args) {

        try {

            ServerSocket server = new ServerSocket(1337);
            System.out.println("ChatServer laeuft!");
            while (true) {

                Socket s = server.accept();
                System.out.println("Neuer Socket zum Client wurde erstellt!");
                new ChatDienstSocket(s).start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
