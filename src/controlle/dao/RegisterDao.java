/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.User;

/**
 *
 * @author thuc
 */
public class RegisterDao extends MainDao {

    public RegisterDao() {
        super();
    }

    public Account createAccount(Account acc) {
        Connection conn = getConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(
                    "insert into tblAccount(username,password) value(?,?)");

            pre.setString(1, acc.getUserName());
            pre.setString(2, acc.getPassWord());
            int result = pre.executeUpdate();
            if (result == 1) {
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getIdAccount(Account acc) {
        User user = new User();
        int id =-1;
        System.out.println("acc.."+acc.getUserName() );
        try {
            PreparedStatement pre = conn.prepareStatement("SELECT tblAccount.id FROM tblAccount \n"
                    + "WHERE tblAccount.username = ? and tblAccount.password = ?");
            pre.setString(1, acc.getUserName());
            pre.setString(2, acc.getPassWord());
            ResultSet rs = pre.executeQuery();
//            System.out.println("idd..........."+rs.getInt("id"));
            if (rs.next()) {
                id = rs.getInt("id");
                System.out.println("idd..........."+rs.getInt("id"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id ;
    }

    public boolean createUser(User user) {
        Connection conn = getConnection();
        int id_account = getIdAccount(user.getAccount()) ;
        System.out.println("id accout.."+id_account);
        try {
            PreparedStatement pre = conn.prepareStatement(
                    "insert into tblUser(name,point,id_account,status) value(?,?,?,?)");
            pre.setString(1, user.getName());
            pre.setInt(2, user.getPoint());
            pre.setInt(3, id_account);
            pre.setInt(4, user.getStatus());
            int result = pre.executeUpdate();
            if (result == 1) {
//                user.setName(user.getName());
//                user.setPoint(0);
//                user.setStatus(1);
//                user.setAccount(acc);
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
