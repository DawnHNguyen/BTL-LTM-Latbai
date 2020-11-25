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
import model.Account;
import model.User;

/**
 *
 * @author thuc
 */
public class LoginDao extends MainDao {

    public LoginDao() {
        super();
        Connection conn = getConnection();
    }

    public User checkLogin(Account acc) {
        User user = new User();
        try {
            PreparedStatement pre = conn.prepareStatement("select * from tblAccount" //table
                    + " where username = ? and password = ?");
            pre.setString(1, acc.getUserName());
            pre.setString(2, acc.getPassWord());
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int idAccount = rs.getInt("id");      
                setStatus(idAccount);
                user = getUser(acc);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void setStatus(int idAccount) {
        try {
            PreparedStatement pre = conn.prepareStatement("update tblUser set status= 1" //table
                    + " where id_account = ?");
            pre.setInt(1, idAccount);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(Account acc) {
        User user = new User();
        try {
            PreparedStatement pre = conn.prepareStatement("SELECT tblUser.name, tblUser.point FROM tblAccount, tblUser\n"
                    + "WHERE tblUser.id_account = tblAccount.id and tblAccount.username = ?");
            pre.setString(1, acc.getUserName());
            ResultSet rs = pre.executeQuery();
             if (rs.next()) {
                 user.setName(rs.getString(1));
                 user.setPoint(rs.getInt(2));
                 user.setStatus(0);
                 user.setAccount(acc);
                 return user;
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
