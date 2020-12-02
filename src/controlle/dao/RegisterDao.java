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
                    "insert into tblAccount(username,password,name,point,status) value(?,?,?,?,?)");

            pre.setString(1, acc.getUserName());
            pre.setString(2, acc.getPassWord());
            pre.setString(3, acc.getUserName());
            pre.setInt(4, acc.getPoint());
            pre.setInt(5, acc.getStatus());
            int result = pre.executeUpdate();
            if (result == 1) {
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
