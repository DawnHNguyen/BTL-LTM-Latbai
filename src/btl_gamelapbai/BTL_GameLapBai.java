/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl_gamelapbai;

import controller.MainController;
import controller.auth.LoginController;
import controller.bxhController;
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
//            
//            bxhController control = new bxhController();
//
//        MainController mainController = new MainController();
//        LoginController control = new LoginController(mainController);

        MainController mainController = new MainController();
        LoginController control = new LoginController();
    }
    
}
