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
import javax.swing.JOptionPane;
import model.Account;
import model.Message;
import model.Type;
import static model.Type.LIST_ONLINE;
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
        MainController.sendData(new Message(null, LIST_ONLINE));
    }

    public void displayUsers() {
        this.homePageView = new HomePageView(this.listUser, account);
        this.homePageView.setVisible(true);
        this.homePageView.setTable(listUser);
        this.homePageView.addLogoutAction(new LogoutAction());
        this.homePageView.addInviteAction(new InviteAction());
        this.homePageView.addRankingAction(new RankingAction());
    }

    public void updateUsersOnline(ArrayList<Account> listUser) {
        this.listUser = listUser;
        for(Account acc: listUser){
            System.out.println(acc.getName()+ " acc "+ acc.getId());
        }
        if (listUser != null && homePageView != null) {
            this.homePageView.setTable(listUser);
        }
    }

    public void reciveListUser(ArrayList<Account> listUser) {
        this.listUser = listUser;
    }

    public static void setViewVisible(boolean isVisible) {
        homePageView.setVisible(isVisible);
    }

    public void showMessage(String content) {
        JOptionPane.showMessageDialog(homePageView, content);
    }

    public int showConfirmDialog(String content) {
        return JOptionPane.showConfirmDialog(homePageView, content);
    }

    class LogoutAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Message message = new Message(account, model.Type.LOGOUT);
            if (message instanceof Message) {
                MainController.sendData(message);
                homePageView.dispose();
                LoginController.setViewVisible(true);
            }
        }
    }

    class InviteAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Account acc = homePageView.getAccountSelected();
            System.out.println("Moi " + acc.getId());
            Message message = new Message(acc, model.Type.CHALLENGE);
            if (message instanceof Message) {
                MainController.sendData(message);
                System.out.println("da gui");
            }
        }
    }

    class RankingAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            homePageView.setVisible(false);
            MainController.sendData(new Message(null, Type.RANKING));
        }
    }

}
