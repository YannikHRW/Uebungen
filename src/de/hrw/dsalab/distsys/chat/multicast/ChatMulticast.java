package de.hrw.dsalab.distsys.chat.multicast;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class ChatMulticast extends JFrame {

    private static final long serialVersionUID = 1L;
    private InputListener inputListener;
    private final String nick;


    private MulticastSocket receiveSocket;
    private DatagramSocket sendingSocket;
    private ChatListenerMulticast chatListener;
    private boolean onlineStatus;

    public ChatMulticast() {
        JPanel mainPanel;

        try {

            receiveSocket = new MulticastSocket(5000);
            receiveSocket.setNetworkInterface(NetworkInterface.getByInetAddress(InetAddress.getByName("localhost")));
            receiveSocket.joinGroup(InetAddress.getByName("239.0.1.1"));

            sendingSocket = new DatagramSocket();

        } catch (IOException e) {
            e.printStackTrace();
        }

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        nick = retrieveNickName();
        setTitle("Chat Tool v0.1" + "\t" + "Benutzer: " + nick);
        mainPanel = setupChatView();
        getContentPane().add(mainPanel);
        getContentPane().getParent().invalidate();
        getContentPane().validate();

    }

    private JPanel setupChatView() {
        JPanel panel = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel northPanel = new JPanel();
        JTextArea textArea = new JTextArea();
        final JTextField textField = new JTextField();
        JButton sendButton = new JButton("Send");
        JButton loginButton = new JButton("Login");
        JButton logoutButton = new JButton("Logout");



        textField.setColumns(50);

        sendButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(onlineStatus) {
                    inputListener.inputReceived(textField.getText());
                }
                textField.setText("");
            }

        });

        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!onlineStatus) {
                    northPanel.setBackground(Color.GREEN);
                    chatListener = new ChatListenerMulticast(textArea, receiveSocket);
                    chatListener.start();
                    inputListener.inputReceived("#on#");
                    onlineStatus = true;
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                northPanel.setBackground(Color.RED);
                onlineStatus = false;
                chatListener.interrupt();
                inputListener.inputReceived("#off#");
            }
        });

        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setEditable(false);

        southPanel.setLayout(new FlowLayout());
        southPanel.add(textField);
        southPanel.add(sendButton);

        northPanel.setLayout(new FlowLayout());
        northPanel.add(loginButton);
        northPanel.add(logoutButton);

        panel.setLayout(new BorderLayout());
        panel.add(textArea, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);
        panel.add(northPanel, BorderLayout.NORTH);

        // this is just an example, please modify for your listeners accordingly...
        inputListener = new KeyboardListenerMulticast(nick, sendingSocket);
       // chatListener = new ChatListener(textArea, clientSocket);

        return panel;
    }

    private String retrieveNickName() {
        return (String) JOptionPane.showInputDialog(this, "Enter your nickname please:", "Enter nickname", JOptionPane.QUESTION_MESSAGE);
    }

    public static void main(String[] args) {
        new ChatMulticast();
        new ChatMulticast();
    }

}
