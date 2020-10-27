/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Account;

/**
 *
 * @author thuc
 */
public class loginDao extends MainDao{
    public loginDao () {
        super() ;
    }
    
    public boolean checkLogin (Account acc ){
        Connection conn = super.getConnection();
        
        try{
            PreparedStatement pre = conn.prepareStatement("select * from tblUser"  //table
                    + " where username = ? and password = ?");
            pre.setString(0, acc.getUserName());
            pre.setString(0, acc.getPassWord() );
            ResultSet rs = pre.executeQuery() ;
            if(rs.next())   return true ;
        }catch(SQLException e){
            System.out.println("SQL exception " +e);
        }
        return false ;
    }
}
