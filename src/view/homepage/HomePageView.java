/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.homepage;

import controller.MainController;
import controller.homepage.HomePageController;
import controller.rank.RankController;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Account;
import model.Message;
import static model.Type.ACCEPT_CHALLENGE;
import static model.Type.INVITE_CHALLENGE;
import static model.Type.PLAYING;
import static model.Type.REJECT_CHALLENGE;
import view.auth.LoginView;
import view.rank.RankView;

/**
 *
 * @author dolong
 */
public class HomePageView extends javax.swing.JFrame {

    DefaultTableModel model;
    ArrayList<Account> listUsers;
    private Account account;
    Runnable listenChallenge;
    Thread t = new Thread(listenChallenge);
    public HomePageView(ArrayList<Account> listUsers, Account account) {
        initComponents();
        this.setLocationRelativeTo(null);
        model = (DefaultTableModel) tblUser.getModel();
        this.listUsers = listUsers;
        setTable(listUsers);
        jlbAccount.setText("Xin chao "+account.getName());
        listenChallenge = new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    Message result = null;
                    result = MainController.receiveData();
                    Account account = (Account) result.getContent();
                    if (result instanceof Message) {
                        result = (Message) result;
                        if (result.getType() == INVITE_CHALLENGE) {
                            int isAccept = JOptionPane.showConfirmDialog(null, account.getName() + " want to challege you in a game");
                            if (isAccept == JOptionPane.YES_OPTION) {
                                Message response = new Message(account, ACCEPT_CHALLENGE);
                                System.out.println("accept");
                                MainController.sendData(response);
                            } else {
                                Message response = new Message(account, REJECT_CHALLENGE);
                                MainController.sendData(response);
                            }
                        }
                        if (result.getType() == ACCEPT_CHALLENGE) {
                            System.out.println("doi ti...");
                        }
                        if (result.getType() == REJECT_CHALLENGE) {
                            JOptionPane.showMessageDialog(null, account.getName() + " dont want to challege you in a game");
                        }
                        if (result.getType() == PLAYING) {
                            JOptionPane.showMessageDialog(null, account.getName() + " playing a game with someone else!");
                        }
                    }
                }
            }
        };
//        Thread t = new Thread(listenChallenge);
        t.start();
    }

    public void addLogoutAcction(ActionListener al) {
        jbtLogout.addActionListener(al);
    }

    public void addInviteAcction(ActionListener al) {
        jbtInvite.addActionListener(al);
    }

    public void addRankingAcction(ActionListener al) {
        jbtRanking.addActionListener(al);
    }

    public Account getAccountSelected() {
        int row = tblUser.getSelectedRow();
        String name = tblUser.getValueAt(row, 1).toString();
        int point = (int) tblUser.getValueAt(row, 2);
        System.out.println("name selected " + name + " " + "point" + point);
        for (Account acc : listUsers) {
            if (acc.getName().equals(name) && acc.getPoint() == point) {
                return acc;
            }
        }
        return null;
    }

    public void showMessage(String mess) {
        JOptionPane.showMessageDialog(this, mess);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        jbtInvite = new javax.swing.JButton();
        jbtLogout = new javax.swing.JButton();
        jbtRanking = new javax.swing.JButton();
        jlbAccount = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 48)); // NOI18N
        jLabel1.setText("Home Page");

        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rank", "Player", "Point", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblUser);

        jbtInvite.setBackground(new java.awt.Color(10, 97, 226));
        jbtInvite.setText("Invite");
        jbtInvite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtInviteActionPerformed(evt);
            }
        });

        jbtLogout.setBackground(new java.awt.Color(233, 18, 18));
        jbtLogout.setText("Logout");
        jbtLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtLogoutActionPerformed(evt);
            }
        });

        jbtRanking.setText("Ranking");
        jbtRanking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRankingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jbtInvite, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtRanking, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtLogout)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlbAccount)
                        .addGap(70, 70, 70))))
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtLogout)
                    .addComponent(jlbAccount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtInvite)
                    .addComponent(jbtRanking))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtLogoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtLogoutActionPerformed

    private void jbtInviteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtInviteActionPerformed
//        Account acc = getAccountSelected();

    }//GEN-LAST:event_jbtInviteActionPerformed

    private void jbtRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRankingActionPerformed

    }//GEN-LAST:event_jbtRankingActionPerformed

    public void setTable(List<Account> list) {
        model.setRowCount(0);
        if (list instanceof ArrayList) {
            int rank = 1;
            for (Account user : list) {
                model.addRow(user.toObjects(rank++));
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtInvite;
    private javax.swing.JButton jbtLogout;
    private javax.swing.JButton jbtRanking;
    private javax.swing.JLabel jlbAccount;
    private javax.swing.JTable tblUser;
    // End of variables declaration//GEN-END:variables

}
