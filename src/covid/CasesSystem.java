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
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author kosta
 */
public class CasesSystem extends javax.swing.JFrame {

    static Connection conn = null;
    static ResultSet rs = null;
    static PreparedStatement pst = null;
    public static int getValue;

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
        setnumberofoheals();
        setnumberofodeaths();
        Fillcombosearch();
        delete_basedontime();
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
         
            
        }catch(Exception e){
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
    private void FillcomboCity(){
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonSEARCHID = new javax.swing.JButton();
        jComboBoxSEARCHID = new javax.swing.JComboBox<>();
        jButtonCLEAR = new javax.swing.JButton();
        jButtonDELETE = new javax.swing.JButton();
        jButtonSAVE = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
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
        jPanel4 = new javax.swing.JPanel();
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
        jPanel5 = new javax.swing.JPanel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("ID SEARCH"));

        jButtonSEARCHID.setText("Search");
        jButtonSEARCHID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSEARCHIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxSEARCHID, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonSEARCHID)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Case Info"));

        jLabelADDRES.setText("Addres:");

        jLabelSURNAME.setText("Surname:");

        jLabelAMKA.setText("AMKA:");

        jLabelNAME.setText("Name:");

        jLabelCITY.setText("City:");

        jLabelPHONENUMBER.setText("Phone number:");

        jLabelAGE.setText("Age:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAGE)
                    .addComponent(jLabelADDRES)
                    .addComponent(jLabelCITY)
                    .addComponent(jLabelAMKA)
                    .addComponent(jLabelPHONENUMBER)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabelNAME, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelSURNAME, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
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

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("DATA"));

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelheals)
                    .addComponent(jLabelAvgAge)
                    .addComponent(jLabeldeaths)
                    .addComponent(jLabelOverallcases)
                    .addComponent(jLabelcurrcases)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelcurrcases))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelOverallcases))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabeldeaths))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabelheals))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabelAvgAge))
                .addContainerGap())
        );

        jButton1.setText("jButton1");

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
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
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButtonSAVE, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonCLEAR, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonDELETE, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jButtonCLEAR)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonDELETE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonSAVE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(31, 31, 31)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addContainerGap(63, Short.MAX_VALUE))
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
                ProbableCases prob = new ProbableCases();
                prob.setVisible(true);
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
      try {
        String sql = "Select ID,AMKA,NAME,SURNAME,AGE from CURRENTCASES ";
        pst  = conn.prepareStatement(sql);
        rs = pst.executeQuery(); 
        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        pst.close();
        rs.close();
        jLabel6.setText("Κρούσματα κορονοιού που νοσούν τώρα");
        
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try {
        String sql = "Select RelatedID,NAME,SURNAME from PROB ";
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
            java.util.logging.Logger.getLogger(CasesSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CasesSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CasesSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CasesSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CasesSystem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonCLEAR;
    private javax.swing.JButton jButtonDELETE;
    private javax.swing.JButton jButtonSAVE;
    private javax.swing.JButton jButtonSEARCHID;
    public static javax.swing.JComboBox<String> jComboBoxCITY;
    public static javax.swing.JComboBox<String> jComboBoxSEARCHID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTextField jTextFieldADDRES;
    public static javax.swing.JTextField jTextFieldAGE;
    public static javax.swing.JTextField jTextFieldAMKA;
    private javax.swing.JTextField jTextFieldCITY;
    public static javax.swing.JTextField jTextFieldNAME;
    public static javax.swing.JTextField jTextFieldPHONENUMBER;
    public static javax.swing.JTextField jTextFieldSURNAME;
    // End of variables declaration//GEN-END:variables
}
