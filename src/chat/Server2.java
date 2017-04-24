/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author kajla
 */
public class Server2 extends JFrame {

    private class MessageService extends UnicastRemoteObject implements MessageInterface {

        public MessageService() throws RemoteException {

        }

        @Override
        public synchronized void sendMessage(String message) throws RemoteException {
            System.out.println("Message: " + message);
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

    public Server2() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 500);
        createService();
        setVisible(true);
    }

    private void createService() {
        try {
            LocateRegistry.createRegistry(1099);
            Naming.bind("MESSAGE_SERVICE", new MessageService());
        } catch (RemoteException ex) {
            Logger.getLogger(Server2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(Server2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Server2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new Server2();
    }
}
