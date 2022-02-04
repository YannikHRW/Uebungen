package de.hrw.dsalab.distsys.chat.udp;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ChatListenerUDP extends Thread implements NetworkListener {

    private final JTextArea textArea;
    private final DatagramSocket clientSocket;


    public ChatListenerUDP(JTextArea textArea, DatagramSocket clientSocket) {
        this.textArea = textArea;
        this.clientSocket = clientSocket;

    }

    public void run() {

        try {

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length);
                clientSocket.receive(datagramPacket);

                String receivedMessage = new String(datagramPacket.getData());

                messageReceived(receivedMessage);

                if (isInterrupted()){
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void messageReceived(String msg) {
        textArea.append(msg + System.getProperty("line.separator"));
    }
}
