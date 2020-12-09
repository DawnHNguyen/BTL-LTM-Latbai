/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.homepage;

import controller.MainController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Message;
import model.Type;
import view.auth.LoginView;
import view.homepage.HomePageView;

/**
 *
 * @author dolong
 */
public class HomePageController{

    HomePageView homePageView;
    ArrayList<Account> listUser;
    Account account;
    private MainController mainController;
    public HomePageController(Account account, MainController mainController){
        this.mainController = mainController;
        this.account = account;
        this.listUser = reciveListUser();
        this.homePageView = new HomePageView(this.listUser, mainController);
        this.homePageView.setVisible(true);
        this.homePageView.addLogoutAcction(new LogoutAction());
        this.homePageView.addInviteAcction(new InviteAction());
//        new ReadThread().run();
    }

    public ArrayList<Account> reciveListUser() {
        ArrayList<Account> listUser = new ArrayList<Account>();
        try {
            mainController.sendData(new Message(account, Type.LIST_ONLINE));
            Message result = mainController.receiveData();
            if (result instanceof Message) {
                listUser = (ArrayList<Account>) result.getContent();
            }
            Collections.sort(listUser, new PointComparator());
            return listUser;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listUser;
    }
    

    class PointComparator implements Comparator<Account> {
        
        @Override
        public int compare(Account user1, Account user2) {
            int point1 = user1.getPoint();
            int point2 = user2.getPoint();
            if (point1 == point2) {
                return 0;
            } else if (point1 < point2) {
                return 1;
            } else {
                return -1;
            }
        }
    }
    
    class LogoutAction implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            Message message = new Message(account, model.Type.LOGOUT);
            if (message instanceof Message) {
                mainController.sendData(message);
                homePageView.dispose();
            }
        }
    }
    
    class InviteAction implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            Account acc = homePageView.getAccountSelected();
            System.out.println(acc.getName());
            Message message = new Message(acc, model.Type.CHALLENGE);
            System.out.println("invite");
            if (message instanceof Message) {
                mainController.sendData(message);
////                homePageView.dispose();
            }
        }
    }
    
    public class ReadThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                Message result = null;
                System.out.println("1");
                Object o = mainController.receiveData();
                System.out.println("2");
                System.out.println("here");
                if (o instanceof Message) {
                    result = (Message) o;
                    System.out.println("xinn chaooo");
                }
            }
        }
    }
    
}
