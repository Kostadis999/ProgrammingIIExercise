/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class Login_Page extends javax.swing.JFrame {
 
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    /**
     * Creates new form Login_Page
     */
    public Login_Page() {
        conn = covid.DAO.ConnectDB();
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogSignUp = new javax.swing.JDialog();
        jPanelSignUp = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        SignUpButton = new javax.swing.JButton();
        BackToLogInButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanelLoginFrame = new javax.swing.JPanel();
        jPanelLoginInfo = new javax.swing.JPanel();
        PASSWORDTextField = new javax.swing.JPasswordField();
        jLabelPass = new javax.swing.JLabel();
        jLabelUname = new javax.swing.JLabel();
        jButtonSignUp = new javax.swing.JButton();
        LOGINButton = new javax.swing.JButton();
        USERNAMETextField = new javax.swing.JTextField();
        jLabelLoginIcon = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaCollab = new javax.swing.JTextArea();
        jButtonHelp = new javax.swing.JButton();

        jDialogSignUp.setTitle("Sign up");
        jDialogSignUp.setBounds(new java.awt.Rectangle(400, 200, 394, 350));
        jDialogSignUp.setForeground(new java.awt.Color(255, 102, 0));

        jPanelSignUp.setBackground(new java.awt.Color(255, 0, 0));
        jPanelSignUp.setForeground(new java.awt.Color(255, 255, 255));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Sign up"));

        jLabel4.setText("Retype Password:");

        jLabel5.setText("Password:");

        jLabel6.setText("Username:");

        jLabel7.setText("email:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2)
                            .addComponent(jPasswordField1)
                            .addComponent(jPasswordField2)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        SignUpButton.setText("Sign Up");
        SignUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpButtonActionPerformed(evt);
            }
        });

        BackToLogInButton.setText("Back to Log In");
        BackToLogInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackToLogInButtonActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/covid/info.png"))); // NOI18N
        jButton3.setText("Help");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSignUpLayout = new javax.swing.GroupLayout(jPanelSignUp);
        jPanelSignUp.setLayout(jPanelSignUpLayout);
        jPanelSignUpLayout.setHorizontalGroup(
            jPanelSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSignUpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelSignUpLayout.createSequentialGroup()
                        .addComponent(SignUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BackToLogInButton)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanelSignUpLayout.setVerticalGroup(
            jPanelSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSignUpLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BackToLogInButton)
                    .addGroup(jPanelSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SignUpButton)
                        .addComponent(jButton3)))
                .addContainerGap(117, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogSignUpLayout = new javax.swing.GroupLayout(jDialogSignUp.getContentPane());
        jDialogSignUp.getContentPane().setLayout(jDialogSignUpLayout);
        jDialogSignUpLayout.setHorizontalGroup(
            jDialogSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelSignUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogSignUpLayout.setVerticalGroup(
            jDialogSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelSignUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CCMS: Συστημα Καταχώρησης κρουσμάτων κορονοιού ");
        setResizable(false);

        jPanelLoginFrame.setBackground(new java.awt.Color(204, 0, 0));

        jPanelLoginInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Login", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 14))); // NOI18N

        jLabelPass.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        jLabelPass.setText("Password:");

        jLabelUname.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        jLabelUname.setText("Username:");

        jButtonSignUp.setText("Sign up");
        jButtonSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSignUpActionPerformed(evt);
            }
        });

        LOGINButton.setText("Login");
        LOGINButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOGINButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLoginInfoLayout = new javax.swing.GroupLayout(jPanelLoginInfo);
        jPanelLoginInfo.setLayout(jPanelLoginInfoLayout);
        jPanelLoginInfoLayout.setHorizontalGroup(
            jPanelLoginInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoginInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLoginInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLoginInfoLayout.createSequentialGroup()
                        .addGroup(jPanelLoginInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelUname)
                            .addComponent(jLabelPass))
                        .addGap(32, 32, 32)
                        .addGroup(jPanelLoginInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(USERNAMETextField, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(PASSWORDTextField)))
                    .addGroup(jPanelLoginInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(LOGINButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSignUp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanelLoginInfoLayout.setVerticalGroup(
            jPanelLoginInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoginInfoLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanelLoginInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUname)
                    .addComponent(USERNAMETextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanelLoginInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPass)
                    .addComponent(PASSWORDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(LOGINButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSignUp))
        );

        jLabelLoginIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/covid/cov.jpg"))); // NOI18N

        jTextAreaCollab.setBackground(new java.awt.Color(240, 240, 240));
        jTextAreaCollab.setColumns(20);
        jTextAreaCollab.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jTextAreaCollab.setRows(5);
        jTextAreaCollab.setText("Αντωνόπουλος Κωνσταντίνος\nΜαρία Gord\nΒασίλης Μίχας\nΧριστίνα Ιωάννα Σμυρλόγλου\nΠαναγιώτης Σερεμέτης \nΧριστίνα Βραγχλιότη\nΜαγδαλινή Λάη \n\n\n\n");
        jTextAreaCollab.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "COLLABORATORS :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 18))); // NOI18N
        jTextAreaCollab.setPreferredSize(new java.awt.Dimension(256, 150));
        jScrollPane2.setViewportView(jTextAreaCollab);

        jButtonHelp.setText("Help");
        jButtonHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHelpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLoginFrameLayout = new javax.swing.GroupLayout(jPanelLoginFrame);
        jPanelLoginFrame.setLayout(jPanelLoginFrameLayout);
        jPanelLoginFrameLayout.setHorizontalGroup(
            jPanelLoginFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoginFrameLayout.createSequentialGroup()
                .addComponent(jButtonHelp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelLoginFrameLayout.createSequentialGroup()
                .addGroup(jPanelLoginFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelLoginIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanelLoginFrameLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelLoginInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(444, 444, 444))
        );
        jPanelLoginFrameLayout.setVerticalGroup(
            jPanelLoginFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLoginFrameLayout.createSequentialGroup()
                .addComponent(jLabelLoginIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 358, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLoginFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelLoginInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jButtonHelp))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelLoginFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 617, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelLoginFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LOGINButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOGINButtonActionPerformed
        String sql = "select * from USERS  where username = ? and password = ?";
        try{
            pst= conn.prepareStatement(sql);
            pst.setString(1,USERNAMETextField.getText());
            pst.setString(2,PASSWORDTextField.getText());
            rs = pst.executeQuery();
            if (rs.next()){
                JOptionPane.showMessageDialog(null,"Welcome to CCMS!!");
                pst.close();
                rs.close();
                CasesSystem s = new CasesSystem();
                s.setVisible(true);
                this.setVisible(false);
            }
            else {
                JOptionPane.showMessageDialog(null,"Username and Password is not correct");
            }

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

    }//GEN-LAST:event_LOGINButtonActionPerformed

    private void jButtonSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSignUpActionPerformed
        jDialogSignUp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonSignUpActionPerformed

    private void SignUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpButtonActionPerformed
        String query = "Select count(*)  from USERS where username = '"+jTextField1.getText()+"'";
        if(jPasswordField2.getText().equals("") || jPasswordField1.getText().equals("") || jTextField1.getText().equals("") || jTextField2.getText().equals("")){
            JOptionPane.showMessageDialog(null,"please fill all fields to continue");
        }
        else{
            try{
                pst = conn.prepareStatement(query);
                rs = pst.executeQuery();
                rs.next();
                int x = rs.getInt("count(*)");
                rs.close();
                String i = jPasswordField2.getText();
                String g = jPasswordField1.getText();
                if (x == 0){
                    if(g.equals(i)){
                        String q = "insert into USERS (username, password) values (?,?)";
                        pst = conn.prepareStatement(q);
                        pst.setString(1,jTextField1.getText());
                        pst.setString(2,jPasswordField1.getText());
                        pst.execute();
                        JOptionPane.showMessageDialog(null,"User created Succesfully");
                        CasesSystem a = new CasesSystem();
                        a.setVisible(true);
                        jDialogSignUp.dispose();
                        this.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"'Password' and 'Retype Password' fields don't match");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"The username that you selected is already beeing used ");
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }        
        }
    }//GEN-LAST:event_SignUpButtonActionPerformed

    private void BackToLogInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackToLogInButtonActionPerformed
        jDialogSignUp.setVisible(false);
        this.setVisible(true);
    }//GEN-LAST:event_BackToLogInButtonActionPerformed

    private void jButtonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHelpActionPerformed
        JOptionPane.showMessageDialog(this," Welcome to CCMS!!! \n\n\nCCMS is a Covid Cases Management System."
        +"\nif it is your first time using this app from this pc"
        +"\ncreate a user to enter the system"
        +"by pressing the Sign up button. \nOnce your user has been succesfully create you will automatically enter the system.");
    }//GEN-LAST:event_jButtonHelpActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JOptionPane.showMessageDialog(jDialogSignUp,"fill the textfileds with your prefered credentials\n"
                + "and press ths 'Sign Up' button to create your user  ");
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Login_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login_Page().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackToLogInButton;
    private javax.swing.JButton LOGINButton;
    private javax.swing.JPasswordField PASSWORDTextField;
    private javax.swing.JButton SignUpButton;
    public static javax.swing.JTextField USERNAMETextField;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonHelp;
    private javax.swing.JButton jButtonSignUp;
    private javax.swing.JDialog jDialogSignUp;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelLoginIcon;
    private javax.swing.JLabel jLabelPass;
    private javax.swing.JLabel jLabelUname;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelLoginFrame;
    private javax.swing.JPanel jPanelLoginInfo;
    public static javax.swing.JPanel jPanelSignUp;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaCollab;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
