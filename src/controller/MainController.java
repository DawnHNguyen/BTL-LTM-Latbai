/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import model.Message;

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
}
