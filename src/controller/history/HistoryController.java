/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.history;

import controller.homepage.HomePageController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import model.Account;
import model.Game;
import view.history.HistoryView;

/**
 *
 * @author Admin 88
 */
public class HistoryController {

    private HistoryView historyView;
    ArrayList<Game> listGame;
    Account account;
    public HistoryController(Account account) {
        this.account = account;       
    }
    public void displayGame() {
        this.historyView = new HistoryView(this.listGame, account);
        this.historyView.setVisible(true);
        this.historyView.setTable(listGame);
        this.historyView.addHomeAcction(new HomePageAction());
    }
    public void reciveListGame(ArrayList<Game> listGame) {
        this.listGame = listGame;
    }
    // quay về trang home
     class HomePageAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ah) {
            historyView.setVisible(false);
            HomePageController.setViewVisible(true);
        }

        
    }
     
    
}
