package de.hrw.dsalab.distsys.chat.chat;

public interface InputListener {
	
	public void inputReceived(String str);
	public void inputReceived(String str, String nick);
}
