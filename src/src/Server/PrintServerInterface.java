package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PrintServerInterface extends Remote {



    //String print(String sessionId, String filename, String printer) throws RemoteException;
    //String queue() throws RemoteException;

    String sayHello() throws RemoteException;

    String print(String sessionId, String filename, String printer) throws RemoteException;

    String queue(String sessionId, String printer) throws RemoteException;

    String login(String username, String password) throws RemoteException;

    String toQueue(String sessionId, String printer, int job) throws RemoteException;

    String start(String sessionId) throws RemoteException;

    String stop(String sessionId) throws RemoteException;

    String restart(String sessionId) throws RemoteException;

    String status(String sessionId,String printer) throws RemoteException;

    String readConfig(String sessionId,String parameter) throws RemoteException;

    String setConfig(String sessionId,String parameter, String value) throws RemoteException;


}








