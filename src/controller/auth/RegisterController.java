/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.auth;

import controller.MainController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Account;
import model.Message;
import view.auth.RegisterView;

public class RegisterController {

    private RegisterView registerView;
    private MainController mainController;

    public RegisterController(MainController mainController) {
        this.mainController = mainController;
        this.registerView = new RegisterView(mainController);
        this.registerView.addRegisterAction(new RegisterAction());
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
                    mainController.sendData(message);
                    Message result = mainController.receiveData();
                    if (result instanceof Message) {
                        if (result.getType() == model.Type.REGISTER_SUCCESS) {
                            JOptionPane.showMessageDialog(registerView, "Register success");
                            Account acc = (Account) result.getContent();
                            System.out.println("user name is" + acc.getUserName());
                            registerView.dispose();
                        } else {
                            JOptionPane.showMessageDialog(registerView, "Registern not success");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(registerView, "Check your login!!");
                }
            }
        }
    }
}
