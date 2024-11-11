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
    public String sayHello(String name) throws RemoteException {
        return "Hello, " + name + "!";
    }

    @Override
    public String print(String sessionId, String filename, String printer) throws RemoteException {
        if (SessionManager.isSessionvalid(sessionId)) {
           return "Printing file: " + filename + " to printer: " + printer;
        } else {
           return "Session is invalid, please log in again.";
        }
       
    }

    @Override
    public String queue(String sessionId, String printer) throws RemoteException {
        if (SessionManager.isSessionvalid(sessionId)) {
            System.out.println("Displaying print queue...");
        } else {
            System.out.println("Session is invalid, please log in again.");
        }
        return "queue";
    }

    @Override
    public String toQueue() throws RemoteException {
        return "toQueue";
    }

    @Override
    public String start() throws RemoteException {
        return "start";
    }

    @Override
    public String stop() throws RemoteException {
        return "stop";
    }

    @Override
    public String restart() throws RemoteException {
        return "restart";
    }

    @Override
    public String status() throws RemoteException {
        return "status";
    }

    @Override
    public String readConfig() throws RemoteException {
        return "readConfig";
    }

    @Override
    public String setConfig() throws RemoteException {
        return "setConfig";
    }


    public static void main(String[] args) {

            try {

                PrintServer server = new PrintServer();
                PrintServerInterface stub = (PrintServerInterface) UnicastRemoteObject.exportObject(server, 0);

                Registry registry = LocateRegistry.createRegistry(1099);

                registry.rebind("RemoteService", stub);

                System.out.println("Server is ready.");
            } catch (RemoteException e) {
                System.err.println("Server exception: " + e.toString());
                e.printStackTrace();
            }

    }




}
