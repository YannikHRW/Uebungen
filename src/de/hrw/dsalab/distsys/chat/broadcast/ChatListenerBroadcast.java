package de.hrw.dsalab.distsys.chat.broadcast;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ChatListenerBroadcast extends Thread implements NetworkListener {

    private final JTextArea textArea;
    private final DatagramSocket receivingSocket;


    public ChatListenerBroadcast(JTextArea textArea, DatagramSocket receivingSocket) {
        this.textArea = textArea;
        this.receivingSocket = receivingSocket;

    }

    public void run() {

        try {

            byte[] buffer = new byte[1024];

            while (true) {

                DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length);
                receivingSocket.receive(datagramPacket);

                String receivedMessage = new String(datagramPacket.getData());

                messageReceived(receivedMessage);

                if (isInterrupted()){
                    break;
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
