package de.hrw.dsalab.distsys.chat.rmi;

import de.hrw.dsalab.distsys.chat.udp.NetworkListener;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Objects;

public class ChatListenerRMI extends Thread implements NetworkListener {

    private final JTextArea textArea;
    private RMITestInterface test;


    public ChatListenerRMI(JTextArea textArea, RMITestInterface test) {
        this.textArea = textArea;
        this.test = test;
    }

    public void run() {

        try {

            while (true) {

                messageReceived(test.receive());

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
