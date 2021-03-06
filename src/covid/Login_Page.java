
package covid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

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
     * για τη δημιουργία της γραφικής απεικόνισης χρησιμοποιήθηκε το windows builder
     * που παρέχει το netbeans.
     * 
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogSignUp = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jTextField2 = new javax.swing.JTextField();
        SignUpButton = new javax.swing.JButton();
        BackToLogInButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanelLoginFrame = new javax.swing.JPanel();
        jButtonHelp = new javax.swing.JButton();
        jLabelUname1 = new javax.swing.JLabel();
        USERNAMETextField = new javax.swing.JTextField();
        PASSWORDTextField = new javax.swing.JPasswordField();
        LOGINButton1 = new javax.swing.JButton();
        jButtonSignUp = new javax.swing.JButton();
        jLabelPass1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jDialogSignUp.setTitle("Sign up");
        jDialogSignUp.setBounds(new java.awt.Rectangle(400, 200, 394, 350));
        jDialogSignUp.setForeground(new java.awt.Color(255, 102, 0));

        jPanel1.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Username:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(10, 90, 90, 14);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Password:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(10, 130, 100, 14);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Retype Pass:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 170, 110, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("email:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(10, 210, 90, 14);
        jPanel1.add(jTextField1);
        jTextField1.setBounds(130, 80, 120, 30);
        jPanel1.add(jPasswordField2);
        jPasswordField2.setBounds(130, 120, 120, 30);
        jPanel1.add(jPasswordField1);
        jPasswordField1.setBounds(130, 160, 120, 30);
        jPanel1.add(jTextField2);
        jTextField2.setBounds(130, 200, 120, 30);

        SignUpButton.setText("Sign Up");
        SignUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpButtonActionPerformed(evt);
            }
        });
        jPanel1.add(SignUpButton);
        SignUpButton.setBounds(130, 250, 90, 23);

        BackToLogInButton.setText("Back to Log In");
        BackToLogInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackToLogInButtonActionPerformed(evt);
            }
        });
        jPanel1.add(BackToLogInButton);
        BackToLogInButton.setBounds(0, 250, 120, 23);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/covid/info.png"))); // NOI18N
        jButton3.setText("Help");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(240, 250, 30, 25);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/covid/cov.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 0, 390, 350);

        javax.swing.GroupLayout jDialogSignUpLayout = new javax.swing.GroupLayout(jDialogSignUp.getContentPane());
        jDialogSignUp.getContentPane().setLayout(jDialogSignUpLayout);
        jDialogSignUpLayout.setHorizontalGroup(
            jDialogSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
        );
        jDialogSignUpLayout.setVerticalGroup(
            jDialogSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CCMS: Συστημα Καταχώρησης κρουσμάτων κορονοιού ");
        setResizable(false);

        jPanelLoginFrame.setLayout(null);

        jButtonHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/covid/info.png"))); // NOI18N
        jButtonHelp.setText("Help");
        jButtonHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHelpActionPerformed(evt);
            }
        });
        jPanelLoginFrame.add(jButtonHelp);
        jButtonHelp.setBounds(500, 200, 30, 25);

        jLabelUname1.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        jLabelUname1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUname1.setText("Username:");
        jPanelLoginFrame.add(jLabelUname1);
        jLabelUname1.setBounds(20, 40, 65, 20);
        jPanelLoginFrame.add(USERNAMETextField);
        USERNAMETextField.setBounds(130, 40, 140, 30);
        jPanelLoginFrame.add(PASSWORDTextField);
        PASSWORDTextField.setBounds(130, 80, 140, 30);

        LOGINButton1.setText("Login");
        LOGINButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOGINButton1ActionPerformed(evt);
            }
        });
        jPanelLoginFrame.add(LOGINButton1);
        LOGINButton1.setBounds(20, 140, 80, 23);

        jButtonSignUp.setText("Sign up");
        jButtonSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSignUpActionPerformed(evt);
            }
        });
        jPanelLoginFrame.add(jButtonSignUp);
        jButtonSignUp.setBounds(130, 140, 100, 23);

        jLabelPass1.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        jLabelPass1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPass1.setText("Password:");
        jPanelLoginFrame.add(jLabelPass1);
        jLabelPass1.setBounds(20, 90, 61, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/covid/cov.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanelLoginFrame.add(jLabel1);
        jLabel1.setBounds(0, 0, 570, 240);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelLoginFrame, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelLoginFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(569, 276));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LOGINButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOGINButton1ActionPerformed
        String sql = "select * from USERS  where username = ? and password = ?";
        try{/*ελέγχει αν τα στοιχεία που έχει εισάγει ο χρήστης στα textfields βρισκονται στη βάση
            αν ναι ο χρήστης εισέρχεται στ συστημα
            */
            pst= conn.prepareStatement(sql);
            pst.setString(1,USERNAMETextField.getText());
            pst.setString(2,PASSWORDTextField.getText());
            rs = pst.executeQuery();
            if (rs.next()){
                JOptionPane.showMessageDialog(null,"καλωσήρθατε στο CCMS!!");
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
    }//GEN-LAST:event_LOGINButton1ActionPerformed

    private void jButtonSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSignUpActionPerformed
        jDialogSignUp.setVisible(true);
        this.setVisible(false);//εμφανιζει το διάλογο για το sign-up
    }//GEN-LAST:event_jButtonSignUpActionPerformed

    private void jButtonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHelpActionPerformed
        JOptionPane.showMessageDialog(this," καλωσήρθατε στο CCMS!!! \n\n\nΤΟ CCMS  (Covid Cases Management System)."
            +"\nπαρέχει ένα γραφικό περιβάλλον για την καταχώρηση κρουσμάτων κορονοϊου,"
            +"\nκαθώς και για την εξαγωγή διαφόρων συμπερασμάτων όσον αφορά την "
            +"\nπορεία του ιού στην χώρα μας.\n"
            +"Εφόσον είναι η πρώτη φορά που χρησιμοποιείτε την εφαρμογή σε αυτόν το υπολογιστή\n"
            +"πατήστε το κουμπι 'Sign up' για να δημιουργήσετε χρήστη.");
    }//GEN-LAST:event_jButtonHelpActionPerformed

    private void SignUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpButtonActionPerformed
        String query = "Select count(*)  from USERS where username = '"+jTextField1.getText()+"'";
        if(jPasswordField2.getText().equals("") || jPasswordField1.getText().equals("") || jTextField1.getText().equals("") || jTextField2.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Παρακαλώ συμπληρώστε όλα τα πεδία");
        }   //δημιουργεί userr
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
                        JOptionPane.showMessageDialog(null,"Δημιουργεία χρήστη επιτυχείς");
                        CasesSystem a = new CasesSystem();
                        a.setVisible(true);
                        jDialogSignUp.dispose();
                        this.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"'Password' και 'Retype Pass' διαφέρουν");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Το username που επιλέξατε χρησιμοποιείτε είδη ");
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JOptionPane.showMessageDialog(jDialogSignUp,"Παρακαλώ συμπληρώστε τα πεδία\n"
            + "και πατήστε 'Sign Up' για να δημιουργήσετε το χρήστη σας.");
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
    private javax.swing.JButton LOGINButton1;
    private javax.swing.JPasswordField PASSWORDTextField;
    private javax.swing.JButton SignUpButton;
    public static javax.swing.JTextField USERNAMETextField;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonHelp;
    private javax.swing.JButton jButtonSignUp;
    private javax.swing.JDialog jDialogSignUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelPass1;
    private javax.swing.JLabel jLabelUname1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelLoginFrame;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
