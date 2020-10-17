package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import model.User;
//import view.ClientView;

public class ClientControl {

//    private ClientView view;
    private String serverHost = "192.168.1.152";
    private int serverPort = 3001;
    User user = new User();

    public void actionPerformed() {
        System.out.println("qwrrwqrqw");
        try {
            System.out.println("123");
            Socket mySocket = new Socket(serverHost, serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeBytes("allooo");

            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            Object o = ois.readObject();
            System.out.println(o);
            mySocket.close();
        } catch (Exception ex) {
//                view.showMessage(ex.getStackTrace().toString());
        }
    }

}
