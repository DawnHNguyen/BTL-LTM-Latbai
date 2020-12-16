/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.rank;

import controller.MainController;
import controller.homepage.HomePageController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import model.Account;
import model.Message;
import model.Type;
import view.rank.RankView;

public class RankController {
    
    private RankView rankView;
    ArrayList<Account> listUser;
    Account account;
    public RankController(Account account) {
        this.account = account;
        
    }
    public void displayUsers() {
        this.rankView = new RankView(this.listUser, account);
        this.rankView.setVisible(true);
        this.rankView.setTable(listUser);
        this.rankView.addHomeAcction(new HomeAction());
    }
    public void reciveListUser(ArrayList<Account> listUser) {
        this.listUser = listUser;
        Collections.sort(listUser, new RankController.PointComparator());
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
            HomePageController.setViewVisible(true);
            rankView.setVisible(false);
        }
    }
}
