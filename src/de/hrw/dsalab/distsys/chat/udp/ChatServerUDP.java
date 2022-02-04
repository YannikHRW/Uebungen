package de.hrw.dsalab.distsys.chat.udp;

import java.io.IOException;
import java.net.*;

public class ChatServerUDP {

    public static void main(String[] args) {

        try {

            DatagramSocket serverSocket = new DatagramSocket(10000);
            System.out.println("ChatServer laeuft!");

            while (true) {

                byte[] buffer = new byte[1024];
                DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length);
                serverSocket.receive(datagramPacket);

                new ChatDienstUDP(datagramPacket, serverSocket).start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
