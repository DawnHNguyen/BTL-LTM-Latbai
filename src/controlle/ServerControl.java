/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlle;

import controlle.dao.MainDao;
import controlle.dao.LoginDao;
import controlle.dao.RegisterDao;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Message;
import model.Type;

/**
 *
 * @author thuc
 */
public class ServerControl implements Runnable {

    private Connection con;
    private LoginDao loginDao ;
    private RegisterDao registerDao ;
    private ServerSocket myServer;
    private int serverPort = 3001;
    Socket clientSocket;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    private ArrayList<Account> listResult = new ArrayList<>();

    public ServerControl() {
    }

    public ServerControl(Socket clientSocket) {
        this.clientSocket = clientSocket;
        loginDao = new LoginDao();
        registerDao =new RegisterDao() ;
        try {
            ois = new ObjectInputStream(clientSocket.getInputStream());
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Object o = ois.readObject();
                if (o instanceof Message) {
                    Message mesSend = new Message();
                    Message mesReceive = (Message) o;
                    Account acc = (Account) mesReceive.getContent();
                    if (mesReceive.getType() == Type.LOGIN){
                        Account checkAcc = loginDao.checkLogin(acc);
                        if (checkAcc !=null) {
                            mesSend = new Message(checkAcc, Type.LOGIN_SUCCESS);
                        }
                        else{
                            mesSend = new Message(checkAcc, Type.LOGIN_FAILL);
                        }
                    }
                    else if (mesReceive.getType() == Type.REGISTER) {
                        Account addAcc = registerDao.CreateAccount(acc);
                        mesSend = new Message(addAcc, Type.REGISTER_SUCCESS);
                    }
                    oos.writeObject(mesSend);
                }

                Thread.sleep(100);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                ois.close();
                oos.close();
                clientSocket.close();
            } catch (IOException ex1) {
                Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
    }

}
