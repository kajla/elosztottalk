/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kajla
 */
public class CalculatorClient {

    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);
            CalculatorService service = (CalculatorService) reg.lookup("CALCULATOR");
            System.out.println(service.add(12, 13));
            System.out.println(service.name("Béla"));
            System.out.println(service.addUser(new User(0, "Béla")));
        } catch (AccessException ex) {
            Logger.getLogger(CalculatorClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(CalculatorClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CalculatorClient.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }

}
