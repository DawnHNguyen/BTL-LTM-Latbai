/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.auth;

import controller.MainController;
import controller.homepage.HomePageController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Account;
import model.Message;
import view.auth.LoginView;

/**
 *
 * @author dolong
 */
public class LoginController extends MainController{
    private LoginView loginView;
    private HomePageController homePageController;
    
    public LoginController(LoginView loginView) {
        super();
        this.loginView = loginView;
        this.loginView.setVisible(true);
        this.loginView.addLoginAction(new LoginAction());
    }

    class LoginAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.out.println("123");
            Account account = loginView.getAccount();
            System.out.println("username "+account.getUserName());
            Message message = new Message(account, model.Type.LOGIN);
            if (message instanceof Message) {
                sendData(message);
                Message result = receiveData();
                System.out.println(result.getType());
                if (result instanceof Message) {
                    if (result.getType() != model.Type.LOGIN_SUCCESS) {
                        JOptionPane.showMessageDialog(loginView, "User not available");
                    } else {
                        JOptionPane.showMessageDialog(loginView, "Login success");
                        Account acc = (Account)result.getContent();
                        System.out.println("ID"+acc.getId());
                        homePageController = new HomePageController(acc);
                        loginView.setVisible(false);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(loginView, "Check your login!!");
            }
        }
    }
}
