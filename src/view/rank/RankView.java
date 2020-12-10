/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.rank;

import model.Account;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dolong
 */
public class RankView extends javax.swing.JFrame {

    DefaultTableModel model;
    private Frame frame;
    ArrayList<Account> listUsers;

    public RankView(ArrayList<Account> listUsers, Account account) {
        initComponents();
        this.setLocationRelativeTo(null);
        model = (DefaultTableModel) tblUser.getModel();
        this.listUsers = listUsers;
        jlbName.setText("Xin chao "+account.getName());
        setTable(listUsers);
        System.out.println("here");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        btlHome = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jlbName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(606, 443));

        tblUser.setBackground(new java.awt.Color(216, 244, 244));
        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rank", "Tên", "Điểm"
            }
        ));
        jScrollPane1.setViewportView(tblUser);

        btlHome.setText("Quay lại");
        btlHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlHomeActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Bảng Xếp Hạng Người Chơi ");

        jlbName.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlbName)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(200, 200, 200)
                            .addComponent(jLabel1))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(57, 57, 57)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(168, 168, 168)
                            .addComponent(btlHome, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jlbName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btlHome)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btlHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlHomeActionPerformed
        
    }//GEN-LAST:event_btlHomeActionPerformed
   
//    public Account getAccountSelected() {
//        int row = tblUser.getSelectedRow();
//        String name = tblUser.getValueAt(row, 1).toString();
//        int point = (int) tblUser.getValueAt(row, 2);
//        System.out.println("name selected " + name + " " + "point" + point);
//        for (Account acc : listUsers) {
//            if (acc.getName().equals(name) && acc.getPoint() == point) {
//                return acc;
//            }
//        }
//        return null;
//    }
    
     public void setTable(List<Account> list) {
        model.setRowCount(0);
        if (list instanceof ArrayList) {
            int i = 1;
            for (Account user : list) {
                model.addRow(user.toObjects(i++));
            }
        }
    }
   
    public void addHomeAcction(ActionListener ah){
        btlHome.addActionListener(ah);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btlHome;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbName;
    private javax.swing.JTable tblUser;
    // End of variables declaration//GEN-END:variables
}
