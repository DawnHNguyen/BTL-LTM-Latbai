/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import model.Account;

/**
 *
 * @author thuc
 */
public class ThreadServer implements Runnable{
    private ArrayList<Account> listAcount =new ArrayList<>() ;
    private ServerSocket myServer ;
    Socket clientSocket ;
    private int port =3001 ;
    MainDao mainDao =new MainDao() ;
    ObjectOutputStream oos=null ;
    ObjectInputStream ois =null ;
    
    public ThreadServer(){
        
    }
   
    
    public void openServer(){
        try{
            myServer =new ServerSocket(port) ;
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            openServer();
            while(!Thread.currentThread().interrupted()){
                try{
                clientSocket =myServer.accept() ;
                ServerControl sc=new ServerControl(clientSocket);
                new Thread(sc).start();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
//            Thread.sleep(100);
//            clientSocket.close();
        }
    }
}
