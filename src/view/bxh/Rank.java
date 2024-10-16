/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.bxh;

import controller.MainController;
import controller.bxhController;
import model.Message;
import model.Account;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Type;

/**
 *
 * @author Admin
 */
public class Rank extends javax.swing.JFrame {

    /**
     * Creates new form Rank
     */
    DefaultTableModel model;
    private Frame frame;
    ArrayList<Account> listUsers = new ArrayList();

    public Rank(ArrayList<Account> listUsers) {
        initComponents();

        this.setLocationRelativeTo(null);
        model = (DefaultTableModel) tblUser.getModel();
        this.listUsers = listUsers;
        System.out.println(listUsers.size());
        for (Account listUser : listUsers) {
            System.out.println("12334567890 " + listUser.getName());
        }
       
    }
     private MainController mainController;
    public Rank(MainController mainController) {
      
        ArrayList<Account> listUsers;
        initComponents();
        this.mainController = mainController;
        
        this.setLocationRelativeTo(null);
       
         
        
    }
    public Rank() {
        initComponents();
        init();
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        btlhome = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(606, 443));

        tblUser.setBackground(new java.awt.Color(0, 102, 102));
        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên", "Điểm", "Rank"
            }
        ));
        jScrollPane1.setViewportView(tblUser);

        btlhome.setText("Quay lại");
        btlhome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlhomeActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Bảng Xếp Hạng Người Chơi ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btlhome, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(181, 181, 181))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(126, 126, 126))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btlhome)
                .addContainerGap(12, Short.MAX_VALUE))
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

    private void btlhomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlhomeActionPerformed
        // TODO add your handling code here:
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btlhomeActionPerformed
   
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

     public void init(){
         MainController mcl = new MainController();
         mcl.sendDataFake(new Message(null,"getList"));
        Message mgs = mcl.receiveDataFake();
         System.out.println(mgs.getM());
        listUsers= (ArrayList<Account>)mgs.getContent();
         setTable();
     }
     public void setTable() {
        DefaultTableModel model = (DefaultTableModel) tblUser.getModel();
        ArrayList<Account> List = listUsers;
        List.sort(new Comparator<Account>() {
            @Override
            public int compare(Account p1, Account p2) {
                if (p1.getPoint() > p2.getPoint()) {
                    return -1;
                } else if (p1.getPoint() < p2.getPoint()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        model.setRowCount(0);
        for (int i = 0; i < List.size(); i++) {
            model.addRow(new Object[]{
               List.get(i).getName(), List.get(i).getPoint(), i + 1
            });
        };
    }
//    public void addRegisterAction(ActionListener al){
//        btnRegister.addActionListener(al);
//    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Rank().setVisible(true);
            }
        });
    }
   
    public void addHomeAcction(ActionListener ah){
        btlhome.addActionListener(ah);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btlhome;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUser;
    // End of variables declaration//GEN-END:variables
}
