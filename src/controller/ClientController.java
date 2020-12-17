/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.auth.LoginController;
import controller.auth.RegisterController;
import controller.game.GameController;
import controller.history.HistoryController;
import controller.homepage.HomePageController;
import controller.rank.RankController;
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
import view.game.MessageView;

/**
 *
 * @author dolong
 */
public class ClientController {

    private static Socket mySocket;
//    private String serverHost = "192.168.43.57";
//  pivate String serverHost = "192.168.43.196";    
    private String serverHost = "localhost";
//    private String serverHost = "172.19.201.17";
    private static int serverPort = 3000;
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;

    private Account currentAccount;
    private Account accountRecived;
    private Game game;
    private ArrayList<Account> listUser;
    
    private LoginController loginController;
    private HomePageController homePageController;
    private RegisterController registerController;
    private RankController rankController;
    private GameController gameController;
    private HistoryController historyController;
    Runnable listenChallenge;
    static Thread thread;

    public ClientController() {
        openConnection();
        loginController = new LoginController();
        listenChallenge = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Message result = null;
                    result = receiveData();
                    System.out.println(result.getType());
                    if (result instanceof Message) {
                        switch (result.getType()) {
                            case LOGIN_SUCCESS:
                                currentAccount = (Account) result.getContent();
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
                                registerController.showMessage("Register not success");
                                break;
                            case LIST_ONLINE:
                                listUser = (ArrayList<Account>) result.getContent();
                                if (!listUser.isEmpty()) {
                                    homePageController.reciveListUser(listUser);
                                    homePageController.displayUsers();
                                }
                                break;
                            case UPDATE_LIST_ONLINE:
                                listUser = (ArrayList<Account>) result.getContent();
                                if (!listUser.isEmpty()) {
                                    homePageController.updateUsersOnline(listUser);
                                }
                                break;
                            case INVITE_CHALLENGE:
                                accountRecived = (Account) result.getContent();
                                int isAccept = homePageController.showConfirmDialog(accountRecived.getName() + " want to challege you in a game");
                                if (isAccept == JOptionPane.YES_OPTION) {
                                    Message response = new Message(accountRecived, ACCEPT_CHALLENGE);
                                    ClientController.sendData(response);
                                } else {
                                    Message response = new Message(accountRecived, REJECT_CHALLENGE);
                                    ClientController.sendData(response);
                                }
                                break;
                            case REJECT_CHALLENGE:
                                accountRecived = (Account) result.getContent();
                                homePageController.showMessage(accountRecived.getName() + " DONT WANT TO PLAY WITH YOU!");
                                break;
                            case PLAYING:
                                accountRecived = (Account) result.getContent();
                                homePageController.showMessage(accountRecived.getName() + " playing a game with someone else!");
                                break;
                            case ACCEPT_CHALLENGE:
                                game = (Game) result.getContent();
                                gameController = new GameController(game, currentAccount);
                                break;
                            case RANKING:
                                listUser = (ArrayList<Account>) result.getContent();
                                rankController = new RankController(currentAccount);
                                rankController.reciveListUser(listUser);
                                rankController.displayUsers();
                                break;
                            case HISTORY_GAME:
                                ArrayList<Game> historyGames = (ArrayList<Game>) result.getContent();
                                historyController = new HistoryController(currentAccount);
                                historyController.reciveListGame(historyGames);
                                historyController.displayGame();
                                rankController.setViewVisible(false);
                                break;
                            case RESULT_GAME:
                                System.out.println("content " + (String) result.getContent());
                                gameController.showMessage((String) result.getContent());
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
