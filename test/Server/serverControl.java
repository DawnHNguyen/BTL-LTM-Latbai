/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//di nguoi lai voi quyen loi 
package Server;


import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author van hieu
 *
 *
 *
 */
public class serverControl  {

    public ServerSocket control;
    public Socket s;
    public serverControl() throws IOException {
        control = new ServerSocket(3000);
        new Thread(() -> {
            while (true) {
                try {
                    Socket s=control.accept();
                    Thread lh=new Thread(new ServerThread(s));
                    lh.start();
                } catch (IOException ex) {
                    Logger.getLogger(serverControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 

        }).start();
    }

}
