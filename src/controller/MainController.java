/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Component;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Account;
import model.Message;
import static model.Type.INVITE_CHALLENGE;

/**
 *
 * @author dolong
 */
public class MainController {

    private static Socket mySocket;
    private static String serverHost = "localhost";
//    private String serverHost = "192.168.43.57";
//    private String serverHost = "192.168.1.152";
//    private String serverHost = "172.27.90.65";
//    private String serverHost = "172.19.201.17";
    private static int serverPort = 3000;
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;

    public MainController() {
        try {
            mySocket = new Socket(serverHost, serverPort);
            oos = new ObjectOutputStream(mySocket.getOutputStream());
            ois = new ObjectInputStream(mySocket.getInputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static boolean sendData(Message message) {
        try {
            oos.writeObject(message);
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    public static Message receiveData() {
        Message result = null;
        try {
            Object o = ois.readObject();
            if (o instanceof Message) {
                result = (Message) o;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static boolean closeConnection() {
        try {
            mySocket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
