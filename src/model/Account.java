/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @authmạngor dolong
 */
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userName;
    private String passWord;
    private String name;
    private int point;
    private String status;
    private int id;

    public Account() {
    }

    public Account(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public Account(String userName, String passWord, String name) {
        this.userName = userName;
        this.passWord = passWord;
        this.name = name;
    }

    public Account(int id, String userName,String name, int point) {
        this.userName = userName;
        this.passWord = passWord;
        this.name = name;
        this.point = point;
        this.status = status;
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object[] toObjects(int rank) {
        return new Object[]{rank, name, point};
    }
}
