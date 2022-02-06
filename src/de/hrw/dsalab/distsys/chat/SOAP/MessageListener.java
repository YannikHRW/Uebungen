package de.hrw.dsalab.distsys.chat.SOAP;

import de.hrw.dislab.distsys.soapserver.ChatServer;
import de.hrw.dislab.distsys.soapserver.ChatServerService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MessageListener extends Thread {


    private JTextArea textArea;
    private ChatServer port;

    public MessageListener(JTextArea textArea, ChatServer port) {

        this.textArea = textArea;
        this.port = port;

    }


    public void run() {

        while (true) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            List<String> list = port.getMessages();

            if (list.size() != 0) {

                for (String message : list) {

                    textArea.append(message + System.getProperty("line.separator"));

                }

            }

        }
    }

}
