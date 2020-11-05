/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlle;

import controlle.MainDao;
import controlle.LoginDao;
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
import java.util.ArrayList;

/**
 *
 * @author thuc
 */
public class ServerControl implements Runnable{

    private Connection con;
    private ServerSocket myServer;
    private int serverPort = 3001;
    Socket clientSocket;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    private ArrayList<Account> listResult = new ArrayList<>();

    public ServerControl() {
    }
    
    public ServerControl(Socket clientSocket) {
        this.clientSocket =clientSocket ;
        try{
            ois = new ObjectInputStream(clientSocket.getInputStream());
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
        }catch(IOException e){
            e.printStackTrace();
        }
//        MainDao mainDao =new MainDao() ;
//        openServer(serverPort);
//        while (true) {
//            listenning();
//        }
    }

//    private void openServer(int portNumber) {
//        try {
//            myServer = new ServerSocket(portNumber);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    private void listenning() {
//        System.out.println("da den listenning");
//        try {
////            Socket clientSocket = myServer.accept();
////            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
////            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
//            Object o = ois.readObject();
//            System.out.println("tai khoan " +o);
//            LoginDao  loginDao =new LoginDao();
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
//    }

    @Override
    public void run() {
       System.out.println("da den listenning");
        try {
//            Socket clientSocket = myServer.accept();
//            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
//            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            Object o = ois.readObject();
            System.out.println("tai khoan " +o);
            LoginDao  loginDao =new LoginDao();
            
            if (o instanceof Account) {
                Account account = (Account) o;
                if (loginDao.checkLogin(account)) {
                    oos.writeObject("ok");
                } else {
                    oos.writeObject("false");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
