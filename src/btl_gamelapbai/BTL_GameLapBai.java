/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl_gamelapbai;

import controlle.ServerControl;
import controlle.ThreadServer;
import view.ServerView;

/**
 *
 * @author dolong
 */
    public class BTL_GameLapBai {

    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadServer());
        thread.start();
    }    
}
