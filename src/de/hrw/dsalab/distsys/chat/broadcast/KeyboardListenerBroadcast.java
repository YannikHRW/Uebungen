package de.hrw.dsalab.distsys.chat.broadcast;

import java.io.IOException;
import java.net.*;

public class KeyboardListenerBroadcast implements InputListener {

    private final String nick;

    private final DatagramSocket sendingSocket;

    public KeyboardListenerBroadcast(String nick, DatagramSocket sendingSocket) {
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
                    InetAddress.getByName("192.168.178.255"),
                    5000
            );

            sendingSocket.send(datagramPacket);

        } catch (IOException s) {
            s.printStackTrace();
        }
    }

}
