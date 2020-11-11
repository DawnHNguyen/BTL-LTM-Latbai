/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl_gamelapbai;

import controller.auth.LoginController;
import view.auth.LoginView;

/**
 *
 * @author dolong
 */
public class BTL_GameLapBai {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        LoginController control = new LoginController(loginView);
    }
    
}
