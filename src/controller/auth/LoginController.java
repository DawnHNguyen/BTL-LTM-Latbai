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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Account;
import model.Message;
import view.auth.LoginView;
import view.auth.RegisterView;

/**
 *
 * @author dolong
 */
public class LoginController{
    private LoginView loginView;
    private MainController mainController;
    public LoginController(MainController mainController) {
        super();
        this.mainController = mainController;
        this.loginView = new LoginView();
        this.loginView.setVisible(true);    
        this.loginView.addLoginAction(new LoginAction());
        this.loginView.addRegisterAction(new RegisterAction());
    }

    class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            Account account = loginView.getAccount();
            Message message = new Message(account, model.Type.LOGIN);
            if (message instanceof Message) {
                mainController.sendData(message);
                Message result = mainController.receiveData();
                if (result instanceof Message) {
                    if (result.getType() != model.Type.LOGIN_SUCCESS) {
                        JOptionPane.showMessageDialog(loginView, "User not available");
                    } else {
                        JOptionPane.showMessageDialog(loginView, "Login success");
                        Account acc = (Account)result.getContent();
                        System.out.println("ID "+acc.getId());
                        new HomePageController(acc,mainController);
                        loginView.setVisible(false);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(loginView, "Check your login!!");
            }
        }
    }
    class RegisterAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            new RegisterController();
        }
    }
}
