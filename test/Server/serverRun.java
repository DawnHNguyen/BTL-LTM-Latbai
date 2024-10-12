/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class serverRun {
    public static void main(String[] args) {
        try {
            System.out.println("Running....");
            serverControl server = new serverControl();
        } catch (IOException ex) {
            Logger.getLogger(serverRun.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
