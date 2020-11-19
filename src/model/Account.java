/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = 6529685098267757692L;
    private String userName;
    private String passWord;
    private int status ;

    public Account() {
    }

    public Account(String userName, String passWord, int status) {
        this.userName = userName;
        this.passWord = passWord;
        this.status = status;
    }

    public Account(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
    

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
