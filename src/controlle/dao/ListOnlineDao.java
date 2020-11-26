/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlle.dao;

import com.mysql.cj.xdevapi.Result;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Account;
import model.User;

/**
 *
 * @author thuc
 */
public class ListOnlineDao extends MainDao {

    public ListOnlineDao() {
        super();
        Connection conn = getConnection();
    }
    
    public ArrayList<User> listOnline() {
        ArrayList<User> listUser = new ArrayList<>();
        try {            
            PreparedStatement pre = conn.prepareStatement("select * from tblUser where status = 1");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                User user = new User() ;
                user.setName(rs.getString("name"));
                user.setPoint(rs.getInt("point"));
                user.setStatus(rs.getInt("status"));
                listUser.add(user);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUser;
        
    }
}
