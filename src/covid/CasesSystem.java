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

/**
 *
 * @author kosta
 */
public class CasesSystem extends javax.swing.JFrame {

    static Connection conn = null;
    static ResultSet rs = null;
    static PreparedStatement pst = null;
    public static int getValue;
    int count;
    int count1;
    covid.DAO a = new covid.DAO();

    /**
     * Creates new form CasesSystem
     */
  
    public CasesSystem() {
        conn = covid.javaconnect.ConnectDB();
        initComponents();
        Update_table();
        setnumberofcurrentcases();
        setnumberofoverallcases();
        setAverageAge();
        FillcomboCity();
        FillcomboCity0();
        setnumberofoheals();
        setnumberofodeaths();
        Fillcombosearch();
        count = 0;
        count1 = 0;
        
    }
    private static java.sql.Timestamp getCurrentTimeStamp() {
    java.util.Date today = new java.util.Date();// generates and returns current Timestamp
    return new java.sql.Timestamp(today.getTime());}
    
    private void FillcomboCity(){
        try{
            String sql = "Select * from REGIONS";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String City = rs.getString("REGION");
                jComboBox1.addItem(City);
                            }
            rs.close();
            pst.close();
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            }
    }
    
    public static void delete_basedontime(){
        try{
            String qq= "DELETE FROM PROB WHERE (DATE < DATETIME('NOW', '-14 days'))";  

            pst= conn.prepareStatement(qq);
            pst.execute();
            pst.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }

    }
    public static void Fillcombosearch(){
        try{
            String sql = "Select * from OVERALLCASES";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jComboBoxSEARCHID.insertItemAt("", 0);
            while(rs.next()){
                String ID = rs.getString("ID");
                CasesSystem.jComboBoxSEARCHID.addItem(ID);
            }
            rs.close();
            pst.close();
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            }
    }
    
    public static void Update_table(){
        
        try {
        String sql = "Select ID,AMKA,NAME,SURNAME,AGE from CURRENTCASES ";
        pst  = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        pst.close();
        rs.close();
        jLabel6.setText("Κρούσματα κορονοιού(ΤΩΡΑ)");
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            
        }
    }
    public static void setAverageAge(){
        try{
            String sql = "select avg(AGE) from CURRENTCASES";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            int x = rs.getInt("avg(AGE)");
            jLabelAvgAge.setText(String.valueOf(x));
            pst.close();
            rs.close();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            
        }
    }
    public static int generateserialId(){
        
        String sql = "select count(ID)+1 from OVERALLCASES";
        try{
            pst = conn.prepareStatement(sql);
            pst.execute();
            rs = pst.executeQuery();
            if(rs.next()){
                getValue = Integer.parseInt(rs.getString(1)); 
            }
            pst.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        return getValue;
    }
    
    public static void setnumberofcurrentcases(){
        try{
            String sql = "select count(*) from CURRENTCASES";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            int x = rs.getInt("count(*)");
            jLabelcurrcases.setText(String.valueOf(x));
            pst.close();
            rs.close();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            
        }
    }
    
    public static void setnumberofoverallcases(){
        try{
            String sql = "select count(*) from Totalcases";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            int x = rs.getInt("count(*)");
            jLabelOverallcases.setText(String.valueOf(x));
            pst.close();
            rs.close();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            
        }
    }
    public static void setnumberofodeaths(){
        try{
            String sql = "select count(*) from PASSED";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            int x = rs.getInt("count(*)");
            jLabeldeaths.setText(String.valueOf(x));
            pst.close();
            rs.close();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            
        }
    }
    public static void setnumberofoheals(){
        try{
            String sql = "select count(*) from HEAL";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            int x = rs.getInt("count(*)");
            jLabelheals.setText(String.valueOf(x));
            pst.close();
            rs.close();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            
        }
    }
    private void FillcomboCity0(){
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
        jTextField1_3 = new javax.swing.JTextField();
        jTextField1_2 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jTextField1_1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButtonAdd = new javax.swing.JButton();
        jButtonRemove = new javax.swing.JButton();
        jButtonFinish = new javax.swing.JButton();
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
        jTextFieldNAME = new javax.swing.JTextField();
        jTextFieldSURNAME = new javax.swing.JTextField();
        jTextFieldAGE = new javax.swing.JTextField();
        jTextFieldADDRES = new javax.swing.JTextField();
        jComboBoxCITY = new javax.swing.JComboBox<>();
        jTextFieldCITY = new javax.swing.JTextField();
        jTextFieldAMKA = new javax.swing.JTextField();
        jTextFieldPHONENUMBER = new javax.swing.JTextField();
        jPanelData = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabeldeaths = new javax.swing.JLabel();
        jLabelcurrcases = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelOverallcases = new javax.swing.JLabel();
        jLabelheals = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelAvgAge = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPaneltable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        jDialogProbableCases.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        jDialogProbableCases.setBounds(new java.awt.Rectangle(80, 80, 950, 600));
        jDialogProbableCases.setModal(true);
        jDialogProbableCases.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                jDialogProbableCasesWindowClosing(evt);
            }
        });

        jPanelDialog.setBackground(new java.awt.Color(204, 255, 255));
        jPanelDialog.setForeground(new java.awt.Color(204, 255, 255));

        jPanelProbableInfo.setBorder(javax.swing.BorderFactory.createTitledBorder("Probable Case Info"));

        jLabel32.setText("Surname:");

        jLabel33.setText("Age:");

        jLabel34.setText(" Name");

        jLabel7.setText("Addres:");

        jLabel8.setText("City:");

        jLabel9.setText("Amka:");

        jLabel10.setText("Phone Number:");

        jLabel11.setText("Gender:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

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
                            .addComponent(jTextField1_2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1_1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProbableInfoLayout.createSequentialGroup()
                        .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel33))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1_3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProbableInfoLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProbableInfoLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProbableInfoLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41))
        );
        jPanelProbableInfoLayout.setVerticalGroup(
            jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProbableInfoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jTextField1_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jTextField1_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jTextField1_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanelProbableInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        jButtonAdd.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jButtonAdd.setIcon(new javax.swing.ImageIcon("C:\\Users\\kosta\\Downloads\\Button-Add-icon.png")); // NOI18N
        jButtonAdd.setText("Add");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonRemove.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jButtonRemove.setIcon(new javax.swing.ImageIcon("C:\\Users\\kosta\\Downloads\\Button-Delete-icon.png")); // NOI18N
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
                            .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(jButtonAdd)
                        .addGap(33, 33, 33)
                        .addComponent(jButtonRemove)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogProbableCasesLayout = new javax.swing.GroupLayout(jDialogProbableCases.getContentPane());
        jDialogProbableCases.getContentPane().setLayout(jDialogProbableCasesLayout);
        jDialogProbableCasesLayout.setHorizontalGroup(
            jDialogProbableCasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogProbableCasesLayout.createSequentialGroup()
                .addComponent(jPanelDialog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialogProbableCasesLayout.setVerticalGroup(
            jDialogProbableCasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelDialog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

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
                        .addComponent(jLabelSURNAME, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

        jPanelData.setBackground(new java.awt.Color(255, 255, 255));
        jPanelData.setBorder(javax.swing.BorderFactory.createTitledBorder("DATA"));

        jLabel1.setText("Number of current cases:");

        jLabeldeaths.setText("jLabel8");

        jLabelcurrcases.setText("jLabel6");

        jLabel5.setText("Average age of cases:");

        jLabelOverallcases.setText("jLabel7");

        jLabelheals.setText("jLabel9");

        jLabel3.setText("Number of deaths:");

        jLabelAvgAge.setText("jLabel10");

        jLabel2.setText("Number of overall cases:");

        jLabel4.setText("Number of heals:");

        javax.swing.GroupLayout jPanelDataLayout = new javax.swing.GroupLayout(jPanelData);
        jPanelData.setLayout(jPanelDataLayout);
        jPanelDataLayout.setHorizontalGroup(
            jPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDataLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelheals)
                    .addComponent(jLabelAvgAge)
                    .addComponent(jLabeldeaths)
                    .addComponent(jLabelOverallcases)
                    .addComponent(jLabelcurrcases)))
        );
        jPanelDataLayout.setVerticalGroup(
            jPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelcurrcases))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelOverallcases))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabeldeaths))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabelheals))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabelAvgAge))
                .addContainerGap())
        );

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");

        jButton3.setText("jButton3");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N

        javax.swing.GroupLayout jPaneltableLayout = new javax.swing.GroupLayout(jPaneltable);
        jPaneltable.setLayout(jPaneltableLayout);
        jPaneltableLayout.setHorizontalGroup(
            jPaneltableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneltableLayout.createSequentialGroup()
                .addGroup(jPaneltableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPaneltableLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPaneltableLayout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPaneltableLayout.setVerticalGroup(
            jPaneltableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneltableLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextFieldNAME)
                                        .addComponent(jTextFieldSURNAME)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jComboBoxCITY, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldCITY, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jTextFieldAGE)
                                        .addComponent(jTextFieldPHONENUMBER)
                                        .addComponent(jTextFieldADDRES))
                                    .addComponent(jTextFieldAMKA, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanelData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPaneltable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPaneltable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanelIDSEARCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jButtonCLEAR)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonDELETE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonSAVE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanelCaseInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(jTextFieldPHONENUMBER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jPanelData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton3)
                            .addComponent(jButton2))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jMenu1.setText("Data tabels");

        jMenuItem1.setText("Κρούσματα τώρα");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Πιθανά κρούσματα");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Αποθανόντες");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Θεραπευμένοι");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("Συνολικά κρούσματα");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSAVEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSAVEActionPerformed
        try{
            String quer = "SELECT COUNT(*) FROM OVERALLCASES WHERE AMKA = '"+jTextFieldAMKA.getText()+"'";
            pst = conn.prepareStatement(quer);
            rs = pst.executeQuery();
            rs.next();
            int x = rs.getInt("COUNT(*)");
            pst.close();
            rs.close();
            try{
            if(jTextFieldPHONENUMBER.getText().equals("") || jTextFieldNAME.getText().equals("") || jTextFieldSURNAME.getText().equals("")
                || jTextFieldNAME.getText().equals("") || jTextFieldADDRES.getText().equals("") || jTextFieldAMKA.getText().equals("") || jComboBoxCITY.getSelectedItem().toString().equals("")){
                JOptionPane.showMessageDialog(null,"Παρακαλώ συμπληρώστε όλα τα πεδία");}
            else if (Integer.parseInt(jTextFieldAGE.getText()) <= 0 || Integer.parseInt(jTextFieldAGE.getText()) >120){
                JOptionPane.showMessageDialog(null,"Παρακαλώ εισάγετε έγκυρη ηλικία");}
            else if (String.valueOf(jTextFieldPHONENUMBER.getText()).length() != 10){
                JOptionPane.showMessageDialog(null,"Παρακαλώ εισάγετε έγκυρο αριθμό τηλεφώνου");}
            else if (String.valueOf(jTextFieldAMKA.getText()).length() != 12 ){
                JOptionPane.showMessageDialog(null,"Παρακαλώ εισάγετε έγκυρο αριθμό AMKA");}
            else if (x == 1){
                JOptionPane.showMessageDialog(null,"Παρακαλώ ο αριθμός AMKA που εισάγατε αντιστοιχεί σε άλλο ασθενή παρακαλώ εισάγετε έγκυρο AMKA");}
            else{
                jDialogProbableCases.setVisible(true);
            }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Παρακαλώ συμπληρώστε όλα τα πεδία");
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
    }//GEN-LAST:event_jButtonSAVEActionPerformed

    private void jButtonDELETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDELETEActionPerformed
        death_or_restore obj = new death_or_restore();
        obj.setVisible(true);
    }//GEN-LAST:event_jButtonDELETEActionPerformed

    private void jButtonCLEARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCLEARActionPerformed
      jTextFieldNAME.setText("");
        jTextFieldSURNAME.setText("");
        jTextFieldAGE.setText("");
        jTextFieldADDRES.setText("");
        jTextFieldPHONENUMBER.setText("");
        jTextFieldCITY.setText("");
        jTextFieldAMKA.setText("");
    }//GEN-LAST:event_jButtonCLEARActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try{
        jTable1.setModel(DbUtils.resultSetToTableModel(a.getCurrentcases()));
        jLabel6.setText("Κρούσματα κορονοιού(ΤΩΡΑ)"); 
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try {
        String sql = "Select RelatedID,NAME,SURNAME,AMKA from PROB ";
        pst  = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        pst.close();
        rs.close();
        jLabel6.setText("Πιθανά κρούσματα");
      
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            
        }

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
        String sql = "Select ID,NAME,SURNAME,AGE from PASSED ";
        pst  = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        pst.close();
        rs.close();
        jLabel6.setText("Αποθανόντες");
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
        String sql = "Select ID,NAME,SURNAME,AGE from HEAL ";
        pst  = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        pst.close();
        rs.close();
        jLabel6.setText("Θεραπευμένοι");
        
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
         try {
        String sql = "Select ID,AMKA,NAME,SURNAME,AGE from OVERALLCASES ";
        pst  = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        pst.close();
        rs.close();
        jLabel6.setText("Συνολική καταγραφή κρουσμάτων");
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jTextFieldAGEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAGEKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c)) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE){
        evt.consume();    
        }
    }//GEN-LAST:event_jTextFieldAGEKeyTyped

    private void jTextFieldAMKAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAMKAKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c)) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE){
        evt.consume();    
        }
    }//GEN-LAST:event_jTextFieldAMKAKeyTyped

    private void jTextFieldPHONENUMBERKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPHONENUMBERKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c)) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE){
        evt.consume();    
        }   
    }//GEN-LAST:event_jTextFieldPHONENUMBERKeyTyped

    private void jButtonSEARCHIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSEARCHIDActionPerformed
        
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Δημιουρργία barchart 
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        JOptionPane c = new JOptionPane();
        try{
        String quer = "SELECT COUNT(*) FROM OVERALLCASES WHERE AMKA = '"+jTextField3.getText()+"'";
        pst = conn.prepareStatement(quer);
        rs = pst.executeQuery();
        rs.next();
        int z = rs.getInt("COUNT(*)");
        pst.close();
        rs.close();
        DefaultTableModel tb1model = (DefaultTableModel)jTable2.getModel();
        int j = jTable2.getRowCount();
        if(jTextField4.getText().equals("") || jTextField1_1.getText().equals("") || jTextField1_2.getText().equals("")
             || jTextField1_3.getText().equals("") || jTextField1.getText().equals("") || jTextField3.getText().equals("")){    
            JDialog x = new JDialog(jDialogProbableCases);
        x.setVisible(true);}
        else if (Integer.parseInt(jTextField1_3.getText()) <= 0 || Integer.parseInt(jTextField1_3.getText()) >120){
               
            JOptionPane.showMessageDialog(jDialogProbableCases, "Invalid Age ");}
        else if (String.valueOf(jTextField4.getText()).length() != 10){
                JOptionPane.showMessageDialog(jDialogProbableCases,"Invalid Phone number");}
        else if (String.valueOf(jTextField3.getText()).length() != 12 ){   
            JOptionPane.showMessageDialog(jDialogProbableCases,  "Invalid AMKA");
                }
        else if (z == 1){
                JOptionPane.showMessageDialog(jDialogProbableCases,"Παρακαλώ ο αριθμός AMKA που εισάγατε αντιστοιχεί σε άλλο ασθενή παρακαλώ εισάγετε έγκυρο AMKA");}
        else{
            try{
            String x = jTable2.getModel().getValueAt(j-1, 5).toString();
             Object[] row  = new Object[8];
             if (!x.equals(jTextField3.getText()) ){
                row[0] = jTextField1_1.getText();
                row[1] = jTextField1_2.getText();
                row[2] = jTextField1_3.getText();
                row[3] = jTextField1.getText();
                row[4] = jComboBox1.getSelectedItem().toString();
                row[5] = jTextField3.getText();
                row[6] = jTextField4.getText();
                row[7] = jComboBox2.getSelectedItem().toString();
                tb1model.addRow(row);
            }
            else{
               JOptionPane.showMessageDialog(jDialogProbableCases,"Παρακαλώ εισάγετε έγκυρο αριμό ΑΜΚΑ"); 
            }

            }catch(Exception e){
                Object[] row  = new Object[8];
                row[0] = jTextField1_1.getText();
                row[1] = jTextField1_2.getText();
                row[2] = jTextField1_3.getText();
                row[3] = jTextField1.getText();
                row[4] = jComboBox1.getSelectedItem().toString();
                row[5] = jTextField3.getText();
                row[6] = jTextField4.getText();
                row[7] = jComboBox2.getSelectedItem().toString();
                tb1model.addRow(row);
            }

        }
        }catch(Exception e){
           JOptionPane.showMessageDialog(this,e); 
        }
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveActionPerformed
        try{
        DefaultTableModel tb1model = (DefaultTableModel) jTable2.getModel();
        if (jTable2.getSelectedRowCount() == 1){
            tb1model.removeRow(jTable2.getSelectedRowCount());
        }else{
            if(jTable2.getRowCount() == 0 ){
                JOptionPane.showMessageDialog(this,"Ο πίνακας είναι άδειος");
            }else{
                JOptionPane.showMessageDialog(this,"Παρακαλώ επιλέξτε μια γραμμή\n του πίνακα");
            }
        }
    }catch(Exception e){
        DefaultTableModel tb1model = (DefaultTableModel) jTable2.getModel();    
            tb1model.removeRow(jTable2.getSelectedRowCount()-1);
       }
    }//GEN-LAST:event_jButtonRemoveActionPerformed

    private void jButtonFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinishActionPerformed
        String Serial = "CS"+new SimpleDateFormat("ddMMyyy").format(new Date())+CasesSystem.generateserialId();
        if(jTable2.getRowCount() ==0){
            String ObjButtons[] = {"Yes","No"};
            int PromptResult = JOptionPane.showOptionDialog(null,"Δεν έχετε καταχωρίσει πιθανά κρούσματα\nγια το κρούσμα με στοιχεία\n"
                    + "\nName:  "+jTextFieldNAME.getText()+
            "\nSurname: "+jTextFieldSURNAME.getText()+"\nAMKA: "+jTextFieldAMKA.getText()+"\nAge: "+jTextFieldAGE.getText()+"\nAddres: "+jTextFieldAGE.getText()+
            "\nRegion: "+jTextFieldAGE.getText()+"\nId: "+Serial+"\n\nEίστε σίγουροι οτι θέλετε να αποθηκευθεί το κρούσμα? ","ProbableCases",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
            if(PromptResult==JOptionPane.YES_OPTION){
                try{
                    String sqqql = "Insert into OVERALLCASES (ID,NAME,SURNAME,AGE,ADDRES,REGION,AMKA,PHONE,DATE) values(?,?,?,?,?,?,?,?,?)";
                    pst = conn.prepareStatement(sqqql);
                    pst.setString(1,Serial);
                    pst.setString(2,CasesSystem.jTextFieldNAME.getText());
                    pst.setString(3,CasesSystem.jTextFieldSURNAME.getText());
                    pst.setString(4,CasesSystem.jTextFieldAGE.getText());
                    pst.setString(5,CasesSystem.jTextFieldADDRES.getText());
                    pst.setString(6,CasesSystem.jComboBoxCITY.getSelectedItem().toString());
                    pst.setString(7,CasesSystem.jTextFieldAMKA.getText());
                    pst.setString(8,CasesSystem.jTextFieldPHONENUMBER.getText());
                    pst.setString(9,new SimpleDateFormat("dd-MM-yyy").format(new Date()));
                    pst.execute(); 
                    String sql = "Insert into CURRENTCASES (ID,NAME,SURNAME, AGE, ADDRES, REGION, AMKA, PHONE,DATE) values (?,?,?,?,?,?,?,?,?)";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1,Serial);
                    pst.setString(2,CasesSystem.jTextFieldNAME.getText());
                    pst.setString(3,CasesSystem.jTextFieldSURNAME.getText());
                    pst.setString(4,CasesSystem.jTextFieldAGE.getText());
                    pst.setString(5,CasesSystem.jTextFieldADDRES.getText());
                    pst.setString(6,CasesSystem.jComboBoxCITY.getSelectedItem().toString());
                    pst.setString(7,CasesSystem.jTextFieldAMKA.getText());           
                    pst.setString(8,CasesSystem.jTextFieldPHONENUMBER.getText());
                    pst.setString(9,new SimpleDateFormat("dd-MM-yyy").format(new Date()));
                    pst.execute();
                    CasesSystem.jComboBoxSEARCHID.addItem(Serial);
                }catch(Exception e){
                    
                }
                jTextField1_1.setText("");
                jTextField1_2.setText("");
                jTextField1_3.setText("");
                jTextField1.setText("");
                jTextField3.setText("");
                jTextField4.setText("");
                jDialogProbableCases.setVisible(false);
            }else{
                jTextField1_1.setText("");
                jTextField1_2.setText("");
                jTextField1_3.setText("");
                jTextField1.setText("");
                jTextField3.setText("");
                jTextField4.setText("");
                jDialogProbableCases.setVisible(false);
            }
            
        }else{
        //covid.Case c = new Case(Serial,CasesSystem.jTextFieldNAME.getText(),CasesSystem.jTextFieldSURNAME.getText(),Integer.parseInt(CasesSystem.jTextFieldAGE.getText()),CasesSystem.jTextFieldADDRES.getText(),CasesSystem.jComboBoxCITY.getSelectedItem().toString(),CasesSystem.jTextFieldAMKA.getText(),CasesSystem.jTextFieldPHONENUMBER.getText(),Serial,new SimpleDateFormat("dd-MM-yyy").format(new Date()));  
        try{
           String sqqql = "Insert into OVERALLCASES (ID,NAME,SURNAME,AGE,ADDRES,REGION,AMKA,PHONE,DATE) values(?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sqqql);
            pst.setString(1,Serial);
            pst.setString(2,CasesSystem.jTextFieldNAME.getText());
            pst.setString(3,CasesSystem.jTextFieldSURNAME.getText());
            pst.setString(4,CasesSystem.jTextFieldAGE.getText());
            pst.setString(5,CasesSystem.jTextFieldADDRES.getText());
            pst.setString(6,CasesSystem.jComboBoxCITY.getSelectedItem().toString());
            pst.setString(7,CasesSystem.jTextFieldAMKA.getText());
            pst.setString(8,CasesSystem.jTextFieldPHONENUMBER.getText());
            pst.setString(9,new SimpleDateFormat("dd-MM-yyy").format(new Date()));
            pst.execute(); 
            String sql = "Insert into CURRENTCASES (ID,NAME,SURNAME, AGE, ADDRES, REGION, AMKA, PHONE,DATE) values (?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1,Serial);
            pst.setString(2,CasesSystem.jTextFieldNAME.getText());
            pst.setString(3,CasesSystem.jTextFieldSURNAME.getText());
            pst.setString(4,CasesSystem.jTextFieldAGE.getText());
            pst.setString(5,CasesSystem.jTextFieldADDRES.getText());
            pst.setString(6,CasesSystem.jComboBoxCITY.getSelectedItem().toString());
            pst.setString(7,CasesSystem.jTextFieldAMKA.getText());           
            pst.setString(8,CasesSystem.jTextFieldPHONENUMBER.getText());
            pst.setString(9,new SimpleDateFormat("dd-MM-yyy").format(new Date()));
            pst.execute();
            CasesSystem.jComboBoxSEARCHID.addItem(Serial);
            int rows=jTable2.getRowCount();
            
            String sqql = "Insert into PROB (RelatedID,NAME,SURNAME,AGE,ADDRES,REGION, AMKA, PHONE, GENRE,DATE) values (?,?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sqql);
            for(int roww = 0; roww< rows; roww++){
                // getting from Jtable1 
                String RelatedID = Serial;
                String Name = (String)jTable2.getValueAt(roww, 0);
                String Surname = (String)jTable2.getValueAt(roww, 1);
                String Age = (String)jTable2.getValueAt(roww, 2);
                String Addres = (String)jTable2.getValueAt(roww, 3);
                String Region = (String)jTable2.getValueAt(roww, 4);
                String Amka = (String)jTable2.getValueAt(roww, 5);
                String Phone = (String)jTable2.getValueAt(roww, 6);
                String Genre = (String)jTable2.getValueAt(roww, 7);
                // setting to database
                pst.setString(1,RelatedID);
                pst.setString(2,Name);
                pst.setString(3,Surname);
                pst.setString(4,Age);
                pst.setString(5,Addres);
                pst.setString(6,Region);
                pst.setString(7,Amka);
                pst.setString(8,Phone);
                pst.setString(9,Genre);
                pst.setTimestamp(10,getCurrentTimeStamp());
                pst.addBatch();
            }
            pst.executeBatch();
            pst.close();
            CasesSystem.Update_table();
            CasesSystem.setnumberofcurrentcases();
            CasesSystem.setnumberofoverallcases();
            CasesSystem.setAverageAge();
            JOptionPane.showMessageDialog(null,"data saved");
            count1 = count1++;
            
            jTextField1_1.setText("");
            jTextField1_2.setText("");
            jTextField1_3.setText("");
            jTextField1.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
            jDialogProbableCases.setVisible(false);
            
            }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        finally{
            try {
                pst.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        }
        }
    }//GEN-LAST:event_jButtonFinishActionPerformed

    private void jDialogProbableCasesWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jDialogProbableCasesWindowClosing
        String Serial = "CS"+new SimpleDateFormat("ddMMyyy").format(new Date())+CasesSystem.generateserialId();
        if (count1 == 0){
            String ObjButtons[] = {"Yes","No"};
            int PromptResult = JOptionPane.showOptionDialog(null,"Δεν έχετε καταχωρίσει πιθανά κρούσματα\nγια το κρούσμα με στοιχεία\n"
                    + "\nName:  "+jTextFieldNAME.getText()+
            "\nSurname: "+jTextFieldSURNAME.getText()+"\nAMKA: "+jTextFieldAMKA.getText()+"\nAge: "+jTextFieldAGE.getText()+"\nAddres: "+jTextFieldAGE.getText()+
            "\nRegion: "+jTextFieldAGE.getText()+"\nId: "+Serial+"\n\nEίστε σίγουροι οτι θέλετε να αποθηκευθεί το κρούσμα? ","ProbableCases",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
            if(PromptResult==JOptionPane.YES_OPTION)
            {
            jTextField1_1.setText("");
            jTextField1_2.setText("");
            jTextField1_3.setText("");
            jTextField1.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            try{
                String sqqql = "Insert into OVERALLCASES (ID,NAME,SURNAME,AGE,ADDRES,REGION,AMKA,PHONE,DATE) values(?,?,?,?,?,?,?,?,?)";
                pst = conn.prepareStatement(sqqql);
                pst.setString(1,Serial);
                pst.setString(2,CasesSystem.jTextFieldNAME.getText());
                pst.setString(3,CasesSystem.jTextFieldSURNAME.getText());
                pst.setString(4,CasesSystem.jTextFieldAGE.getText());
                pst.setString(5,CasesSystem.jTextFieldADDRES.getText());
                pst.setString(6,CasesSystem.jComboBoxCITY.getSelectedItem().toString());
                pst.setString(7,CasesSystem.jTextFieldAMKA.getText());
                pst.setString(8,CasesSystem.jTextFieldPHONENUMBER.getText());
                pst.setString(9,new SimpleDateFormat("dd-MM-yyy").format(new Date()));
                pst.execute(); 
                String sql = "Insert into CURRENTCASES (ID,NAME,SURNAME, AGE, ADDRES, REGION, AMKA, PHONE,DATE) values (?,?,?,?,?,?,?,?,?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1,Serial);
                pst.setString(2,CasesSystem.jTextFieldNAME.getText());
                pst.setString(3,CasesSystem.jTextFieldSURNAME.getText());
                pst.setString(4,CasesSystem.jTextFieldAGE.getText());
                pst.setString(5,CasesSystem.jTextFieldADDRES.getText());
                pst.setString(6,CasesSystem.jComboBoxCITY.getSelectedItem().toString());
                pst.setString(7,CasesSystem.jTextFieldAMKA.getText());           
                pst.setString(8,CasesSystem.jTextFieldPHONENUMBER.getText());
                pst.setString(9,new SimpleDateFormat("dd-MM-yyy").format(new Date()));
                pst.execute();
                CasesSystem.jComboBoxSEARCHID.addItem(Serial);
                jDialogProbableCases.setVisible(false);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
           
        }else{
                
        jTextField1_1.setText("");
        jTextField1_2.setText("");
        jTextField1_3.setText("");
        jTextField1.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jDialogProbableCases.setVisible(false);
        }
            
        }else{
        jTextField1_1.setText("");
        jTextField1_2.setText("");
        jTextField1_3.setText("");
        jTextField1.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jDialogProbableCases.setVisible(false);
        }
        
    }//GEN-LAST:event_jDialogProbableCasesWindowClosing

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonCLEAR;
    private javax.swing.JButton jButtonDELETE;
    private javax.swing.JButton jButtonFinish;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JButton jButtonSAVE;
    private javax.swing.JButton jButtonSEARCHID;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    public static javax.swing.JComboBox<String> jComboBoxCITY;
    public static javax.swing.JComboBox<String> jComboBoxSEARCHID;
    private javax.swing.JDialog jDialogProbableCases;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelADDRES;
    private javax.swing.JLabel jLabelAGE;
    private javax.swing.JLabel jLabelAMKA;
    public static javax.swing.JLabel jLabelAvgAge;
    private javax.swing.JLabel jLabelCITY;
    private javax.swing.JLabel jLabelNAME;
    public static javax.swing.JLabel jLabelOverallcases;
    private javax.swing.JLabel jLabelPHONENUMBER;
    private javax.swing.JLabel jLabelSURNAME;
    public static javax.swing.JLabel jLabelcurrcases;
    public static javax.swing.JLabel jLabeldeaths;
    public static javax.swing.JLabel jLabelheals;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelCaseInfo;
    private javax.swing.JPanel jPanelData;
    private javax.swing.JPanel jPanelDialog;
    private javax.swing.JPanel jPanelIDSEARCH;
    private javax.swing.JPanel jPanelProbableInfo;
    private javax.swing.JPanel jPaneltable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField1_1;
    private javax.swing.JTextField jTextField1_2;
    private javax.swing.JTextField jTextField1_3;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    public static javax.swing.JTextField jTextFieldADDRES;
    public static javax.swing.JTextField jTextFieldAGE;
    public static javax.swing.JTextField jTextFieldAMKA;
    private javax.swing.JTextField jTextFieldCITY;
    public static javax.swing.JTextField jTextFieldNAME;
    public static javax.swing.JTextField jTextFieldPHONENUMBER;
    public static javax.swing.JTextField jTextFieldSURNAME;
    // End of variables declaration//GEN-END:variables
}
