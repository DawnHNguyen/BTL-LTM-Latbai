/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.MainController;
import controller.homepage.HomePageController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;
import model.Account;
import model.Message;
import model.Type;
import view.auth.RegisterView;
import view.bxh.Rank;
import view.homepage.HomePageView;

public class bxhController {
    
    private Rank rank;
    ArrayList<Account> listUser;
    Account account;
    public bxhController() {
        this.account = account;
        this.listUser = reciveListUser();
        this.rank= new Rank(this.listUser);
        this.rank.setVisible(true);
        this.rank.addHomeAcction(new HomeAction());
    }

    public ArrayList<Account> reciveListUser() {
        ArrayList<Account> listUser = new ArrayList<Account>();
        try {
            MainController.sendData(new Message(null, Type.RANKING));
            Message result = MainController.receiveData();
            if (result instanceof Message) {
                listUser = (ArrayList<Account>) result.getContent();
            }
            Collections.sort(listUser, new bxhController.PointComparator());
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
    // quay về trang home
     class HomeAction implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ah) {
               new HomePageController(account);
                
                rank.setVisible(false);
        }
    }
}
