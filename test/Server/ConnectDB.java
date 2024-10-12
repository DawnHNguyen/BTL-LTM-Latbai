/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ConnectDB {

    public static Connection getConnect() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/game_online", "root", "123a");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
//        Connection con = new ConnectDB().getConnect();
//        String query = "Select * FROM user Where username = 'doquocviet' and password  = '123' ";
//        Statement st;
//        try {
//            st = con.createStatement();
//            ResultSet rs = st.executeQuery(query);
//            System.out.println(rs.next());
//            while(rs.next()){
//                System.out.println(rs.getString(2));
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }

    }
}
