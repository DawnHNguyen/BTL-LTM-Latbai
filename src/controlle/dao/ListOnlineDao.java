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

    public ArrayList<Account> listOnline(Account account) {
        ArrayList<Account> listAccount = new ArrayList<>();
        try {
            PreparedStatement pre = conn.prepareStatement("select * from tblAccount where status = 1 "
                    + "and id != ?");
            pre.setInt(1, account.getId());
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setUserName(rs.getString("username"));
                acc.setPassWord(rs.getString("password"));
                acc.setName(rs.getString("name"));
                acc.setPoint(rs.getInt("point"));
                acc.setStatus(rs.getInt("status"));
                acc.setId(rs.getInt("id"));
                listAccount.add(acc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listAccount;

    }
}
