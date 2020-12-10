/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author dolong
 */
public class Game implements Serializable{
    private static final long serialVersionUID = 1L;
    private Account player1;
    private Account player2;
    private Account winnerAccount;
    private int[][] debai;

    public Account getPlayer1() {
        return player1;
    }

    public void setPlayer1(Account player1) {
        this.player1 = player1;
    }

    public Account getPlayer2() {
        return player2;
    }

    public void setPlayer2(Account player2) {
        this.player2 = player2;
    }

    public Account getWinnerAccount() {
        return winnerAccount;
    }

    public void setWinnerAccount(Account winnerAccount) {
        this.winnerAccount = winnerAccount;
    }

    public int[][] getDebai() {
        return debai;
    }

    public void setDebai(int[][] debai) {
        this.debai = debai;
    }
    
}
