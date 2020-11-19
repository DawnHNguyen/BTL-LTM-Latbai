/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlle.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Account;

/**
 *
 * @author thuc
 */
public class ListOnlineDao extends MainDao{
    public ListOnlineDao (){
        super();
        Connection conn = getConnection();
    }
    
    public ArrayList<Account> listOnline(){
        ArrayList<Account>  listAccount= new ArrayList<>();
      try{          
        PreparedStatement pre = conn.prepareStatement("select * from tblAccount where status = 1") ;
        pre.executeQuery() ;
        
      }catch(SQLException e){
          e.printStackTrace();
      }
      return  listAccount ;
             
    }
}
