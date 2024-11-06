package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class PrintServer implements PrintServerInterface {


    @Override
    public String sayHello(String name) throws RemoteException {
        return "Hello, " + name + "!";
    }

    @Override
    public String print() throws RemoteException {
        return "good";
    }

    @Override
    public String queue() throws RemoteException {
        return null;
    }

    @Override
    public String toQueue() throws RemoteException {
        return null;
    }

    @Override
    public String start() throws RemoteException {
        return null;
    }

    @Override
    public String stop() throws RemoteException {
        return null;
    }

    @Override
    public String restart() throws RemoteException {
        return null;
    }

    @Override
    public String status() throws RemoteException {
        return null;
    }

    @Override
    public String readConfig() throws RemoteException {
        return null;
    }

    @Override
    public String setConfig() throws RemoteException {
        return null;
    }


    public static void main(String[] args) {

            try {
                // 创建并导出远程对象
                PrintServer server = new PrintServer();
                PrintServerInterface stub = (PrintServerInterface) UnicastRemoteObject.exportObject(server, 0);

                // 创建或获取RMI注册表
                Registry registry = LocateRegistry.createRegistry(1099);

                // 绑定远程对象
                registry.rebind("RemoteService", stub);

                System.out.println("Server is ready.");
            } catch (RemoteException e) {
                System.err.println("Server exception: " + e.toString());
                e.printStackTrace();
            }

    }




}
