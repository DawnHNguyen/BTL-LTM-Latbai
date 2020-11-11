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

public class RegisterController extends MainController {

    private RegisterView registerView;

    public RegisterController(RegisterView registerView) {
        super();
        this.registerView = registerView;
        this.registerView.addRegisterAction(new RegisterAction());
    }

    class RegisterAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Account account = registerView.getAccount();
            Message message = new Message(account, model.Type.REGISTER);
            if (message instanceof Message) {
                sendData(message);
                Message result = receiveData();
                if (result instanceof Message) {
                    if (result.getType() != model.Type.LOGIN_SUCCESS) {
                        JOptionPane.showMessageDialog(registerView, "User not available");
                    } else {
                        JOptionPane.showMessageDialog(registerView, "Login success");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(registerView, "Check your login!!");
            }
        }
    }
}
