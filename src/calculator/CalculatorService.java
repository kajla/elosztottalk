/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author kajla
 */
public interface CalculatorService extends Remote {

    int add(int a, int b) throws RemoteException;
    String name(String name) throws RemoteException;
    long addUser(User u) throws RemoteException;
}
