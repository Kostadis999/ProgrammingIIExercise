/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid;


import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.Color;
import java.io.File;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author kosta
 */
public class CasesSystem extends javax.swing.JFrame {
    static String genre;
    static Connection conn = null;
    static ResultSet rs = null;
    static PreparedStatement pst = null;
    public static int getValue;
    covid.DAO a = new covid.DAO();
    static int COUNT1;
  
    public CasesSystem() {
        conn = covid.javaconnect.ConnectDB();
        initComponents();
        DAO.fillJtableCases("CURRENTCASES","ID","Ενεργά κρούσματα","");
        FillcomboCity();
        FillcomboCity0();
        Fillcombosearch();
        COUNT1 = 0;
        
    }
    public static java.sql.Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        // generates and returns current Timestamp
        return new java.sql.Timestamp(today.getTime());
    }
    
    public static void clearprobdialodtextfields(){
            jTextFieldProbName.setText("");
            jTextFieldProbSurname.setText("");
            jTextFieldProbAge.setText("");
            jTextFieldProbAddres.setText("");
            jTextFieldProbAmka.setText("");
            jTextFieldProbPhonNumbr.setText("");
         
    }
    
    private void FillcomboCity(){
        try{
            String sql = "Select * from REGIONS";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String City = rs.getString("REGION");
                jComboBoxProbCity.addItem(City);
                            }
            rs.close();
            pst.close();
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            }
    }
    

    public static void FillDeletecombo(){
        try{
            String sql = "Select * from CURRENTCASES";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String ID = rs.getString("ID");
                jComboBoxDeleteId.addItem(ID);
            }
            rs.close();
            pst.close();
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            }
    }
    
    private static void delete_basedontime(){
        //Διαγράφει τα πιθανά κρούσματα μετά από 14 μέρες από την καταχώρισή τους
        try{
            String qq= "DELETE FROM PROB WHERE (DATE < DATETIME('NOW', '-14 days'))";  

            pst= conn.prepareStatement(qq);
            pst.execute();
            pst.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }

    }
    private static void Fillcombosearch()
    /*γεμιζει τα items του jComboBoxCITY με τις πολεις
        του πινακα REGION της βάσης jComboBoSearchID*/{
        try{
            String sql = "Select * from CURRENTCASES";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jComboBoxSEARCHID.insertItemAt("", 0);
            while(rs.next()){
                String ID = rs.getString("ID");
                jComboBoxSEARCHID.addItem(ID);
            }
            rs.close();
            pst.close();
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            }
    }
    
    public static void Update_table(){
        try {//ανανεώνει τον πίνακα jTable  από τη βάση
        String sql = "Select ID,AMKA,NAME,SURNAME,AGE from CURRENTCASES ";
        pst  = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        jTableCases.setModel(DbUtils.resultSetToTableModel(rs));
        pst.close();
        rs.close();
        jLabel6.setText("Κρούσματα κορονοιού(ΤΩΡΑ)");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            
        }
    }

    private void FillcomboCity0(){
        /*γεμιζει τα items του jComboBoxCITY με τις πολεις
        του πινακα REGION της βάσης*/
        try{
            String sql = "Select * from REGIONS";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jComboBoxCITY.insertItemAt("", 0);
            while(rs.next()){
                String City = rs.getString("REGION");
                jComboBoxCITY.addItem(City);
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

        jDialogProbableCases = new javax.swing.JDialog();
        jPanelDialog = new javax.swing.JPanel();
        jPanelProbableInfo = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jTextFieldProbAge = new javax.swing.JTextField();
        jTextFieldProbSurname = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jTextFieldProbName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldProbAddres = new javax.swing.JTextField();
        jTextFieldProbAmka = new javax.swing.JTextField();
        jTextFieldProbPhonNumbr = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jComboBoxProbCity = new javax.swing.JComboBox<>();
        jComboBoxprobgenre = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProbableCases = new javax.swing.JTable();
        jButtonAddProb = new javax.swing.JButton();
        jButtonRemove = new javax.swing.JButton();
        jButtonFinish = new javax.swing.JButton();
        jDialogDatadisplay = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jPanelData2 = new javax.swing.JPanel();
        casescountlabel = new javax.swing.JLabel();
        MalecasesLabel = new javax.swing.JLabel();
        maxagecaselabel = new javax.swing.JLabel();
        Minagecaselabel = new javax.swing.JLabel();
        AvgageofcasesLabel = new javax.swing.JLabel();
        Femalecaseslabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDialogDeathOrRestore = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jComboBoxDeleteId = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        PassedButton = new javax.swing.JButton();
        HealButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanelIDSEARCH = new javax.swing.JPanel();
        jButtonSEARCHID = new javax.swing.JButton();
        jComboBoxSEARCHID = new javax.swing.JComboBox<>();
        jButtonCLEAR = new javax.swing.JButton();
        jButtonDELETE = new javax.swing.JButton();
        jButtonSAVE = new javax.swing.JButton();
        jPanelCaseInfo = new javax.swing.JPanel();
        jLabelADDRES = new javax.swing.JLabel();
        jLabelSURNAME = new javax.swing.JLabel();
        jLabelAMKA = new javax.swing.JLabel();
        jLabelNAME = new javax.swing.JLabel();
        jLabelCITY = new javax.swing.JLabel();
        jLabelPHONENUMBER = new javax.swing.JLabel();
        jLabelAGE = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldNAME = new javax.swing.JTextField();
        jTextFieldSURNAME = new javax.swing.JTextField();
        jTextFieldAGE = new javax.swing.JTextField();
        jTextFieldADDRES = new javax.swing.JTextField();
        jComboBoxCITY = new javax.swing.JComboBox<>();
        jTextFieldCITY = new javax.swing.JTextField();
        jTextFieldAMKA = new javax.swing.JTextField();
        jTextFieldPHONENUMBER = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPaneltable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCases = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jCheckBoxCurrentCases = new javax.swing.JCheckBox();
        jCheckBoxProbCases = new javax.swing.JCheckBox();
        jCheckBoxTottalCases = new javax.swing.JCheckBox();
        jCheckBoxPassed = new javax.swing.JCheckBox();
        jCheckBoxHealed = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemBarchart = new javax.swing.JMenuItem();
        jMenuItemPieChartGender = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        jDialogProbableCases.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        jDialogProbableCases.setTitle("Καταχώριση πιθανών κρουσμάτων");
        jDialogProbableCases.setBounds(new java.awt.Rectangle(80, 80, 950, 600));
        jDialogProbableCases.setModal(true);
        jDialogProbableCases.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                jDialogProbableCasesWindowClosing(evt);
            }
        });

        jPanelDialog.setBackground(new java.awt.Color(255, 204, 204));
        jPanelDialog.setForeground(new java.awt.Color(204, 255, 255));

        jPanelProbableInfo.setBorder(javax.swing.BorderFactory.createTitledBorder("Probable Case Info"));

        jLabel32.setText("Surname:");

        jLabel33.setText("Age:");

        jLabel34.setText(" Name");

        jLabel7.setText("Address");

        jLabel8.setText("City:");

        jLabel9.setText("Amka:");

        jLabel10.setText("Phone Number:");

        jLabel11.setText("Gender:");

        jComboBoxprobgenre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        javax.swing.GroupLayout jPanelProbableInfoLayout = new javax.swing.GroupLayout(jPanelProbableInfo);
        jPanelProbableInfo.setLayout(jPanelProbableInfoLayout);
        jPanelProbableInfoLayout.setHorizontalGroup(
            jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProbableInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProbableInfoLayout.createSequentialGroup()
                        .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldProbSurname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldProbName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProbableInfoLayout.createSequentialGroup()
                        .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel33))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxProbCity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldProbAddres, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldProbAge, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProbableInfoLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldProbAmka, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProbableInfoLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(jTextFieldProbPhonNumbr, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProbableInfoLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBoxprobgenre, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41))
        );
        jPanelProbableInfoLayout.setVerticalGroup(
            jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProbableInfoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jTextFieldProbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jTextFieldProbSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jTextFieldProbAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldProbAddres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBoxProbCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldProbAmka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldProbPhonNumbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBoxprobgenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jTableProbableCases.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Surname", "Age", "Addres", "City", "AMKA", "Phone Number", "Gender"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableProbableCases);

        jButtonAddProb.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jButtonAddProb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/covid/Button-Add-icon.png"))); // NOI18N
        jButtonAddProb.setText("Add");
        jButtonAddProb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddProbActionPerformed(evt);
            }
        });

        jButtonRemove.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jButtonRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/covid/Button-Delete-icon.png"))); // NOI18N
        jButtonRemove.setText("Remove");
        jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveActionPerformed(evt);
            }
        });

        jButtonFinish.setText("Finish");
        jButtonFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinishActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDialogLayout = new javax.swing.GroupLayout(jPanelDialog);
        jPanelDialog.setLayout(jPanelDialogLayout);
        jPanelDialogLayout.setHorizontalGroup(
            jPanelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelProbableInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDialogLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonAddProb, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDialogLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85))))
        );
        jPanelDialogLayout.setVerticalGroup(
            jPanelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogLayout.createSequentialGroup()
                .addGroup(jPanelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDialogLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelProbableInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelDialogLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonFinish))))
                    .addGroup(jPanelDialogLayout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jButtonAddProb)
                        .addGap(33, 33, 33)
                        .addComponent(jButtonRemove)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogProbableCasesLayout = new javax.swing.GroupLayout(jDialogProbableCases.getContentPane());
        jDialogProbableCases.getContentPane().setLayout(jDialogProbableCasesLayout);
        jDialogProbableCasesLayout.setHorizontalGroup(
            jDialogProbableCasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelDialog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogProbableCasesLayout.setVerticalGroup(
            jDialogProbableCasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelDialog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialogDatadisplay.setBounds(new java.awt.Rectangle(400, 400, 400, 400));
        jDialogDatadisplay.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                jDialogDatadisplayWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        jPanelData2.setBackground(new java.awt.Color(255, 255, 255));
        jPanelData2.setBorder(javax.swing.BorderFactory.createTitledBorder("DATA"));

        casescountlabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        casescountlabel.setText("Number of current cases:");

        MalecasesLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        MalecasesLabel.setText("Average age of cases:");

        maxagecaselabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        maxagecaselabel.setText("Number of deaths:");

        Minagecaselabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        Minagecaselabel.setText("Number of overall cases:");

        AvgageofcasesLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        AvgageofcasesLabel.setText("Number of heals:");

        Femalecaseslabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        Femalecaseslabel.setText("jLabel17");

        javax.swing.GroupLayout jPanelData2Layout = new javax.swing.GroupLayout(jPanelData2);
        jPanelData2.setLayout(jPanelData2Layout);
        jPanelData2Layout.setHorizontalGroup(
            jPanelData2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelData2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelData2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(casescountlabel, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(Minagecaselabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(maxagecaselabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AvgageofcasesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelData2Layout.createSequentialGroup()
                        .addGroup(jPanelData2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(MalecasesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                            .addComponent(Femalecaseslabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        jPanelData2Layout.setVerticalGroup(
            jPanelData2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelData2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(casescountlabel)
                .addGap(18, 18, 18)
                .addComponent(Minagecaselabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(maxagecaselabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(AvgageofcasesLabel)
                .addGap(18, 18, 18)
                .addComponent(MalecasesLabel)
                .addGap(18, 18, 18)
                .addComponent(Femalecaseslabel)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanelData2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jPanelData2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );

        javax.swing.GroupLayout jDialogDatadisplayLayout = new javax.swing.GroupLayout(jDialogDatadisplay.getContentPane());
        jDialogDatadisplay.getContentPane().setLayout(jDialogDatadisplayLayout);
        jDialogDatadisplayLayout.setHorizontalGroup(
            jDialogDatadisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogDatadisplayLayout.setVerticalGroup(
            jDialogDatadisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialogDeathOrRestore.setTitle("Διαγραφή Ενεργού Κρούσματος");
        jDialogDeathOrRestore.setBounds(new java.awt.Rectangle(400, 200, 482, 120));

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));
        jPanel3.setForeground(new java.awt.Color(204, 255, 255));

        jLabel1.setText("Case id:");

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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/covid/info.png"))); // NOI18N
        jButton1.setText("Help");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxDeleteId, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(PassedButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(HealButton)
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxDeleteId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PassedButton)
                    .addComponent(HealButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogDeathOrRestoreLayout = new javax.swing.GroupLayout(jDialogDeathOrRestore.getContentPane());
        jDialogDeathOrRestore.getContentPane().setLayout(jDialogDeathOrRestoreLayout);
        jDialogDeathOrRestoreLayout.setHorizontalGroup(
            jDialogDeathOrRestoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogDeathOrRestoreLayout.setVerticalGroup(
            jDialogDeathOrRestoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jPanelIDSEARCH.setBorder(javax.swing.BorderFactory.createTitledBorder("ID SEARCH"));

        jButtonSEARCHID.setText("Search");
        jButtonSEARCHID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSEARCHIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelIDSEARCHLayout = new javax.swing.GroupLayout(jPanelIDSEARCH);
        jPanelIDSEARCH.setLayout(jPanelIDSEARCHLayout);
        jPanelIDSEARCHLayout.setHorizontalGroup(
            jPanelIDSEARCHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIDSEARCHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxSEARCHID, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonSEARCHID)
                .addContainerGap())
        );
        jPanelIDSEARCHLayout.setVerticalGroup(
            jPanelIDSEARCHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIDSEARCHLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelIDSEARCHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSEARCHID)
                    .addComponent(jComboBoxSEARCHID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonCLEAR.setText("Clear");
        jButtonCLEAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCLEARActionPerformed(evt);
            }
        });

        jButtonDELETE.setText("Delete Case");
        jButtonDELETE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDELETEActionPerformed(evt);
            }
        });

        jButtonSAVE.setText("Save case");
        jButtonSAVE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSAVEActionPerformed(evt);
            }
        });

        jPanelCaseInfo.setBorder(javax.swing.BorderFactory.createTitledBorder("Case Info"));

        jLabelADDRES.setText("Addres:");

        jLabelSURNAME.setText("Surname:");

        jLabelAMKA.setText("AMKA:");

        jLabelNAME.setText("Name:");

        jLabelCITY.setText("City:");

        jLabelPHONENUMBER.setText("Phone number:");

        jLabelAGE.setText("Age:");

        jLabel17.setText("Genre:");

        javax.swing.GroupLayout jPanelCaseInfoLayout = new javax.swing.GroupLayout(jPanelCaseInfo);
        jPanelCaseInfo.setLayout(jPanelCaseInfoLayout);
        jPanelCaseInfoLayout.setHorizontalGroup(
            jPanelCaseInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaseInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCaseInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAGE)
                    .addComponent(jLabelADDRES)
                    .addComponent(jLabelCITY)
                    .addComponent(jLabelAMKA)
                    .addComponent(jLabelPHONENUMBER)
                    .addGroup(jPanelCaseInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabelNAME, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelSURNAME, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelCaseInfoLayout.setVerticalGroup(
            jPanelCaseInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaseInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNAME)
                .addGap(18, 18, 18)
                .addComponent(jLabelSURNAME)
                .addGap(18, 18, 18)
                .addComponent(jLabelAGE)
                .addGap(18, 18, 18)
                .addComponent(jLabelADDRES)
                .addGap(18, 18, 18)
                .addComponent(jLabelCITY)
                .addGap(18, 18, 18)
                .addComponent(jLabelAMKA)
                .addGap(18, 18, 18)
                .addComponent(jLabelPHONENUMBER)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addContainerGap())
        );

        jTextFieldNAME.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jTextFieldSURNAME.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jTextFieldAGE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextFieldAGE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldAGEKeyTyped(evt);
            }
        });

        jTextFieldADDRES.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jTextFieldCITY.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jTextFieldAMKA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextFieldAMKA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldAMKAKeyTyped(evt);
            }
        });

        jTextFieldPHONENUMBER.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextFieldPHONENUMBER.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPHONENUMBERKeyTyped(evt);
            }
        });

        jButton2.setText("EXIT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTableCases.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableCases);

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N

        buttonGroup1.add(jCheckBoxCurrentCases);
        jCheckBoxCurrentCases.setText("Κρούσματα");
        jCheckBoxCurrentCases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxCurrentCasesActionPerformed(evt);
            }
        });

        buttonGroup1.add(jCheckBoxProbCases);
        jCheckBoxProbCases.setText("Πιθανά κρούσματα");
        jCheckBoxProbCases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxProbCasesActionPerformed(evt);
            }
        });

        buttonGroup1.add(jCheckBoxTottalCases);
        jCheckBoxTottalCases.setText("Συνολικά Κρούσματα");
        jCheckBoxTottalCases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTottalCasesActionPerformed(evt);
            }
        });

        buttonGroup1.add(jCheckBoxPassed);
        jCheckBoxPassed.setText("Αποθανόντες");
        jCheckBoxPassed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPassedActionPerformed(evt);
            }
        });

        buttonGroup1.add(jCheckBoxHealed);
        jCheckBoxHealed.setText("Θεραπευμένοι");
        jCheckBoxHealed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHealedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPaneltableLayout = new javax.swing.GroupLayout(jPaneltable);
        jPaneltable.setLayout(jPaneltableLayout);
        jPaneltableLayout.setHorizontalGroup(
            jPaneltableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneltableLayout.createSequentialGroup()
                .addGroup(jPaneltableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPaneltableLayout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPaneltableLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPaneltableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPaneltableLayout.createSequentialGroup()
                                .addComponent(jCheckBoxCurrentCases, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBoxProbCases)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBoxTottalCases, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBoxPassed, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBoxHealed, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPaneltableLayout.setVerticalGroup(
            jPaneltableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneltableLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPaneltableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxTottalCases, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneltableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBoxPassed)
                        .addComponent(jCheckBoxHealed))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneltableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCheckBoxCurrentCases)
                        .addComponent(jCheckBoxProbCases)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(213, 213, 213))
        );

        jCheckBox6.setBackground(new java.awt.Color(255, 204, 204));
        buttonGroup2.add(jCheckBox6);
        jCheckBox6.setText("Male");
        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });

        jCheckBox7.setBackground(new java.awt.Color(255, 204, 204));
        buttonGroup2.add(jCheckBox7);
        jCheckBox7.setText("Female");
        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelIDSEARCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButtonSAVE, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonCLEAR, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonDELETE, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanelCaseInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldNAME)
                                    .addComponent(jTextFieldSURNAME)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jComboBoxCITY, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldCITY, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextFieldAGE)
                                    .addComponent(jTextFieldPHONENUMBER)
                                    .addComponent(jTextFieldADDRES)
                                    .addComponent(jTextFieldAMKA)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jCheckBox6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jCheckBox7)
                                        .addGap(36, 36, 36)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                        .addComponent(jPaneltable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelIDSEARCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(jTextFieldNAME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSURNAME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldAGE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldADDRES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxCITY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCITY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldAMKA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldPHONENUMBER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox6)
                            .addComponent(jCheckBox7)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jButtonCLEAR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonDELETE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonSAVE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanelCaseInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPaneltable, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(jButton2)
                .addGap(33, 33, 33))
        );

        jMenu2.setText("Reports");

        jMenuItem6.setText("Κρούσματα(τώρα)");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("Συνολικά Κρούσματα");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem8.setText("Αποθανόντες ");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem9.setText("Θεραπευμένοι ");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Diagrams");

        jMenuItemBarchart.setText("Κρούσματα ανά ηλικία");
        jMenuItemBarchart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBarchartActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemBarchart);

        jMenuItemPieChartGender.setText("Ενεργά κρούσματα ανα φύλο");
        jMenuItemPieChartGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPieChartGenderActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemPieChartGender);

        jMenuItem1.setText("Θάνατοι ανά ηλικία ");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

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

        setBounds(0, 0, 1154, 623);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSAVEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSAVEActionPerformed
       
        try{
            String quer1 = "SELECT COUNT(*) FROM PROB WHERE AMKA = '"+jTextFieldAMKA.getText()+"' AND PHONE = '"+jTextFieldPHONENUMBER.getText()+"' AND AGE = '"+jTextFieldAGE.getText()+"' ";
            String quer = "SELECT COUNT(*) FROM OVERALLCASES WHERE AMKA = '"+jTextFieldAMKA.getText()+"'";
            String quer2 = "SELECT COUNT(*) FROM OVERALLCASES WHERE AMKA = '"+jTextFieldAMKA.getText()+"'AND PHONE = '"+jTextFieldPHONENUMBER.getText()+"'AND AGE = '"
                    +jTextFieldAGE.getText()+"'AND REGION = '"+jComboBoxCITY.getSelectedItem().toString()+"'";
            pst = conn.prepareStatement(quer2);
            rs = pst.executeQuery();
            rs.next();
            int COUNT2 = rs.getInt("COUNT(*)");
            pst = conn.prepareStatement(quer);
            rs = pst.executeQuery();
            rs.next();
            int COUNT = rs.getInt("COUNT(*)");
            /* η μεταβλητη COUNT περιέχει τον αριθμό των γραμμών του πίνακα των συνολικών κρουσμάτων 
            της βάσης που το ΑΜΚΑ τους ταυτίζεται με το AMKA που εχει εισάγει ο χρήστης στο πεδίο jTextFieldAMKA 
            αρα αν count = 1 το ID που ειχε εισαγει ο χρήστης δεν ειανι εγκυρο*/
            pst = conn.prepareStatement(quer1);
            rs = pst.executeQuery();
            rs.next();
            COUNT1 = rs.getInt("COUNT(*)");
            //αντιστοιχα η COUNT1 περιεχει τον αριθμο των στοιχείων των πιθανων κρουσμάτων που έχουν ΑΜΚΑ και τηλεφωνο 
            //ομοια με αυτα που εισήγαγε ο χρήστης. Χρησιμοποιείτε δηλαδή για την επιβεβαίωση πιθανων κρουσμάτων
            //ελεγχος εγκυρότητας δεδομένων 
            if(COUNT2==1){
                JOptionPane.showMessageDialog(null,"Τα στοιχεία που εισάγατε αντιστοιχούν σε άλλο\nσε είδη καταχωρημένο ασθενή");
            }else if(jTextFieldPHONENUMBER.getText().equals("") || jTextFieldNAME.getText().equals("") || jTextFieldSURNAME.getText().equals("")
                || jTextFieldNAME.getText().equals("") || jTextFieldADDRES.getText().equals("") || jTextFieldAMKA.getText().equals("") 
                || jComboBoxCITY.getSelectedItem().toString().equals("")){
                JOptionPane.showMessageDialog(null,"Παρακαλώ συμπληρώστε όλα τα πεδία");}
            else if (Integer.parseInt(jTextFieldAGE.getText()) <= 0 || Integer.parseInt(jTextFieldAGE.getText()) >120){
                JOptionPane.showMessageDialog(null,"Παρακαλώ εισάγετε έγκυρη ηλικία");}
            else if (String.valueOf(jTextFieldPHONENUMBER.getText()).length() != 10){
                JOptionPane.showMessageDialog(null,"Παρακαλώ εισάγετε έγκυρο αριθμό τηλεφώνου");
                }
            else if (String.valueOf(jTextFieldAMKA.getText()).length() != 12 ){
                JOptionPane.showMessageDialog(null,"Παρακαλώ εισάγετε έγκυρο αριθμό AMKA");}
            else if ( COUNT == 1){
                JOptionPane.showMessageDialog(null,"O αριθμός AMKA που εισάγατε αντιστοιχεί \nσε άλλο ασθενή παρακαλώ εισάγετε έγκυρο AMKA");}
            else if (COUNT1 == 1) {
                /*για να εκτελεστεί ο κώδικας που βρίσκεται σε αυτό το 'else if' σημαίνει ότι ο χρήστης έχει καταχωρίσει AMKA και τηλέφωνο 
                που αντιστοιχεί σε κάποιο πιθανό κρούσμα, το προγραμμα θα ενημερώσει για την επιβεβαίωση του πιθανού κρούσματος*/
                JOptionPane.showMessageDialog(null,"Επιβεβαίωση πιθανού κρούσματος !!\n\n" 
                        + "Το κρούσμα που επιθημείτε να εισάγετε\n"
                        +"βρισκεται καταχωριμένο στα πιθανά κρούσματα");
                       jDialogProbableCases.setVisible(true);
            }else{
                jDialogProbableCases.setVisible(true);
                /*για να εκτελεστεί ο κώδικας που βρίσκεται σε αυτό το 'else if' σημαίνει ότι ο χρήστης έχει καταχωρίσει 
                έγκυρα δεδομένα στα πεδία και δεν αντιστοιχουν τα δεδομένα σε πιθανό κρούσμα*/
            }
        }catch(SQLException e ){
            JOptionPane.showMessageDialog(null,e);
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null,"παρακαλώ συμπληρώστε όλα τα πεδία");
            /*Αν ο χρήστης επιλέξει το κενο στοιχείο στο jComboBoxCITY η εντολή 
            'jComboBoxCITY.getSelectedItem().toString().equals("")' δημιουργεί NullPointerException
            */
        }   
        
    }//GEN-LAST:event_jButtonSAVEActionPerformed

    private void jButtonDELETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDELETEActionPerformed
        /*γεμίζει τα items του jComboboxid το οποίο βρίσκετε στο διάλογο jDialogDeathOrRestore
        με τα ID των CURRENTCASES */
        try{
            String sql = "Select * from CURRENTCASES";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jComboBoxDeleteId.insertItemAt("", 0);
            while(rs.next()){
                String ID = rs.getString("ID");
                jComboBoxDeleteId.addItem(ID);
            }
            rs.close();
            pst.close();
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            }
        jDialogDeathOrRestore.setVisible(true);
    }//GEN-LAST:event_jButtonDELETEActionPerformed

    private void jButtonCLEARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCLEARActionPerformed
        jTextFieldNAME.setText("");//καθαρίζει τα πεδία
        jTextFieldSURNAME.setText("");
        jTextFieldAGE.setText("");
        jTextFieldADDRES.setText("");
        jTextFieldPHONENUMBER.setText("");
        jTextFieldCITY.setText("");
        jTextFieldAMKA.setText("");
    }//GEN-LAST:event_jButtonCLEARActionPerformed

    private void jTextFieldAGEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAGEKeyTyped
        char c = evt.getKeyChar();//εμποδίζει την εισαγωγή χαρακτήρων στο πεδίο jTextFieldAGE 
        if(!(Character.isDigit(c)) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE){
        evt.consume();    
        }
    }//GEN-LAST:event_jTextFieldAGEKeyTyped

    private void jTextFieldAMKAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAMKAKeyTyped
        char c = evt.getKeyChar();//εμποδίζει την εισαγωγή χαρακτήρων στο πεδίο jTextFieldAMKA
        if(!(Character.isDigit(c)) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE){
        evt.consume();    
        }
    }//GEN-LAST:event_jTextFieldAMKAKeyTyped

    private void jTextFieldPHONENUMBERKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPHONENUMBERKeyTyped
        char c = evt.getKeyChar();//εμποδίζει την εισαγωγή χαρακτήρων στο πεδίο jTextFieldPHONENUMBER
        if(!(Character.isDigit(c)) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE){
        evt.consume();    
        }   
    }//GEN-LAST:event_jTextFieldPHONENUMBERKeyTyped

    private void jButtonSEARCHIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSEARCHIDActionPerformed
    /* η μέθοδος εμφανιζει τα στοιχεία του κρούσματος του οποίου το ID  έχει επιλεχτεί 
       στο jComboBoxSEARCHID τα  */   
    try{
        int x =1;    
        try{
            if("".equals(jComboBoxSEARCHID.getSelectedItem().toString())){
                x = 0;
                JOptionPane.showMessageDialog(null,"Παρακαλώ επιλέξτε ID ");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        if (x==1){
            String ssql = "select * from OVERALLCASES where ID =?";
            pst = conn.prepareStatement(ssql);
            pst.setString(1,jComboBoxSEARCHID.getSelectedItem().toString());
            rs=pst.executeQuery();
            if(rs.next()){
                String add1 = rs.getString("NAME");
                jTextFieldNAME.setText(add1);
                String add2 = rs.getString("SURNAME");
                jTextFieldSURNAME.setText(add2);
                String add3 = rs.getString("AGE");
                jTextFieldAGE.setText(add3);
                String add4 = rs.getString("ADDRES");
                jTextFieldADDRES.setText(add4);
                String add5 = rs.getString("REGION");
                jTextFieldCITY.setText(add5);
                String add6 = rs.getString("PHONE");
                jTextFieldPHONENUMBER.setText(add6);
                String add7 = rs.getString("AMKA");
                jTextFieldAMKA.setText(add7);
            }
            pst.close();
            rs.close();
        } 
    }catch(HeadlessException | SQLException e){
        JOptionPane.showMessageDialog(null,e);
    }
    }//GEN-LAST:event_jButtonSEARCHIDActionPerformed

    private void jButtonAddProbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddProbActionPerformed
        /*η μέθοδος αφου ελέγξει τα στοιχεία που έχει καταχωρίσει ο χρήστης στα Textfields του διαλόγου 
        jDialogProbableCases προσθετει μία γραμμη με αυτά στον πίνακα jTableProbableCases*/
        try{
        String quer = "SELECT COUNT(*) FROM OVERALLCASES WHERE AMKA = '"+jTextFieldProbAmka.getText()+"'";
        pst = conn.prepareStatement(quer);
        rs = pst.executeQuery();
        rs.next();
        int z = rs.getInt("COUNT(*)");
        pst.close();
        rs.close();
        DefaultTableModel tb1model = (DefaultTableModel)jTableProbableCases.getModel();
        int j = jTableProbableCases.getRowCount();
        if(jTextFieldProbPhonNumbr.getText().equals("") || jTextFieldProbName.getText().equals("") || jTextFieldProbSurname.getText().equals("")
             || jTextFieldProbAge.getText().equals("") || jTextFieldProbAddres.getText().equals("") || jTextFieldProbAmka.getText().equals("")){    
            JOptionPane.showMessageDialog(jDialogProbableCases, "Παρακαλώ συμπληρώστε όλα τα πεδία ");
        }
        else if (Integer.parseInt(jTextFieldProbAge.getText()) <= 0 || Integer.parseInt(jTextFieldProbAge.getText()) >120){
               
            JOptionPane.showMessageDialog(jDialogProbableCases, "Invalid Age ");}
        else if (String.valueOf(jTextFieldProbPhonNumbr.getText()).length() != 10){
                JOptionPane.showMessageDialog(jDialogProbableCases,"Invalid Phone number");}
        else if (String.valueOf(jTextFieldProbAmka.getText()).length() != 12 ){   
            JOptionPane.showMessageDialog(jDialogProbableCases,  "Invalid AMKA");
                }
        else if (z == 1){
                JOptionPane.showMessageDialog(jDialogProbableCases,"Παρακαλώ ο αριθμός AMKA που εισάγατε αντιστοιχεί σε άλλο ασθενή παρακαλώ εισάγετε έγκυρο AMKA");}
        else{
            try{
            String x = jTableProbableCases.getModel().getValueAt(j-1, 5).toString();
             Object[] row  = new Object[8];
             if (!x.equals(jTextFieldProbAmka.getText()) ){
                row[0] = jTextFieldProbName.getText();
                row[1] = jTextFieldProbSurname.getText();
                row[2] = jTextFieldProbAge.getText();
                row[3] = jTextFieldProbAddres.getText();
                row[4] = jComboBoxProbCity.getSelectedItem().toString();
                row[5] = jTextFieldProbAmka.getText();
                row[6] = jTextFieldProbPhonNumbr.getText();
                row[7] = jComboBoxprobgenre.getSelectedItem().toString();
                tb1model.addRow(row);
            }
            else{
               JOptionPane.showMessageDialog(jDialogProbableCases,"Παρακαλώ εισάγετε έγκυρο αριμό ΑΜΚΑ"); 
            }

            }catch(Exception e){
                Object[] row  = new Object[8];
                row[0] = jTextFieldProbName.getText();
                row[1] = jTextFieldProbSurname.getText();
                row[2] = jTextFieldProbAge.getText();
                row[3] = jTextFieldProbAddres.getText();
                row[4] = jComboBoxProbCity.getSelectedItem().toString();
                row[5] = jTextFieldProbAmka.getText();
                row[6] = jTextFieldProbPhonNumbr.getText();
                row[7] = jComboBoxprobgenre.getSelectedItem().toString();
                tb1model.addRow(row);
            }

        }
        }catch(Exception e){
           JOptionPane.showMessageDialog(this,e); 
        }
    }//GEN-LAST:event_jButtonAddProbActionPerformed

    private void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveActionPerformed
        try{//Η μέθοδος διαγράφει μία επιλεγμένη γραμμή από τον πίνακα jTableProbableCases
        DefaultTableModel tb1model = (DefaultTableModel) jTableProbableCases.getModel();
        if (jTableProbableCases.getSelectedRowCount() == 1){
            tb1model.removeRow(jTableProbableCases.getSelectedRowCount());
        }else{
            if(jTableProbableCases.getRowCount() == 0 ){
                JOptionPane.showMessageDialog(jDialogProbableCases,"Ο πίνακας είναι άδειος");
            }else{
                JOptionPane.showMessageDialog(jDialogProbableCases,"Παρακαλώ επιλέξτε μια γραμμή\n του πίνακα");
            }
        }
    }catch(ArrayIndexOutOfBoundsException e){
        DefaultTableModel tb1model = (DefaultTableModel) jTableProbableCases.getModel();    
            tb1model.removeRow(jTableProbableCases.getSelectedRowCount()-1);
            /*σε περίπτωση που διαγραφτεί το τελευταίο στοιχείο του πίνακα
            η εντολή 'tb1model.removeRow(jTableProbableCases.getSelectedRowCount());'
            δημιουργεί ArrayIndexOutOfBoundsException:*/ 
        }
    }//GEN-LAST:event_jButtonRemoveActionPerformed

    private void jButtonFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinishActionPerformed
        /*η μεθοδος αποθηκεύει τα πιθανα κρούσματα και το κρούσμα που κατασώρισε ο χρήστης. 
        Σε περιπτωση που ο χρήστης δεν έχει καταχορίσει πιθανά κρούσματα εμφανίζεται ανάλογο μήνυμα*/
        String Serial = "CS"+new SimpleDateFormat("ddMMyyy").format(new Date())+DAO.generateserialId();
        if(jTableProbableCases.getRowCount() ==0){//αν δεν εχουν καταχωριθεί πιθανα κρούσματα
            String ObjButtons[] = {"Yes","No"};//ρωταει με OptionDialog αν θελει να αποθηκευτει το κρούσμα
            int PromptResult = JOptionPane.showOptionDialog(null,"Δεν έχετε καταχωρίσει πιθανά κρούσματα\nγια το κρούσμα με στοιχεία\n"
                    + "\nName:  "+jTextFieldNAME.getText()+
            "\nSurname: "+jTextFieldSURNAME.getText()+"\nAMKA: "+jTextFieldAMKA.getText()+"\nAge: "+jTextFieldAGE.getText()+"\nAddres: "+jTextFieldAGE.getText()+
            "\nRegion: "+jTextFieldAGE.getText()+"\nId: "+Serial+"\n\nEίστε σίγουροι οτι θέλετε να αποθηκευθεί το κρούσμα? ","ProbableCases",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
            if(PromptResult==JOptionPane.YES_OPTION){//αν επιλεχτει το ναι
                DAO.SaveCases();                 //αποθηκευσε
                clearprobdialodtextfields(); //καθαρισε τα παιδια
                jDialogProbableCases.setVisible(false);//κλείσε το διάλογο
            }else{                           //αλλιώς
                clearprobdialodtextfields(); //καθάρισε τα πεδία
                jDialogProbableCases.setVisible(false);//κλείσε το διάλογο
            }
            
        }else{
        
            DAO.SaveCases();
            DAO.SaveProbCases();
        }
    }//GEN-LAST:event_jButtonFinishActionPerformed

    private void jDialogProbableCasesWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jDialogProbableCasesWindowClosing
        String Serial = "CS"+new SimpleDateFormat("ddMMyyy").format(new Date())+DAO.generateserialId();
        if (jTableProbableCases.getRowCount()== 0){
            String ObjButtons[] = {"Yes","No"};
            int PromptResult = JOptionPane.showOptionDialog(null,"Δεν έχετε καταχωρίσει πιθανά κρούσματα\nγια το κρούσμα με στοιχεία\n"
                    + "\nName:  "+jTextFieldNAME.getText()+
            "\nSurname: "+jTextFieldSURNAME.getText()+"\nAMKA: "+jTextFieldAMKA.getText()+"\nAge: "+jTextFieldAGE.getText()+"\nAddres: "+jTextFieldADDRES.getText()+
            "\nRegion: "+jTextFieldCITY.getText()+"\nId: "+Serial+"\n\nEίστε σίγουροι οτι θέλετε να αποθηκευθεί το κρούσμα? ","ProbableCases",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
            if(PromptResult==JOptionPane.YES_OPTION)
            {
                DAO.SaveCases();
                Update_table();
                jDialogProbableCases.setVisible(false);
                

            }else{
                clearprobdialodtextfields();
                jDialogProbableCases.setVisible(false);
            }
        }else{
            String ObjButtons[] = {"Yes","No"};
            int PromptResult = JOptionPane.showOptionDialog(null,"Θέλετε να αποθηκευτεί \nτο κρούσμα με στοιχεία\n"
            + "\nName:  "+jTextFieldNAME.getText()+
                "\nSurname: "+jTextFieldSURNAME.getText()+"\nAMKA: "+jTextFieldAMKA.getText()+"\nAge: "+jTextFieldAGE.getText()+"\nAddres: "+jTextFieldADDRES.getText()+
                "\nRegion: "+jTextFieldCITY.getText()+"\nId: "+Serial+"\nκαι τα πιθανά κορύσματα που σχετίζονατι με αυτο; ","Exiting",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
            if(PromptResult==JOptionPane.YES_OPTION){
                DAO.SaveCases();
                DAO.SaveProbCases();
                JOptionPane.showMessageDialog(null,"data saved");
                clearprobdialodtextfields();
                DefaultTableModel model = (DefaultTableModel) jTableProbableCases.getModel();
                model.setRowCount(0);
                jDialogProbableCases.setVisible(false);
            }else{
                jDialogProbableCases.setVisible(false);
            }
        clearprobdialodtextfields();
        jDialogProbableCases.setVisible(false);
        }
        
    }//GEN-LAST:event_jDialogProbableCasesWindowClosing

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        jLabel4.setText("Κρούσματα τώρα");
        try{
            String sql = "select avg(AGE),min(AGE),max(AGE),count(*) from CURRENTCASES";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            int avg = rs.getInt("avg(AGE)");
            int min = rs.getInt("min(AGE)");
            int max = rs.getInt("max(AGE)");
            int G = rs.getInt("count(*)");
            rs.close();
            String ss = "select count(*) from CURRENTCASES where GENRE = 'Male'";
            String XX = "select count(*) from CURRENTCASES where GENRE = 'Female'";
            pst = conn.prepareStatement(ss);
            rs = pst.executeQuery();
            rs.next();
            int V = rs.getInt("count(*)");
            rs.close();
            pst = conn.prepareStatement(XX);
            rs = pst.executeQuery();
            rs.next();
            int b = rs.getInt("count(*)");
            rs.close();
            casescountlabel.setText("Current Count of cases: '"+G+"' ");
            rs.close();
            Minagecaselabel.setText("Current minimum age recorded: '"+min+"'");
            rs.close();
            maxagecaselabel.setText("Current Maximun age recorded: '"+max+"'");
            rs.close();
            AvgageofcasesLabel.setText("Current AVG age of cases:'"+avg+"'");
            rs.close();
            MalecasesLabel.setText("Current Count of male cases:'"+V+"'");
            rs.close();
            Femalecaseslabel.setText("Current Count of female cases: '"+b+"'");
            rs.close();
            jDialogDatadisplay.setVisible(true);
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            
        }finally{
           try{
             pst.close();
            rs.close();  
           }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
    
           }
       }
        
        
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        //η μεθοδος εμφανίζει 
        jLabel4.setText("Συνολική καταγραφή");
        try{
            String sql = "select avg(AGE),min(AGE),max(AGE),count(*) from OVERALLCASES";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            int avg = rs.getInt("avg(AGE)");
            int min = rs.getInt("min(AGE)");
            int max = rs.getInt("max(AGE)");
            int G = rs.getInt("count(*)");
            rs.close();
            String ss = "select count(*) from OVERALLCASES where GENRE = 'Male'";
            String XX = "select count(*) from OVERALLCASES where GENRE = 'Female'";
            pst = conn.prepareStatement(ss);
            rs = pst.executeQuery();
            rs.next();
            int V = rs.getInt("count(*)");
            rs.close();
            pst = conn.prepareStatement(XX);
            rs = pst.executeQuery();
            rs.next();
            int b = rs.getInt("count(*)");
            rs.close();
            casescountlabel.setText("Overall Count of cases: '"+G+"' ");
            rs.close();
            Minagecaselabel.setText("minimum Overall age recorded: '"+min+"'");
            rs.close();
            maxagecaselabel.setText("Maximun Overall age recorded: '"+max+"'");
            rs.close();
            AvgageofcasesLabel.setText("AVG age of  cases overall:'"+avg+"'");
            rs.close();
            MalecasesLabel.setText("Count of male cases Overall:'"+V+"'   ");
            rs.close();
            Femalecaseslabel.setText("Count of female cases Overall: '"+b+"'");
            rs.close();
            jDialogDatadisplay.setVisible(true);
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            
        }finally{
           try{
             pst.close();
            rs.close();  
           }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
    
           }
       }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jDialogDatadisplayWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jDialogDatadisplayWindowClosing
        try{
        rs.close();
        pst.close();
        }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            
        }
    }//GEN-LAST:event_jDialogDatadisplayWindowClosing

    private void PassedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PassedButtonActionPerformed
        DAO.deletecASE("PASSED");
    }//GEN-LAST:event_PassedButtonActionPerformed

    private void HealButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HealButtonActionPerformed
        DAO.deletecASE("HEAL");
    }//GEN-LAST:event_HealButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        jLabel4.setText("Αποθανόντες");
        try{
            String sql = "select avg(AGE),min(AGE),max(AGE),count(*) from PASSED";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            int avg = rs.getInt("avg(AGE)");
            int min = rs.getInt("min(AGE)");
            int max = rs.getInt("max(AGE)");
            int G = rs.getInt("count(*)");
            rs.close();
            String ss = "select count(*) from PASSED where GENRE = 'Male'";
            String XX = "select count(*) from PASSED where GENRE = 'Female'";
            pst = conn.prepareStatement(ss);
            rs = pst.executeQuery();
            rs.next();
            int V = rs.getInt("count(*)");
            rs.close();
            pst = conn.prepareStatement(XX);
            rs = pst.executeQuery();
            rs.next();
            int b = rs.getInt("count(*)");
            rs.close();
            casescountlabel.setText("Αριθμός θανάτων : "+G+" ");
            rs.close();
            Minagecaselabel.setText("Ελάχιστη ηλικία θανάτου: "+min+"");
            rs.close();
            maxagecaselabel.setText("Μέγιστη ηλικία θανάτου: "+max+"");
            rs.close();
            AvgageofcasesLabel.setText("Μέσος όρος ηλικίας θανάτων:"+avg+"");
            rs.close();
            MalecasesLabel.setText("θάνατοι αντρών:"+V+"   ");
            rs.close();
            Femalecaseslabel.setText("θάνατοι γυναικών : "+b+"");
            rs.close();
            jDialogDatadisplay.setVisible(true);
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            
        }finally{
           try{
             pst.close();
            rs.close();  
           }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
    
           }
       }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        jLabel4.setText("Θεραπευμαίνοι");
        try{
            String sql = "select avg(AGE),min(AGE),max(AGE),count(*) from HEAL";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            int avg = rs.getInt("avg(AGE)");
            int min = rs.getInt("min(AGE)");
            int max = rs.getInt("max(AGE)");
            int G = rs.getInt("count(*)");
            rs.close();
            String ss = "select count(*) from HEAL where GENRE = 'Male'";
            String XX = "select count(*) from HEAL where GENRE = 'Female'";
            pst = conn.prepareStatement(ss);
            rs = pst.executeQuery();
            rs.next();
            int V = rs.getInt("count(*)");
            rs.close();
            pst = conn.prepareStatement(XX);
            rs = pst.executeQuery();
            rs.next();
            int b = rs.getInt("count(*)");
            rs.close();
            casescountlabel.setText("Αριθμός ιάσεων : "+G+" ");
            rs.close();
            Minagecaselabel.setText("Ελάχιστη ηλικία ίασης: "+min+"");
            rs.close();
            maxagecaselabel.setText("Μέγιστη ηλικία ίασης: "+max+"");
            rs.close();
            AvgageofcasesLabel.setText("Μέσος όρος ηλικίας ιάσεων:"+avg+"");
            rs.close();
            MalecasesLabel.setText("Ιάσεις αντρών:"+V+"   ");
            rs.close();
            Femalecaseslabel.setText("Ιάσεις γυναικών : "+b+"");
            rs.close();
            jDialogDatadisplay.setVisible(true);
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            
        }finally{
           try{
             pst.close();
            rs.close();  
           }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
    
           }
       }        
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jCheckBoxCurrentCasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxCurrentCasesActionPerformed
        DAO.fillJtableCases("CURRENTCASES","ID","Ενεργά κρούσματα","");
    }//GEN-LAST:event_jCheckBoxCurrentCasesActionPerformed

    private void jCheckBoxProbCasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxProbCasesActionPerformed
        DAO.fillJtableCases("PROB","RelatedID","Πιθανά κρούσματα","");
    }//GEN-LAST:event_jCheckBoxProbCasesActionPerformed

    private void jCheckBoxTottalCasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTottalCasesActionPerformed
        DAO.fillJtableCases("OVERALLCASES","ID","Συνολικά κρούσματα","");
    }//GEN-LAST:event_jCheckBoxTottalCasesActionPerformed

    private void jCheckBoxPassedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPassedActionPerformed
        DAO.fillJtableCases("PASSED","ID","Αποθανόντες ","");
    }//GEN-LAST:event_jCheckBoxPassedActionPerformed

    private void jCheckBoxHealedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHealedActionPerformed
        DAO.fillJtableCases("HEAL","ID","Θεραπευμαίνοι","");
    }//GEN-LAST:event_jCheckBoxHealedActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        genre ="Male";
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        genre ="Female";
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void jMenuItemBarchartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBarchartActionPerformed

        try{
            String d1 = "0 - 20";
            String d2 = "21 - 40";
            String d3 = "41 - 60";
            String d4 = "41 - 80";
            String d5 = "81 - 100";
            String d6 = "101 - 120";        
            DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
            String q = "select count(AGE) from CURRENTCASES where AGE <= 20";
            pst = conn.prepareStatement(q);
            pst.execute();
            rs = pst.executeQuery();
            rs.next();
            int c1 = rs.getInt("count(AGE)");
            rs.close();
            String q1 = "select count(AGE) from CURRENTCASES where AGE <= 40 and AGE > 20";
            pst = conn.prepareStatement(q1);
            pst.execute();
            rs = pst.executeQuery();
            rs.next();
            int c2 = rs.getInt("count(AGE)");
            rs.close();
            String q2 = "select count(AGE) from CURRENTCASES where AGE <= 60 and AGE > 40";
            pst = conn.prepareStatement(q2);
            pst.execute();
            rs = pst.executeQuery();
            rs.next();
            int c3 = rs.getInt("count(AGE)");
            rs.close();
            String q3 = "select count(AGE) from CURRENTCASES where AGE <= 80 and AGE > 60";
            pst = conn.prepareStatement(q3);
            pst.execute();
            rs = pst.executeQuery();
            rs.next();
            int c4 = rs.getInt("count(AGE)");
            rs.close();
            String q4 = "select count(AGE) from CURRENTCASES where AGE <= 100 and AGE > 80";
            pst = conn.prepareStatement(q4);
            pst.execute();
            rs = pst.executeQuery();
            rs.next();
            int c5 = rs.getInt("count(AGE)");
            rs.close();
            String q5 = "select count(AGE) from CURRENTCASES where AGE <= 120 and AGE > 100";
            pst = conn.prepareStatement(q5);
            pst.execute();
            rs = pst.executeQuery();
            rs.next();
            int c6 = rs.getInt("count(AGE)");
            rs.close();
            dataset.setValue(c1,"Values",d1);
            rs.close();
            dataset.setValue(c2,"Values",d2);
            rs.close();
            dataset.setValue(c3,"Values",d3);
            rs.close();
            dataset.setValue(c4,"Values",d4);
            rs.close();
            dataset.setValue(c5,"Values",d5);
            rs.close();
            dataset.setValue(c6,"Values",d6);
            rs.close();
            JFreeChart chart = ChartFactory.createBarChart3D("Parameter Values","Parameters","Values",dataset,PlotOrientation.VERTICAL,false,true,false);
            chart.setBackgroundPaint(Color.BLUE);
            chart.getTitle().setPaint(Color.red);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLUE);
            ChartFrame frame = new ChartFrame("bar chart",chart);
            frame.setVisible(true);
            frame.setSize(650,350);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            }
        finally{
            try{
                rs.close();
                pst.close();
              
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }//GEN-LAST:event_jMenuItemBarchartActionPerformed

    private void jMenuItemPieChartGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPieChartGenderActionPerformed
        
        try{
            String q = "select count(*) from CURRENTCASES WHERE GENRE = 'Male'";
            String m = "select count(*) from CURRENTCASES WHERE GENRE = 'Female'";
            pst  = conn.prepareStatement(m);
            rs = pst.executeQuery();
            rs.next();
            int v = rs.getInt("count(*)");
            pst  = conn.prepareStatement(q);
            rs = pst.executeQuery();
            rs.next();
            int z = rs.getInt("count(*)");
            DefaultPieDataset pieDataset = new DefaultPieDataset();
            pieDataset.setValue("Άντρες",v);
            pieDataset.setValue("Γυναίκες",z);
            JFreeChart chart = ChartFactory.createPieChart3D("",pieDataset,true,true,true);
            PiePlot3D p = (PiePlot3D)chart.getPlot();
            ChartFrame frame = new ChartFrame("Pie Chart: Ενεργά κρούσματα ανα φύλο",chart);
            frame.setVisible(true);
            frame.setSize(450,500);
            
            String ObjButtons[] = {"Yes","No"};//ρωταει με OptionDialog αν θελει να αποθηκευτει το κρούσμα
            int PromptResult = JOptionPane.showOptionDialog(null,"θέλετε να αποθηκεύσετε τον πίνακα","ProbableCases",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,ObjButtons,ObjButtons[1]);
            if(PromptResult==JOptionPane.YES_OPTION){
                try{
                    final ChartRenderingInfo Info = new ChartRenderingInfo(new StandardEntityCollection());
                    String x = new SimpleDateFormat("yyMMddHHmmssZ").format(new Date());
                    final File file1 = new File("Chart"+x+".png");
                    ChartUtilities.saveChartAsPNG(file1,chart,600,400,Info);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,"α;");
                }    
            }
                      
        }catch(SQLException e){
            
        }
    }//GEN-LAST:event_jMenuItemPieChartGenderActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        JOptionPane.showMessageDialog(null,"Επιλέξτε το ID του κρούσματος\nπου επιθυμείτε να διαγράψετε από τα Ενεργά Κρούσματα\nκαι επιλέξτε αν το κρούσμα ανάρρωσε ή απεβίωσε");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try{
            String d1 = "0 - 20";
            String d2 = "21 - 40";
            String d3 = "41 - 60";
            String d4 = "41 - 80";
            String d5 = "81 - 100";
            String d6 = "101 - 120";        
            DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
            String q = "select count(AGE) from PASSED where AGE <= 20";
            pst = conn.prepareStatement(q);
            pst.execute();
            rs = pst.executeQuery();
            rs.next();
            int c1 = rs.getInt("count(AGE)");
            rs.close();
            String q1 = "select count(AGE) from PASSED where AGE <= 40 and AGE > 20";
            pst = conn.prepareStatement(q1);
            pst.execute();
            rs = pst.executeQuery();
            rs.next();
            int c2 = rs.getInt("count(AGE)");
            rs.close();
            String q2 = "select count(AGE) from PASSED where AGE <= 60 and AGE > 40";
            pst = conn.prepareStatement(q2);
            pst.execute();
            rs = pst.executeQuery();
            rs.next();
            int c3 = rs.getInt("count(AGE)");
            rs.close();
            String q3 = "select count(AGE) from PASSED where AGE <= 80 and AGE > 60";
            pst = conn.prepareStatement(q3);
            pst.execute();
            rs = pst.executeQuery();
            rs.next();
            int c4 = rs.getInt("count(AGE)");
            rs.close();
            String q4 = "select count(AGE) from PASSED where AGE <= 100 and AGE > 80";
            pst = conn.prepareStatement(q4);
            pst.execute();
            rs = pst.executeQuery();
            rs.next();
            int c5 = rs.getInt("count(AGE)");
            rs.close();
            String q5 = "select count(AGE) from PASSED where AGE <= 120 and AGE > 100";
            pst = conn.prepareStatement(q5);
            pst.execute();
            rs = pst.executeQuery();
            rs.next();
            int c6 = rs.getInt("count(AGE)");
            rs.close();
            dataset.setValue(c1,"Values",d1);
            rs.close();
            dataset.setValue(c2,"Values",d2);
            rs.close();
            dataset.setValue(c3,"Values",d3);
            rs.close();
            dataset.setValue(c4,"Values",d4);
            rs.close();
            dataset.setValue(c5,"Values",d5);
            rs.close();
            dataset.setValue(c6,"Values",d6);
            rs.close();
            JFreeChart chart = ChartFactory.createBarChart3D("Θάνατοι ανά ηλικιακό γκρουπ","θάνατοι","ηλικιακά γκρουπ",dataset,PlotOrientation.VERTICAL,false,true,false);
            chart.setBackgroundPaint(Color.BLUE);
            chart.getTitle().setPaint(Color.red);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.YELLOW);
            ChartFrame frame = new ChartFrame("Θάνατοι ανά ηλικιακό γκρουπ",chart);
            frame.setVisible(true);
            frame.setSize(650,350);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            }
        finally{
            try{
                rs.close();
                pst.close();
              
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CasesSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable    () {
            public void run() {
                new CasesSystem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AvgageofcasesLabel;
    private javax.swing.JLabel Femalecaseslabel;
    private javax.swing.JButton HealButton;
    private javax.swing.JLabel MalecasesLabel;
    private javax.swing.JLabel Minagecaselabel;
    private javax.swing.JButton PassedButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel casescountlabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAddProb;
    private javax.swing.JButton jButtonCLEAR;
    private javax.swing.JButton jButtonDELETE;
    private javax.swing.JButton jButtonFinish;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JButton jButtonSAVE;
    private javax.swing.JButton jButtonSEARCHID;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    public static javax.swing.JCheckBox jCheckBoxCurrentCases;
    public static javax.swing.JCheckBox jCheckBoxHealed;
    public static javax.swing.JCheckBox jCheckBoxPassed;
    public static javax.swing.JCheckBox jCheckBoxProbCases;
    public static javax.swing.JCheckBox jCheckBoxTottalCases;
    public static javax.swing.JComboBox<String> jComboBoxCITY;
    public static javax.swing.JComboBox<String> jComboBoxDeleteId;
    private javax.swing.JComboBox<String> jComboBoxProbCity;
    public static javax.swing.JComboBox<String> jComboBoxSEARCHID;
    private javax.swing.JComboBox<String> jComboBoxprobgenre;
    private javax.swing.JDialog jDialogDatadisplay;
    private javax.swing.JDialog jDialogDeathOrRestore;
    public static javax.swing.JDialog jDialogProbableCases;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelADDRES;
    private javax.swing.JLabel jLabelAGE;
    private javax.swing.JLabel jLabelAMKA;
    private javax.swing.JLabel jLabelCITY;
    private javax.swing.JLabel jLabelNAME;
    private javax.swing.JLabel jLabelPHONENUMBER;
    private javax.swing.JLabel jLabelSURNAME;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItemBarchart;
    private javax.swing.JMenuItem jMenuItemPieChartGender;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelCaseInfo;
    private javax.swing.JPanel jPanelData2;
    private javax.swing.JPanel jPanelDialog;
    private javax.swing.JPanel jPanelIDSEARCH;
    private javax.swing.JPanel jPanelProbableInfo;
    public static javax.swing.JPanel jPaneltable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTableCases;
    public static javax.swing.JTable jTableProbableCases;
    public static javax.swing.JTextField jTextFieldADDRES;
    public static javax.swing.JTextField jTextFieldAGE;
    public static javax.swing.JTextField jTextFieldAMKA;
    private javax.swing.JTextField jTextFieldCITY;
    public static javax.swing.JTextField jTextFieldNAME;
    public static javax.swing.JTextField jTextFieldPHONENUMBER;
    private static javax.swing.JTextField jTextFieldProbAddres;
    private static javax.swing.JTextField jTextFieldProbAge;
    private static javax.swing.JTextField jTextFieldProbAmka;
    private static javax.swing.JTextField jTextFieldProbName;
    private static javax.swing.JTextField jTextFieldProbPhonNumbr;
    private static javax.swing.JTextField jTextFieldProbSurname;
    public static javax.swing.JTextField jTextFieldSURNAME;
    private javax.swing.JLabel maxagecaselabel;
    // End of variables declaration//GEN-END:variables
}
