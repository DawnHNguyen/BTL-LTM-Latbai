/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Vector;


public class User {
    private String name;
    private int point;
    private String status;
    private Account account;

    public Account getAccount() {
        return account;
    }

    public User(String name, int point, String status) {
        this.name = name;
        this.point = point;
        this.status = status;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object[] toObjects() {
        return new Object[]{name,point,status};
    }
    
}
