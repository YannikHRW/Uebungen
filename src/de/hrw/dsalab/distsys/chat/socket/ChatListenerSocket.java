package de.hrw.dsalab.distsys.chat.socket;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;

public class ChatListenerSocket extends Thread implements NetworkListener {

    private JTextArea textArea;
    private BufferedReader vomServer;

    public ChatListenerSocket(JTextArea textArea, BufferedReader vomServer) {
        this.textArea = textArea;
        this.vomServer = vomServer;
    }

    public void run() {

        String str;

        try {
            while ((str = vomServer.readLine()) != null) {
                messageReceived(str);
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
