/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.homepage;

import controller.MainController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Message;
import model.User;
import view.homepage.HomePageView;

/**
 *
 * @author dolong
 */
public class HomePageController extends MainController{
    HomePageView homePageView;
    ArrayList<User> listUser;
    public HomePageController(){
//        super();
System.out.println("2");
        this.listUser = reciveListUser();
        System.out.println("ok");
        this.homePageView = new HomePageView(this.listUser);
        this.homePageView.setVisible(true);
    }
    public ArrayList<User> reciveListUser(){
        ArrayList<User> listUser = new ArrayList<User>();
        try{
            Message message = receiveData();
            listUser.add(new User("Long",3, "Online"));
            listUser.add(new User("Thuc",2, "Online"));
            listUser.add(new User("Nam",5, "Online"));
//            listUser = (ArrayList<User>)message.getContent();
            Collections.sort(listUser, new PointComparator());
            return listUser;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return listUser;
    }
    
    class PointComparator implements Comparator<User> {
        @Override
        public int compare(User user1, User user2) {
            int point1 = user1.getPoint();
            int point2 = user2.getPoint();
            if (point1 == point2) {
                return 0;
            } else if (point1 > point2) {
                return 1;
            } else {
                return -1;
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("1");
        new HomePageController();
    }
}
