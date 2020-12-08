/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Message;

/**
 *
 * @author dolong
 */
public class ReadThread extends Thread {

    private ObjectInputStream ois;
    private Socket socket;

    public ReadThread(Socket socket) {
        this.socket = socket;

        try {
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            Message result = null;
            try {
                Object o = ois.readObject();
                if (o instanceof Message) {
                    result = (Message) o;
                    System.out.println("xinn chaooo");
                }
            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ReadThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
