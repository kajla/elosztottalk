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
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ádám
 */
public class Kliens {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;
        try (Socket socket = new Socket(host, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            String userInput, serverInput;
            while ((serverInput = in.readLine()) != null) {
                System.out.println(serverInput);
                userInput = stdIn.readLine();
//                if (userInput.equals("exit")) { // nem szép
//                    break;
//                }
                if (userInput != null) {
                    out.println(userInput);
                }
            }
//            out.write("Haho ...");
        } catch (IOException ex) {
            Logger.getLogger(Kliens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
