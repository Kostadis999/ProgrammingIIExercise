/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author kosta
 */
public class death_or_restore extends javax.swing.JFrame {
static Connection conn = null;
static ResultSet rs = null;
static PreparedStatement pst = null;
    /**
     * Creates new form death_or_restore
     */
    public death_or_restore() {
        initComponents();
        conn = javaconnect.ConnectDB();
        Fillcombodelete();
    }

    public static void Fillcombodelete(){
        try{
            String sql = "Select * from CURRENTCASES";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jComboBox1.insertItemAt("", 0);
            while(rs.next()){
                String ID = rs.getString("ID");
                jComboBox1.addItem(ID);
            }
            rs.close();
            pst.close();
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PassedButton = new javax.swing.JButton();
        HealButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PassedButton.setText("Απεβίωσε");
        PassedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PassedButtonActionPerformed(evt);
            }
        });

        HealButton.setText("Ανάρρωσε");
        HealButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HealButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Case id:");

        jLabel2.setText("Παρακαλώ συμπληρώστε το ID του κρούσματος που επιθημείτε να διαγράψετε");

        jLabel3.setText("και στη συνέχεια διευκρινείστε αν ο αστενής απεβίωσε η ανάρρωσε.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(PassedButton)
                        .addGap(18, 18, 18)
                        .addComponent(HealButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HealButton)
                    .addComponent(PassedButton)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PassedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PassedButtonActionPerformed
        try{
            String sql = "select count(*) from CURRENTCASES where ID =  '"+jComboBox1.getSelectedItem().toString()+"' ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            pst.close();
            if(jComboBox1.getSelectedItem().toString().equals("") ){
                JOptionPane.showMessageDialog(null, "Πληκτρολογίστε το ID του ασθενή που επιθυμείς να διαγράψεις");
            } 
            else if (count == 0) {
                JOptionPane.showMessageDialog(null, "Το ID που πληκτρολογίσατε δεν αντιστοιχεί σε κάποιο ασθενή");
            }
            else {
                String quer = "insert into PASSED select * from CURRENTCASES where ID    = '"+jComboBox1.getSelectedItem().toString()+"' ";
                pst = conn.prepareStatement(quer);
                pst.execute();
                String query= "delete from CURRENTCASES where ID = '" +jComboBox1.getSelectedItem().toString()+ "' ";
                pst = conn.prepareStatement(query);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Data Deleted");
                pst.close();	
                CasesSystem.Update_table();
                dispose();
                CasesSystem.setnumberofodeaths();
                CasesSystem.setAverageAge();
                CasesSystem.setnumberofcurrentcases();
            }
            }catch(Exception e){
          JOptionPane.showMessageDialog(null,e);
                
        }
    }//GEN-LAST:event_PassedButtonActionPerformed

    private void HealButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HealButtonActionPerformed
        try{
            String que = "select count(*) from CURRENTCASES where ID =  '"+jComboBox1.getSelectedItem().toString()+"' ";
            pst = conn.prepareStatement(que);
            rs = pst.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            pst.close();
            if(jComboBox1.getSelectedItem().toString().equals("") ) {
                JOptionPane.showMessageDialog(null, "Πληκτρολογίστε το ID του ασθενή που επιθυμείς να διαγράψεις");
            }
            else if (count == 0) {
                JOptionPane.showMessageDialog(null, "Το ID που πληκτρολογίσατε δεν αντιστοιχεί σε κάποιο ασθενή");
            }
            else {
                String quer = "insert into HEAL select * from CURRENTCASES where ID = '"+jComboBox1.getSelectedItem().toString()+"' ";
                
                pst = conn.prepareStatement(quer);
                String query= "delete from CURRENTCASES where ID = '" +jComboBox1.getSelectedItem().toString()+ "' ";
                pst = conn.prepareStatement(query);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Data Deleted");
                pst.close();	
                CasesSystem.Update_table();
                CasesSystem.setnumberofoheals();
                dispose();
                CasesSystem.setAverageAge();
                CasesSystem.setnumberofcurrentcases();
                
            }
            
        }catch(Exception e){
          JOptionPane.showMessageDialog(null,e);
                
        }
    }//GEN-LAST:event_HealButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(death_or_restore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(death_or_restore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(death_or_restore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(death_or_restore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new death_or_restore().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton HealButton;
    private javax.swing.JButton PassedButton;
    public static javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
