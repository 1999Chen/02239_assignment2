package Client;

import Server.PrintServer;
import Server.PrintServerInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException {

            try {

                Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                PrintServerInterface stub = (PrintServerInterface) registry.lookup("RemoteService");


                String response = stub.sayHello("Alice");

                System.out.println("Response from server: " + response);

                String response1="";
                Scanner scanner = new Scanner(System.in);
                System.out.println("pls (input 'exit' to quit)：input commands: print/ queue/ toQueue/ start/ stop/ restart/ status/ readConfig/ setConfig: " );

                boolean running = true;

                while (running) {

                    String input = scanner.nextLine();

                    switch (input){

                        case "print" :
                            response1=stub.print();

                        case "exit":
                            System.out.println("ends。");
                            break;

                        case "queue":
                            response1=stub.queue();

                        case "toQueue":
                            response1=stub.toQueue();

                        case "start":
                            response1=stub.start();

                        case "restart":
                            response1=stub.restart();

                        case "status":
                            response1=stub.status();

                        case "stop":
                            response1=stub.stop();

                        case "readConfig":
                            response1=stub.readConfig();

                        case "setConfig":
                            response1=stub.setConfig();
                    }
                    System.out.println("Response from server: " + response1);



                    System.out.println("hello: " + input);

                }





            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }


    }
}
