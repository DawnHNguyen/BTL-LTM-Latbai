package view;

import controller.login.ServerControl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thuc
 */
public class ServerView {
     public ServerView() {
        new ServerControl();
        showMessage("TCP server is running...");
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }
}
