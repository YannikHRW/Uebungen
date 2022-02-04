package de.hrw.dsalab.distsys.chat.socket;
import javax.swing.JTextArea;
import java.io.PrintWriter;

public class KeyboardListenerSocket implements InputListener {

    //private JTextArea textArea;
    private String nick;


    private PrintWriter zumServer;

    public KeyboardListenerSocket(/**JTextArea textArea, **/String nick, PrintWriter zumServer) {
        //this.textArea = textArea;
        this.nick = nick;


        this.zumServer = zumServer;
    }

    @Override
    public void inputReceived(String str) {
        //textArea.append("<" + nick + "> " + str + System.getProperty("line.separator"));


        zumServer.println("<" + nick + "> " + str);
    }

}
