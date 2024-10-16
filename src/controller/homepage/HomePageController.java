/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.homepage;

import controller.MainController;
import controller.auth.RegisterController;
import controller.bxhController;
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
import view.bxh.Rank;

/**
 *
 * @author dolong
 */
public class HomePageController{

    HomePageView homePageView;
    ArrayList<Account> listUser;
    Account account;
//    private MainController mainController;
    public HomePageController(Account account){
        this.account = account;
        this.listUser = reciveListUser();
        this.homePageView = new HomePageView(this.listUser);
        this.homePageView.setVisible(true);
        this.homePageView.addLogoutAcction(new LogoutAction());
        this.homePageView.addInviteAcction(new InviteAction());
        this.homePageView.addRankAction(new RankAction());
    }

    public ArrayList<Account> reciveListUser() {
        ArrayList<Account> listUser = new ArrayList<Account>();
        try {
            MainController.sendData(new Message(account, Type.LIST_ONLINE));
            Message result = MainController.receiveData();
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
                MainController.sendData(message);
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
                MainController.sendData(message);
////                homePageView.dispose();
            }
        }
    }
    class RankAction implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
                new bxhController();
                
                homePageView.setVisible(false);
        }
    }
     
}
