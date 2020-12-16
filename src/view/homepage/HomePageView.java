package view.homepage;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Account;

/**
 *
 * @author dolong
 */
public class HomePageView extends javax.swing.JFrame {

    DefaultTableModel model;
    ArrayList<Account> listUsers;
    private Account account;

    public HomePageView(ArrayList<Account> listUsers, Account account) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.account = account;
        model = (DefaultTableModel) tblUser.getModel();
        this.listUsers = listUsers;
        setTable(listUsers);
        jlbAccount.setText("Xin chao " + account.getName());
    }

    public void addLogoutAction(ActionListener al) {
        jbtLogout.addActionListener(al);
    }

    public void addInviteAction(ActionListener al) {
        jbtInvite.addActionListener(al);
    }

    public void addRankingAction(ActionListener al) {
        jbtRanking.addActionListener(al);
    }

    public Account getAccountSelected() {
        int row = tblUser.getSelectedRow();
        String name = tblUser.getValueAt(row, 1).toString();
        int point = (int) tblUser.getValueAt(row, 2);
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
        jLabel1.setText("Home");

        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stt", "Player", "Point", "Status"
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlbAccount)
                                .addGap(70, 70, 70))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addComponent(jLabel1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtLogout)
                            .addComponent(jlbAccount)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtInvite)
                    .addComponent(jbtRanking))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtLogoutActionPerformed
    }//GEN-LAST:event_jbtLogoutActionPerformed
    private void jbtInviteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtInviteActionPerformed
    }//GEN-LAST:event_jbtInviteActionPerformed
    private void jbtRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRankingActionPerformed
    }//GEN-LAST:event_jbtRankingActionPerformed

    public void setTable(ArrayList<Account> listUser) {
        this.listUsers = listUser;
        model.setRowCount(0);
        int rank = 1;
        for (Account acc : listUser) {
            if (!acc.equals(this.account)) {
                model.addRow(acc.toObjects(rank++));
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
