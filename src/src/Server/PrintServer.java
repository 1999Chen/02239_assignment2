package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class PrintServer extends UnicastRemoteObject implements PrintServerInterface {
    public PrintServer() throws RemoteException {
        super();
    }
    private AuthService authService = new AuthService();

    public String login(String username, String password) throws RemoteException {
        if (authService.Authenticate(username, password)){
            return SessionManager.createSession(username);
        }
        return null;
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Hello! ";
    }

    @Override
    public String print(String sessionId, String filename, String printer) throws RemoteException {
        System.out.println("session id is "+sessionId);
        if (SessionManager.isSessionvalid(sessionId)) {
            return "Printing file: " + filename + " to printer:";
        } else {
            return "Session is invalid, please log in again.";
        }

    }

    @Override
    public String queue(String sessionId, String printer) throws RemoteException {
        if (SessionManager.isSessionvalid(sessionId)) {
            return "Displaying print queue...";
        } else {
            return "Session is invalid, please log in again.";
        }
    }

    @Override
    public String toQueue(String sessionId, String printer, int job) throws RemoteException
    {
        if (SessionManager.isSessionvalid(sessionId)) {
            return "Displaying print queue...";
        } else {
            return "Session is invalid, please log in again.";
        }
    }


    @Override
    public String start(String sessionId) throws RemoteException {
        if (SessionManager.isSessionvalid(sessionId)){
            return "starting..";
        }
        else {
            return "Session is invalid, please log in again.";
        }

    }

    @Override
    public String stop(String sessionId) throws RemoteException {
        if (SessionManager.isSessionvalid(sessionId)){
            return "stopping..";
        }
        else {
            return "Session is invalid, please log in again.";
        }
    }

    @Override
    public String restart(String sessionId) throws RemoteException {
        if (SessionManager.isSessionvalid(sessionId)){
            return "restarting..";
        }
        else {
            return "Session is invalid, please log in again.";
        }
    }

    @Override
    public String status(String sessionId, String printer) throws RemoteException {
        if (SessionManager.isSessionvalid(sessionId)){
            return "status is ..";
        }
        else {
            return "Session is invalid, please log in again.";
        }
    }

    @Override
    public String readConfig(String sessionId, String parameter) throws RemoteException {
        if (SessionManager.isSessionvalid(sessionId)){
            return "reading config ..";
        }
        else {
            return "Session is invalid, please log in again.";
        }
    }

    @Override
    public String setConfig(String sessionId, String parameter, String value) throws RemoteException {
        if (SessionManager.isSessionvalid(sessionId)){
            return "setting config ..";
        }
        else {
            return "Session is invalid, please log in again.";
        }
    }


    public static void main(String[] args) {

            try {

                PrintServer server = new PrintServer();

                Registry registry = LocateRegistry.createRegistry(1099);

                registry.rebind("RemoteService", server);

                System.out.println("Server is ready.");
            } catch (RemoteException e) {
                System.err.println("Server exception: " + e.toString());
                e.printStackTrace();
            }

    }




}
