package de.hrw.dsalab.distsys.chat.xml;

import de.hrw.dsalab.distsys.chat.multicast.InputListener;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class KeyboardListenerMulticast_XML implements InputListener {

    private final String nick;

    private final DatagramSocket sendingSocket;

    private String ip;
    private int port;

    public KeyboardListenerMulticast_XML(String nick, DatagramSocket sendingSocket, String ip, int port) {
        this.nick = nick;
        this.sendingSocket = sendingSocket;
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void inputReceived(String str) {

        String message = ("<" + nick + "> " + str);

        try {
            DatagramPacket datagramPacket = new DatagramPacket(
                    message.getBytes(),
                    message.length(),
                    InetAddress.getByName(ip),
                    port
            );
            sendingSocket.send(datagramPacket);

        } catch (IOException s) {
            s.printStackTrace();
        }
    }

}
