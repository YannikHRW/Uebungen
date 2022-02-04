package de.hrw.dsalab.distsys.chat.xml;

import de.hrw.dsalab.distsys.chat.multicast.NetworkListener;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class ChatListenerMulticast_XML extends Thread implements NetworkListener {

    private final JTextArea textArea;
    private final MulticastSocket receiveSocket;


    public ChatListenerMulticast_XML(JTextArea textArea, MulticastSocket receiveSocket) {
        this.textArea = textArea;
        this.receiveSocket = receiveSocket;

    }

    public void run() {

        try {

            while (true) {

                byte[] buffer = new byte[1024];

                DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length);
                receiveSocket.receive(datagramPacket);

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
