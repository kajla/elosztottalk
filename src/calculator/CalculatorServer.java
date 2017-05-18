/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author kajla
 */
public class CalculatorServer {

    private static class Calculator extends UnicastRemoteObject implements CalculatorService {

        private int count = 0;

        public int getCount() {
            return count;
        }

        public Calculator() throws RemoteException {
        }

        @Override
        public synchronized int add(int a, int b) throws RemoteException {
            count++;
            return a + b;
        }

        @Override
        public synchronized String name(String name) throws RemoteException {
            System.out.println("Szia " + name + "!");
            return "Szia " + name + "!";
        }

        @Override
        public synchronized long addUser(User u) throws RemoteException {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("calculatorPU");
            EntityManager em = emf.createEntityManager();
            USR usr = new USR();
            usr.setName(u.getName());
            em.getTransaction().begin();
            em.persist(usr);
            em.getTransaction().commit();
            return usr.getId();
        }

    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            Naming.bind("CALCULATOR", new Calculator());
            System.out.println("Fut a szerver...");
        } catch (RemoteException ex) {
            Logger.getLogger(CalculatorServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(CalculatorServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CalculatorServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
