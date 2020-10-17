/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl_gamelapbai;

import controller.ClientControl;

/**
 *
 * @author dolong
 */
public class BTL_GameLapBai {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        ClientView view = new ClientView();
        ClientControl control = new ClientControl();
        control.actionPerformed();
//        view.setVisible(true);
    
    }
    
}
