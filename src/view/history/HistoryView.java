/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.history;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Account;
import model.Game;

/**
 *
 * @author Admin 88
 */
public class HistoryView extends javax.swing.JFrame {
DefaultTableModel model;
    ArrayList<Game> listGames;
    private Account account;
    public HistoryView(ArrayList<Game> listGames, Account account) {
        initComponents();
        this.setLocationRelativeTo(null);
        model = (DefaultTableModel) tblGame.getModel();
        this.listGames = listGames;
        jlbAccount.setText("Xin chao "+account.getName());
        System.out.println("history...");
        System.out.println("here");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        label1 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGame = new javax.swing.JTable();
        btlHome = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jlbAccount = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        label1.setText("label1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblGame.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Player 1", "Player 2", "Winner", "Time"
            }
        ));
        jScrollPane1.setViewportView(tblGame);

        btlHome.setText("Back");
        btlHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlHomeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        jLabel2.setText("History");

        jlbAccount.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btlHome)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbAccount)
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jlbAccount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btlHome)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btlHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlHomeActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_btlHomeActionPerformed

    public void setTable(List<Game> list) {
        model.setRowCount(0);
        if (list instanceof ArrayList) {
            int i = 1;
            for (Game game : list) {
                model.addRow(game.toObjects(i++));
            }
        }
    }
    
    public void addHomeAcction(ActionListener ah){
        btlHome.addActionListener(ah);
    }
    
    
    /**
     * @param args the command line arguments
     */
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btlHome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbAccount;
    private java.awt.Label label1;
    private javax.swing.JTable tblGame;
    // End of variables declaration//GEN-END:variables
}
