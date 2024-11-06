package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PrintServerInterface extends Remote{

        String sayHello(String name)throws RemoteException;

        String print() throws RemoteException;
        String queue() throws RemoteException;
        String toQueue() throws RemoteException;
        String start() throws RemoteException;
        String stop() throws RemoteException;
        String restart() throws RemoteException;
        String status() throws RemoteException;
        String readConfig() throws RemoteException;
        String setConfig() throws RemoteException;



    }









