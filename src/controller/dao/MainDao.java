/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author thuc
 */
public class MainDao {
    private Connection conn ;
    private String jdbcURL="jdbc:mysql://localhost:3306/demo_keo_tha" ;
    private String jdbcUsername = "root" ;
    private String jdbcPassword ="123456" ;
    public MainDao(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver") ;
            conn =DriverManager.getConnection(jdbcURL,
                    jdbcUsername, jdbcPassword) ;
        }catch(SQLException e){
            System.out.println("SQL exception" +e);
        }catch(ClassNotFoundException e){
            System.out.println("Not Found Exception " +e );
        }
    }
      public Connection getConnection(){
        return conn;
    }
}
