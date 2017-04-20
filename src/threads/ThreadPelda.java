/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

/**
 *
 * @author kajla
 */
class PrintA extends Thread {

    @Override
    public void run() {
        while (true) {
            System.out.println("A");
            yield();
        }
    }

}

class PrintB implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("B");
        }
    }

}

public class ThreadPelda {

    public static void main(String[] args) {
        new PrintA().start();
//        new Thread(new PrintB().run);
    }
}
