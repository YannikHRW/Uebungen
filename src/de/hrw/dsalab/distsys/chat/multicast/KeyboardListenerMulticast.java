package de.hrw.dsalab.distsys.chat.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class KeyboardListenerMulticast implements InputListener {

    private final String nick;

    private final DatagramSocket sendingSocket;

    public KeyboardListenerMulticast(String nick, DatagramSocket sendingSocket) {
        this.nick = nick;
        this.sendingSocket = sendingSocket;
    }

    @Override
    public void inputReceived(String str) {

        String message = ("<" + nick + "> " + str);

        try {
            DatagramPacket datagramPacket = new DatagramPacket(
                    message.getBytes(),
                    message.length(),
                    InetAddress.getByName("239.0.1.1"),
                    5000
            );
            sendingSocket.send(datagramPacket);

        } catch (IOException s) {
            s.printStackTrace();
        }
    }

}
