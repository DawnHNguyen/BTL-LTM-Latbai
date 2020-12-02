/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlle.dao;

import com.mysql.cj.xdevapi.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 * 
 * @author thuc
 */
public class Invite  extends MainDao{
    public Invite(){
        super();
    }
    public void GetAccout(Account acc){
        Connection con = getConnection() ;
        try {
            PreparedStatement pre = con.prepareStatement( "select * from tblAccount where id=?");
            pre.setInt(1, acc.getId());
            ResultSet rs = pre.executeQuery() ;
            if(rs.next()){
                System.out.println("ten tai khoan..." +acc.getName());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Invite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void SendInvite( Account accInvite,Account accReceive){
       
    }
}
