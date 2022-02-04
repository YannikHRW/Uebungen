package de.hrw.dsalab.distsys.chat.udp;

import javax.swing.JTextArea;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;

public class KeyboardListenerUDP implements InputListener {

    //private JTextArea textArea;
    private final String nick;


    private static final int serverPort = 10000;

    private final String clientPort;
    private final DatagramSocket clientSocket;

    public KeyboardListenerUDP(/**JTextArea textArea, **/String nick, int clientPort, DatagramSocket clientSocket) {
        //this.textArea = textArea;
        this.nick = nick;

        if (clientPort < 10000) {
            this.clientPort = "0" + clientPort;
        } else {
            this.clientPort = "" + clientPort;
        }
        this.clientSocket = clientSocket;
    }

    @Override
    public void inputReceived(String str) {
        //textArea.append("<" + nick + "> " + str + System.getProperty("line.separator"));

        String message = (clientPort + "<" + nick + "> " + str);

        try {
            DatagramPacket datagramPacket = new DatagramPacket(
                    message.getBytes(),
                    message.length(),
                    InetAddress.getLocalHost(),
                    serverPort
            );
            clientSocket.send(datagramPacket);
        } catch (IOException s) {
            s.printStackTrace();
        }
    }

}
