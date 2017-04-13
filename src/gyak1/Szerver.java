/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gyak1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ádám
 */
public class Szerver {

    public static void main(String[] args) {
        System.out.println("Fut a szerver ...");
        try (ServerSocket ss = new ServerSocket(1234);
                Socket clientSocket = ss.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            out.println(":>");
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(":> " + inputLine);
                out.println(inputLine);
            }
            System.out.println(inputLine);
        } catch (IOException ex) {
            Logger.getLogger(Szerver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
