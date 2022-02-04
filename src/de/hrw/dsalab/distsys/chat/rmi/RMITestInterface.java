package de.hrw.dsalab.distsys.chat.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMITestInterface extends Remote {

    public void send (String par) throws RemoteException;
    public String receive () throws RemoteException;


}
