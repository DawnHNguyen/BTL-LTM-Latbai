/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.Serializable;

<<<<<<< HEAD
import java.io.Serializable;


public class Account implements Serializable {
=======
/**
 *
 * @authmạngor dolong
 */
public class Account implements Serializable{
>>>>>>> aef791e111ca5579223cfcd94d96dc1eaada62a6
    private String userName;
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
}
