/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kajla
 */
public class CServer {

    private static List<PrintWriter> writers = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Fut a szerver...");
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientThread(socket).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(CServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static class ClientThread extends Thread {

        private Socket clientSocket = null;
        private PrintWriter writer = null;

        public ClientThread(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);) {
                this.writer = output;
                synchronized (writers) {
                    writers.add(this.writer);
                }
                String request;
                while (true) {
                    request = input.readLine();
                    if (request != null) {
                        System.out.println(request);
                        synchronized (writers) {
                            for (PrintWriter out : writers) {
                                if (out != output) {
                                    out.println(request);
                                }
                            }
                        }
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(CServer.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (writer != null) {
                    synchronized (writers) {
                        writers.remove(writer);
                    }
                }
                if (clientSocket != null) {
                    try {
                        clientSocket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(CServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

    }

}
