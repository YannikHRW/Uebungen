package de.hrw.dsalab.distsys.chat.rmi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatRMI extends JFrame {

    private static final long serialVersionUID = 1L;
    private String nick;

    private RMITestInterface test;


    public ChatRMI() {
        JPanel mainPanel;

        Registry registry;

        try {
            registry = LocateRegistry.getRegistry("localhost");
            test = (RMITestInterface) registry.lookup("test");

        } catch (RemoteException | NotBoundException re) {
            re.printStackTrace();
        }

        setTitle("Chat Tool v0.1");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        nick = retrieveNickName();
        mainPanel = setupChatView();
        getContentPane().add(mainPanel);
        getContentPane().getParent().invalidate();
        getContentPane().validate();
    }

    private JPanel setupChatView() {
        JPanel panel = new JPanel();
        JPanel southPanel = new JPanel();
        JTextArea textArea = new JTextArea();
        final JTextField textField = new JTextField();
        JButton sendButton = new JButton("Send");

        ChatListenerRMI chatListener = new ChatListenerRMI(textArea, test);
        chatListener.start();

        textField.setColumns(50);

        sendButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    test.send("<" + nick + "> " + textField.getText());
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
                textField.setText("");
            }

        });

        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setEditable(false);

        southPanel.setLayout(new FlowLayout());
        southPanel.add(textField);
        southPanel.add(sendButton);

        panel.setLayout(new BorderLayout());
        panel.add(textArea, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);

        // this is just an example, please modify for your listeners accordingly...

        return panel;
    }

    private String retrieveNickName() {
        return (String) JOptionPane.showInputDialog(this, "Enter your nickname please:", "Enter nickname", JOptionPane.QUESTION_MESSAGE);
    }

    public static void main(String[] args) {
        new ChatRMI();
        new ChatRMI();
    }

}
