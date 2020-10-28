/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.login;

import controller.dao.MainDao;
import controller.dao.LoginDao;
import java.io.IOException;
import model.Account;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author thuc
 */
public class ServerControl {

    private Connection con;
    private ServerSocket myServer;
    private int serverPort = 3001;

    public ServerControl() {
//        MainDao mainDao =new MainDao() ;
        openServer(serverPort);
        while (true) {
            listenning();
        }
    }

    private void openServer(int portNumber) {
        try {
            myServer = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenning() {
//        try {
//            Socket clientSocket = myServer.accept();
//            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
//            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
//            System.out.println("abcasasdasda");
//
//            Object o = ois.readObject();
//            System.out.println("abcasasdasda");
//            System.out.println(o);
//            LoginDao loginDao = new LoginDao();
//
//            if (o instanceof Account) {
//                Account account = (Account) o;
//                if (loginDao.checkLogin(account)) {
//                    oos.writeObject("ok");
//                } else {
//                    oos.writeObject("false");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
try {
           Socket clientSocket = myServer.accept();
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            System.out.println("tao xin may");
            Object o = ois.readObject();
            System.out.println("chao anh em");
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
