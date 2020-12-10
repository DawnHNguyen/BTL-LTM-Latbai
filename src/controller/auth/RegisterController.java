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
import view.auth.RegisterView;

public class RegisterController{

    private RegisterView registerView;

    public RegisterController() {
        this.registerView = new RegisterView();
        this.registerView.setVisible(true);
        this.registerView.addRegisterAction(new RegisterAction());
    }
    public void showMessage(String content) {
        JOptionPane.showMessageDialog(registerView, content);
    }

    class RegisterAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!registerView.checkPassword()) {
                JOptionPane.showMessageDialog(registerView, "Mat khau phai trung nhau");
            } else {
                Account account = registerView.getAccount();
                Message message = new Message(account, model.Type.REGISTER);
                if (message instanceof Message) {
                    MainController.sendData(message);
                } else {
                    JOptionPane.showMessageDialog(registerView, "Check your login!!");
                }
            }
        }
    }
}
