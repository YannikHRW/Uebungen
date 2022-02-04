package de.hrw.dsalab.distsys.chat.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMITestInterfaceImpl implements RMITestInterface {

    private static String message;
    private static boolean status;


    public void send(String par) throws RemoteException {

        message = par;
        status = true;

    }

    public String receive() throws RemoteException {

        while(true) {
            if (status) {
                status = false;
                return message;
            }
        }
    }


    public static void main(String[] args) {

        RMITestInterface instance = new RMITestInterfaceImpl();
        RMITestInterface stub;
        Registry registry;


        try {
            stub = (RMITestInterface) UnicastRemoteObject.exportObject(instance, 0);
            registry = LocateRegistry.getRegistry();
            registry.rebind("test", stub);

        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }

}
