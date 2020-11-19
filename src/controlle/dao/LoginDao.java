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

/**
 *
 * @author thuc
 */
public class LoginDao extends MainDao {

    public LoginDao() {
        super();
        Connection conn = getConnection();
    }

    public Account checkLogin(Account acc) {
        try {
            PreparedStatement pre = conn.prepareStatement("select * from tblAccount" //table
                    + " where username = ? and password = ?");
            pre.setString(1, acc.getUserName());
            pre.setString(2, acc.getPassWord());
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                setStatus(acc);
                return acc;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setStatus(Account acc) {
        try{
            PreparedStatement pre = conn.prepareStatement("update tblAccount set status=1" //table
                + " where username = ? and password = ?");
        pre.setString(1, acc.getUserName());
        pre.setString(2, acc.getPassWord());
        pre.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
