/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlle;

import controlle.dao.ListOnlineDao;
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
import model.User;

/**
 *
 * @author thuc
 */
public class ServerControl implements Runnable {

    private Connection con;
    private LoginDao loginDao;
    private RegisterDao registerDao;
    private ListOnlineDao listOnline;
    private ServerSocket myServer;
    private int serverPort = 3001;
    private ArrayList<Account> listResult = new ArrayList<>();
    Socket clientSocket;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    public ServerControl() {
    }

    public ServerControl(Socket clientSocket) {
        this.clientSocket = clientSocket;
        loginDao = new LoginDao();
        registerDao = new RegisterDao();
        listOnline = new ListOnlineDao();
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

                    switch (mesReceive.getType()) {
                        case LOGIN:
                            System.out.println("yeu cau login");
                            Account checkAcc = loginDao.checkLogin(acc);
                            if (checkAcc != null) {
                                mesSend = new Message(checkAcc, Type.LOGIN_SUCCESS);
                            } else {
                                mesSend = new Message(checkAcc, Type.LOGIN_FAIL);
                            }
                            break;
                        case LOGOUT:
                            System.out.println("id..."+acc.getId());
                            loginDao.setStatus(acc.getId(), 0);
                            break;

                        case REGISTER:
                            Account addAcc = registerDao.createAccount(acc);
                            if (addAcc != null) {
                                mesSend = new Message(addAcc, Type.REGISTER_SUCCESS);
                            } else {
                                mesSend = new Message(addAcc, Type.REGISTER_FAIL);
                            }
                            break;
                        case LIST_ONLINE:
                            listResult = listOnline.listOnline(acc);
                            mesSend = new Message(listResult, Type.LIST_ONLINE);
                            break;
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
