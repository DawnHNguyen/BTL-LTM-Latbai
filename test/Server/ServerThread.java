/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Message;
import model.Type;

/**
 *
 * @author Admin
 */
public class ServerThread extends Thread {

    private int Id;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    private Socket clientSocket;
    private Connection con = ConnectDB.getConnect();
    private static ArrayList<ServerThread> listThread = new ArrayList();

    public ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            System.out.println(clientSocket);
             oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(clientSocket.getInputStream());
           
            while (true) {
                Object o = ois.readObject();
                if (o instanceof Message) {
                    Message m = (Message) o;
                    System.out.println(m.getM());
                    if (m.getM().equals("getList")) {
                        System.out.println(m.getM());
                        ArrayList<Account> listAll = getListALL();
                        SendMess(new Message(listAll,"getList" ));
                    }
                    
                }
            }
//             clientSocket.close();
//             this.ois.close();
//             this.oos.close();
//             System.out.println(clientSocket);
//             System.out.println(clientSocket.isClosed());
//             System.out.println(clientSocket.isConnected());
//             System.out.println(clientSocket.isInputShutdown());
//             System.out.println(clientSocket.isOutputShutdown());
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SendMess(Message m) throws IOException {
        oos.writeObject(m);
        System.out.println("done!");
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    public ArrayList<Account> getListALL() throws SQLException {
        ArrayList<Account> listALl = new ArrayList();
        String query = "Select * FROM user ";
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String username = rs.getString("username");
            String password = rs.getString("password");
            int point = rs.getInt("point");
            boolean status = rs.getBoolean("status");
           
            Account u1 = new Account(id, username, name, point);
            listALl.add(u1);
        }
        return listALl;
    }
}
