package Client;

import Server.PrintServerInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException {

            try {

                Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                PrintServerInterface stub = (PrintServerInterface) registry.lookup("RemoteService");

                String sessionId = login(stub);
                if (sessionId == null) {
                    System.out.println("Login failed. Exit the program。");
                    return;
                }


                String response = stub.sayHello();

                System.out.println("Response from server: " + response);

                String response1="";
                Scanner scanner = new Scanner(System.in);

                while (true) {
                    System.out.print("Enter command (print, queue, topQueue, status, start, stop, restart, readConfig, setConfig, exit): ");
                    String command = scanner.nextLine();
                    String input = scanner.nextLine();

                    switch (input){

                        case "print" :
                            System.out.print("Enter filename: ");
                            String filename = scanner.nextLine();
                            System.out.print("Enter printer: ");
                            String printer = scanner.nextLine();
                            response1=stub.print(sessionId, filename, printer);
                            System.out.println("Response from server: " + response1);
                            break;

                        case "exit":
                            System.out.println("ends。");
                            return;

                        case "queue":
                            System.out.print("Enter printer: ");
                            printer = scanner.nextLine();
                            response1=stub.queue(sessionId,printer);
                            System.out.println("Response from server: " + response1);
                            break;

                        case "toQueue":
                            System.out.print("Enter printer: ");
                            printer = scanner.nextLine();
                            System.out.print("Enter job number: ");
                            int jobNumber = Integer.parseInt(scanner.nextLine());
                            response1=stub.toQueue(sessionId,printer,jobNumber);
                            System.out.println("Response from server: " + response1);
                            break;

                        case "start":
                            System.out.print("EStarting the server...");
                            response1=stub.start(sessionId);
                            System.out.println("Response from server: " + response1);
                            break;

                        case "restart":
                            response1=stub.restart(sessionId);
                            System.out.println("Response from server: " + response1);
                            break;

                        case "status":
                            System.out.print("Enter printer: ");
                            printer = scanner.nextLine();
                            response1=stub.status(sessionId,printer);
                            System.out.println("Response from server: " + response1);
                            break;

                        case "stop":
                            response1=stub.stop(sessionId);
                            System.out.println("Response from server: " + response1);
                            break;

                        case "readConfig":
                            System.out.print("Enter parameter: ");
                            String parameter = scanner.nextLine();
                            response1=stub.readConfig(sessionId,parameter);
                            System.out.println("Response from server: " + response1);
                            break;

                        case "setConfig":
                            System.out.print("Enter printer: ");
                            printer = scanner.nextLine();
                            System.out.print("Enter value: ");
                            String value = scanner.nextLine();

                            response1=stub.setConfig(sessionId,printer,value);
                            System.out.println("Response from server: " + response1);
                            break;
                    }

                    System.out.println("hello: " + input);

                }


            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }


    }

    private static String login(PrintServerInterface stub)  throws RemoteException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Call the login method on the server
        String sessionToken = stub.login(username, password);
        if (sessionToken != null) {
            System.out.println("Login successful. Session token: " + sessionToken);
        } else {
            System.out.println("Login failed.");
        }
        return sessionToken;
    }
}
