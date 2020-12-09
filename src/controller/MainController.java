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

    private Socket mySocket;
    private String serverHost = "localhost";
//    private String serverHost = "192.168.43.57";
//    private String serverHost = "192.168.1.152";
//    private String serverHost = "172.27.90.65";
//    private String serverHost = "172.19.201.17";
    private int serverPort = 3000;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public MainController() {
        try {
            mySocket = new Socket(serverHost, serverPort);
            oos = new ObjectOutputStream(mySocket.getOutputStream());
            ois = new ObjectInputStream(mySocket.getInputStream());
//            run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public Socket getSocket(){
        return mySocket;
    }
    public ObjectInputStream getInputStream(){
        return ois;
    }
    public boolean sendData(Message message) {
        try {
            oos.writeObject(message);
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    public Message receiveData() {
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

    public boolean closeConnection() {
        try {
            mySocket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
//    public void run() {
//        while (!Thread.currentThread().isInterrupted()) {
//            Message result = null;
//            result = receiveData();
//            System.out.println(result.getType());
//            Account account = (Account) result.getContent();
//            if (result instanceof Message) {
//                result = (Message) result;
////                if (result.getType() == INVITE_CHALLENGE) {
////                    Component JFrame;
////                    int isAccept = JOptionPane.showConfirmDialog(JFrame, account.getName() + " want to challege you in a game");
////                    if (isAccept == JOptionPane.YES_OPTION) {
////                        Message response = new Message(null, null);
////                        sendData(response);
////                    }
////                }
//
//            }
//        }
//    }
}
