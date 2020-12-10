/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.homepage;

import controller.MainController;
import controller.auth.LoginController;
import controller.rank.RankController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class HomePageController {

    static HomePageView homePageView;
    ArrayList<Account> listUser;
    Account account;

    public HomePageController(Account account) {
        this.account = account;
        this.listUser = reciveListUser();
        this.homePageView = new HomePageView(this.listUser, account);
        this.homePageView.setVisible(true);
        this.homePageView.addLogoutAcction(new LogoutAction());
        this.homePageView.addInviteAcction(new InviteAction());
        this.homePageView.addRankingAcction(new RankingAction());
    }

    public ArrayList<Account> reciveListUser() {
        ArrayList<Account> listUser = new ArrayList<>();
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

    public static void setViewVisible() {
        homePageView.setVisible(true);
        homePageView.startThread();
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
                LoginController.setViewVisible();
                homePageView.dispose();
            }
        }
    }

    class InviteAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Account acc = homePageView.getAccountSelected();
            Message message = new Message(acc, model.Type.CHALLENGE);
            if (message instanceof Message) {
                MainController.sendData(message);
                System.out.println("da gui");
////                homePageView.dispose();
            }
        }
    }

    class RankingAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            homePageView.setVisible(false);
            homePageView.stopThread();
            System.out.println("ok");
            new RankController(account);
        }
    }

}
