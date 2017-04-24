/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author kajla
 */
public interface MessageInterface extends Remote {

    public void sendMessage(String message) throws RemoteException;

    public int add(int a, int b) throws RemoteException;

    public int[] sort(int[] a) throws RemoteException;

    public void sendUser(User user) throws RemoteException;
}
