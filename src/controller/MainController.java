/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.auth.LoginController;
import controller.auth.RegisterController;
import controller.homepage.HomePageController;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Account;
import model.Game;
import model.Message;
import static model.Type.ACCEPT_CHALLENGE;
import static model.Type.INVITE_CHALLENGE;
import model.Type;
import static model.Type.PLAYING;
import static model.Type.REJECT_CHALLENGE;
import view.game.GameLatBai;

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

    private Account currentAccount;
    private Account accountRecived;
    private LoginController loginController;
    private HomePageController homePageController;
    private RegisterController registerController;
    Runnable listenChallenge;
    static Thread thread;

    public MainController() {
        openConnection();
        loginController = new LoginController();
        listenChallenge = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Message result = null;
                    result = receiveData();
                    if (result instanceof Message) {
                        switch (result.getType()) {
                            case LOGIN_SUCCESS:
                                currentAccount = (Account) result.getContent();
                                loginController.showMessage("Login success");
                                loginController.setViewVisible(false);
                                homePageController = new HomePageController(currentAccount);
                                break;
                            case LOGIN_FAIL:
                                loginController.showMessage("User not available");
                                break;
                            case REGISTER_SUCCESS:
                                currentAccount = (Account) result.getContent();
                                registerController.showMessage("Register success");
                                homePageController = new HomePageController(currentAccount);
                                break;
                            case REGISTER_FAIL:
                                registerController.showMessage("Registern not success");
                                break;
                            case LIST_ONLINE:
                                ArrayList<Account> listUser = (ArrayList<Account>) result.getContent();
                                System.out.println("list user");
                                homePageController.reciveListUser(listUser);
                                homePageController.displayUsers();
                                break;
                            case INVITE_CHALLENGE:
                                accountRecived = (Account) result.getContent();
                                int isAccept = homePageController.showConfirmDialog(accountRecived.getName() + " want to challege you in a game");
                                if (isAccept == JOptionPane.YES_OPTION) {
                                    Message response = new Message(accountRecived, ACCEPT_CHALLENGE);
                                    System.out.println("accept");
                                    MainController.sendData(response);
                                } else {
                                    Message response = new Message(accountRecived, REJECT_CHALLENGE);
                                    MainController.sendData(response);
                                }
                                break;
                            case REJECT_CHALLENGE:
                                accountRecived = (Account) result.getContent();
                                homePageController.showMessage(accountRecived.getName() + " dont want to challege you in a game");
                                break;
                            case PLAYING:
                                accountRecived = (Account) result.getContent();
                                homePageController.showMessage(accountRecived.getName() + " playing a game with someone else!");
                                break;
                            case ACCEPT_CHALLENGE:
                                Game game = (Game) result.getContent();
                                new GameLatBai(0, 0, game.getDebai());
                                break;
                        }
                    }
                }
            }
        };
        thread = new Thread(listenChallenge);
        thread.start();
    }

    public void openConnection() {
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
