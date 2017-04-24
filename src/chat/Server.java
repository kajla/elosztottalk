/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kajla
 */
public class Server implements MessageInterface {

    public static void main(String[] args) {
        Server server = new Server();
        try {
            System.out.println("Fut a szerver...");
            MessageInterface stub = (MessageInterface) UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("MESSAGE_SERVICE", stub);
        } catch (AccessException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public synchronized void sendMessage(String string) throws RemoteException {
        System.out.println("Message: " + string);
    }

    @Override
    public synchronized int add(int a, int b) throws RemoteException {
        return a + b;
    }

    @Override
    public synchronized int[] sort(int[] a) throws RemoteException {
        Arrays.sort(a);
        return a;
    }

    @Override
    public synchronized void sendUser(User user) throws RemoteException {
        System.out.println(user);
    }

}
