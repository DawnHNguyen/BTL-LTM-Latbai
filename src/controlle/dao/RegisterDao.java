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
public class RegisterDao extends MainDao{
    public  RegisterDao(){
        super();
    }
    public Account CreateAccount(Account acc){
        Connection conn =getConnection() ;
        try {
            PreparedStatement pre = conn.prepareStatement(
                    "insert into tblAccount(username,password) value(?,?)");
            
            pre.setString(1,acc.getUserName());
            pre.setString(2, acc.getPassWord());
            int result = pre.executeUpdate() ;
            if(result==1){
                return  acc ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterDao.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }
    
    public int getIdAccount (Account acc){
         User user = new User();
        try {
            PreparedStatement pre = conn.prepareStatement("SELECT tblAccount.id,FROM tblAccount \n"
                    + "WHERE tblAccount.username = ?");
            pre.setString(1, acc.getUserName());
            ResultSet rs = pre.executeQuery();
             if (rs.next()) {
 
                 return rs.getInt("id") ;
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }
    
    public User CreateUser(String name,Account acc){
        Connection conn =getConnection() ;
         User user = new User() ;
        try {
            PreparedStatement pre = conn.prepareStatement(
                    "insert into tblUser(name,point,id_account,status) value(?,0, ,1)");
            
            pre.setString(1,name);
            int result = pre.executeUpdate();
            if(result == 1){
                 user.setName(name);
                 user.setPoint(0);
                 user.setStatus(1);
                 user.setAccount(acc);
                return  user ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterDao.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }
}
