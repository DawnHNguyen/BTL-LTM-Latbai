/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.game;

import controller.MainController;
import controller.homepage.HomePageController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Account;
import model.Game;
import model.Message;
import view.game.GameLatBai;

/**
 *
 * @author dolong
 */
public class GameController {

    private GameLatBai gameLatBai;
    private Game game;
    private Account account;

    public GameController(Game game, Account account) {
        this.game = game;
        this.account = account;
        gameLatBai = new GameLatBai(0, 0, game);
        gameLatBai.addCancelAcction(new CancelAction());
    }

    class CancelAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int isAccept = JOptionPane.showConfirmDialog(gameLatBai,
                    "I appear as part of the frame!!", "Customized Dialog",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (isAccept == 0) {
                game.getPlayer1().setPoint(0);
                Message message = new Message(game, model.Type.RESULT_GAME);
                if (message instanceof Message) {
                    MainController.sendData(message);
                    HomePageController.setViewVisible(true);
                }
            }
        }
    }

}
