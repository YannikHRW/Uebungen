package de.hrw.dsalab.distsys.chat.udp;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class ChatDienstUDP extends Thread {

    static ArrayList<Integer> clientsList = new ArrayList<>();

    DatagramPacket datagramPacket;
    DatagramSocket serverSocket;
    String receivedMessage;
    int clientPort;


    public ChatDienstUDP(DatagramPacket datagramPacket, DatagramSocket serverSocket) {

        this.datagramPacket = datagramPacket;
        this.serverSocket = serverSocket;
        receivedMessage = new String(datagramPacket.getData());
        clientPort = Integer.parseInt(receivedMessage.substring(0, 5));
        receivedMessage = receivedMessage.substring(5);

    }

    public void run() {

        try {

            boolean delete = false;

            if (!(clientsList.contains(clientPort))) {
                clientsList.add(clientPort);
                receivedMessage = "***\n Server: Client " + receivedMessage.substring(receivedMessage.indexOf("<"), (receivedMessage.indexOf(">") + 1)) + " ist online! \n***";
            }

            if (receivedMessage.contains("#off#")) {
                delete = true;
                receivedMessage = "***\n Server: Client " + receivedMessage.substring(receivedMessage.indexOf("<"), (receivedMessage.indexOf(">") + 1)) + " ist offline! \n***";
            }

            for (Integer integer : clientsList) {
                DatagramPacket datagramPacket = new DatagramPacket(
                        receivedMessage.getBytes(),
                        receivedMessage.length(),
                        InetAddress.getLocalHost(),
                        integer
                );
                serverSocket.send(datagramPacket);
            }

            if (delete){
                clientsList.remove((Integer) clientPort);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
