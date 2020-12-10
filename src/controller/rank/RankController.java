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
        this.listUser = reciveListUser();
        this.rankView= new RankView(this.listUser, account);
        this.rankView.setVisible(true);
        this.rankView.addHomeAcction(new HomeAction());
    }

    public ArrayList<Account> reciveListUser() {
        ArrayList<Account> listUser = new ArrayList<Account>();
        try {
            MainController.sendData(new Message(null, Type.RANKING));
            Message result = MainController.receiveData();
            if (result instanceof Message) {
                listUser = (ArrayList<Account>) result.getContent();
                for(Account account: listUser){
                    System.out.println(account.getName());
                }
            }
            Collections.sort(listUser, new RankController.PointComparator());
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
            HomePageController.setViewVisible();
            rankView.setVisible(false);
        }
    }
}
