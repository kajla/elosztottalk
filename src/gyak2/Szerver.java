/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gyak2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
        try (ServerSocket serverSocket = new ServerSocket(1234);
                Socket clientSocket = serverSocket.accept();
                PrintWriter clientOut = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String inputLine, outputLine;
            UserProtocol up = new UserProtocol();
            outputLine = up.processInput(null);
            clientOut.println(outputLine);
            while ((inputLine = clientIn.readLine()) != null) {
                System.out.println(inputLine);
                outputLine = up.processInput(inputLine);
                System.out.println("Output: " + outputLine);
                clientOut.println(outputLine);
            }
        } catch (IOException ex) {
            Logger.getLogger(Szerver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
