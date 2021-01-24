/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid;


import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.Date;
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
 * @author stefm
 */
public class DAO  {
    static int getValue;
    static int COUNT1;
    

    public DAO() {
        
        getValue = 0;
        COUNT1 = 0;
        
    }
    public static Connection ConnectDB(){
        try{  //επιστρέφει το connection με τη βάση
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Covidcases.sqlite");
            return conn;
        }catch (ClassNotFoundException | SQLException e){
          JOptionPane.showMessageDialog(null,e);
          return null;
        }
    }
    
    public static void fillJtableCases(String DbTable,String IdorRelid, String label,String orderby){
        try {//γεμίζει τον πίνακα jTableCases με τα διάφορα είδη κρουσμάτων κρούσματα
        String sql = "Select "+IdorRelid+",NAME,SURNAME,AGE,ADDRES,REGION,AMKA,PHONE,DATE from "+DbTable+" "+orderby+"";
        CasesSystem.pst  = CasesSystem.conn.prepareStatement(sql);
        CasesSystem.rs = CasesSystem.pst.executeQuery();
        CasesSystem.jTableCases.setModel(DbUtils.resultSetToTableModel(CasesSystem.rs));
        CasesSystem.pst.close();
        CasesSystem.rs.close();
        CasesSystem.jPaneltable.setBorder(javax.swing.BorderFactory.createTitledBorder(null, label, javax
                .swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION
                ,new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(255, 255, 255)));
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
            
        }
    }
    public static void deletecASE(String Dbtable){
        /*η μεθοδος ελεγχει αν υπαρχει κρούσμα που αντιστοιχεί στο ID που εχει επιλέξει ο χρήστης ως item στο 
        jComboBox3, αν υπάρχει το κρούσμα διαγράφεται απο τα CURRENTCASES και εισάγετε στους θανάτους
        η στις Ιάσεις ανάλογα με τη τιμή της μεταβλητής Dbtable αλλιως εμφανίζεται ανάλογο μήνυμα*/
        try{
            String sql = "select count(*) from CURRENTCASES where ID =  '"+CasesSystem.jComboBoxDeleteId.getSelectedItem().toString()+"' ";
            CasesSystem.pst = CasesSystem.conn.prepareStatement(sql);
            CasesSystem.rs = CasesSystem.pst.executeQuery();
            CasesSystem.rs.next();
            int count = CasesSystem.rs.getInt(1);
            CasesSystem.rs.close();
            if(CasesSystem.jComboBoxDeleteId.getSelectedItem().toString().equals("") ){
                JOptionPane.showMessageDialog(null, "Πληκτρολογίστε το ID του ασθενή που επιθυμείς να διαγράψεις");
            } 
            else if (count == 0) {
                JOptionPane.showMessageDialog(null, "Το ID που πληκτρολογίσατε δεν αντιστοιχεί σε κάποιο ασθενή");
                
            }
            else {
                String quer = "insert into "+Dbtable+" select * from CURRENTCASES where ID    = '"+CasesSystem.jComboBoxDeleteId.getSelectedItem().toString()+"' ";
                CasesSystem.pst = CasesSystem.conn.prepareStatement(quer);
                CasesSystem.pst.execute();
                String query= "delete from CURRENTCASES where ID = '" +CasesSystem.jComboBoxDeleteId.getSelectedItem().toString()+ "' ";
                CasesSystem.pst = CasesSystem.conn.prepareStatement(query);
                CasesSystem.pst.execute();
                JOptionPane.showMessageDialog(null, "Data Deleted");
                CasesSystem.pst.close();	
                if(CasesSystem.jCheckBoxCurrentCases.isSelected()){
                    fillJtableCases("CURRENTCASES","ID","Ενεργά κρούσματα","");
                }else if(CasesSystem.jCheckBoxPassed.isSelected()){
                    fillJtableCases("PASSED","ID","Αποθανόντες ","");
                }else if(CasesSystem.jCheckBoxHealed.isSelected()){
                    fillJtableCases("HEAL","ID","Θεραπευμαίνοι","");    
                }    
                CasesSystem.jComboBoxDeleteId.removeAllItems();
                CasesSystem.FillDeletecombo();
            }
            }catch(NullPointerException e){
                /*αν επιλεχτει το κενό item στο jComboBox3 η 
                jComboBox3.getSelectedItem().toString().equals("") δημιουργεί NullPointerException */
                JOptionPane.showMessageDialog(null,"Παρακαλώ επιλεξτε ID ");
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);

        }

    }
    public static void Searchcase(){
         /* η μέθοδος εμφανιζει τα στοιχεία του κρούσματος του οποίου το ID έχει επιλεχτεί 
       στο jComboBoxSEARCHID τα  */
        try{
        int x =1;    
        try{
            if("".equals(CasesSystem.jComboBoxSEARCHID.getSelectedItem().toString())){
                x = 0;
                JOptionPane.showMessageDialog(null,"Please select ID ");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        if (x==1){
            String ssql = "select * from OVERALLCASES where ID =?";
           CasesSystem.pst = CasesSystem.conn.prepareStatement(ssql);
            CasesSystem.pst.setString(1,CasesSystem.jComboBoxSEARCHID.getSelectedItem().toString());
            CasesSystem.rs=CasesSystem.pst.executeQuery();
            if(CasesSystem.rs.next()){
                String add1 = CasesSystem.rs.getString("NAME");
                CasesSystem.jTextFieldNAME.setText(add1);
                String add2 = CasesSystem.rs.getString("SURNAME");
                CasesSystem.jTextFieldSURNAME.setText(add2);
                String add3 = CasesSystem.rs.getString("AGE");
                CasesSystem.jTextFieldAGE.setText(add3);
                String add4 = CasesSystem.rs.getString("ADDRES");
                CasesSystem.jTextFieldADDRES.setText(add4);
                String add5 = CasesSystem.rs.getString("REGION");
                CasesSystem.jTextFieldCITY.setText(add5);
                String add6 = CasesSystem.rs.getString("PHONE");
                CasesSystem.jTextFieldPHONENUMBER.setText(add6);
                String add7 = CasesSystem.rs.getString("AMKA");
                CasesSystem.jTextFieldAMKA.setText(add7);
            }
            CasesSystem.pst.close();
            CasesSystem.rs.close();
        } 
    }catch(HeadlessException | SQLException e){
        JOptionPane.showMessageDialog(null,e);
    }
    }


        public static void SaveProbCases(){
        try{
            String Serial = "CS"+new SimpleDateFormat("ddMMyyy").format(new Date())+DAO.generateserialId();
            int rows=CasesSystem.jTableProbableCases.getRowCount();
            String sqql = "Insert into PROB (RelatedID,NAME,SURNAME,AGE,ADDRES,REGION, AMKA, PHONE, GENRE,DATE) values (?,?,?,?,?,?,?,?,?,?)";
            CasesSystem.pst = CasesSystem.conn.prepareStatement(sqql);
            for(int roww = 0; roww< rows; roww++){
           // getting from Jtable1 
                String RelatedID = Serial;
                String Name = (String)CasesSystem.jTableProbableCases.getValueAt(roww, 0);
                String Surname = (String)CasesSystem.jTableProbableCases.getValueAt(roww, 1);
                String Age = (String)CasesSystem.jTableProbableCases.getValueAt(roww, 2);
                String Addres = (String)CasesSystem.jTableProbableCases.getValueAt(roww, 3);
                String Region = (String)CasesSystem.jTableProbableCases.getValueAt(roww, 4);
                String Amka = (String)CasesSystem.jTableProbableCases.getValueAt(roww, 5);
                String Phone = (String)CasesSystem.jTableProbableCases.getValueAt(roww, 6);
                String Genre = (String)CasesSystem.jTableProbableCases.getValueAt(roww, 7);
                // setting to database
                CasesSystem.pst.setString(1,RelatedID);
                CasesSystem.pst.setString(2,Name);
                CasesSystem.pst.setString(3,Surname);
                CasesSystem.pst.setString(4,Age);
                CasesSystem.pst.setString(5,Addres);
                CasesSystem.pst.setString(6,Region);
                CasesSystem.pst.setString(7,Amka);
                CasesSystem.pst.setString(8,Phone);
                CasesSystem.pst.setString(9,Genre);
                CasesSystem.pst.setTimestamp(10,DAO.getCurrentTimeStamp());
                CasesSystem.pst.addBatch();
            }
            CasesSystem.pst.executeBatch();
            CasesSystem.pst.close();
            JOptionPane.showMessageDialog(null,"Probable cases saved");
            CasesSystem.clearprobdialodtextfields();//clear dialog fields
            DefaultTableModel model = (DefaultTableModel) CasesSystem.jTableProbableCases.getModel();
            model.setRowCount(0);//clear table
        }catch( SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        finally{
            try {
                CasesSystem.pst.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        } 
        
    }public static int generateserialId(){
        //Επιστρέφει το count των συνολικών κρουσμάτων + 1
        String sql = "select count(ID)+1 from OVERALLCASES";
        try{
            CasesSystem.pst = CasesSystem.conn.prepareStatement(sql);
            CasesSystem.pst.execute();
            CasesSystem.rs = CasesSystem.pst.executeQuery();
            if(CasesSystem.rs.next()){
                getValue = Integer.parseInt(CasesSystem.rs.getString(1)); 
                CasesSystem.pst.close();
                CasesSystem.rs.close();
            }
            CasesSystem.pst.close();
            CasesSystem.rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        return getValue;
        
    }
    public static java.sql.Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        // generates and returns current Timestamp
        return new java.sql.Timestamp(today.getTime());
    }
    public static void DeleteAllcases(){
        //διαγράφει όλες τις κατηγορίες κρουσμάτων από τη βάση
        String ObjButtons[] = {"Yes","No"};
        int PromptResult = JOptionPane.showOptionDialog(null,"Are your sure you want to empty \n"
                + "all tables ?"
                ,"Clearing tables",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
        if(PromptResult==JOptionPane.YES_OPTION){
            try{
                String query= "delete * from CURRENTCASES, OVERALLASES, HEAL, PASSED, PROB ";
                CasesSystem.pst = CasesSystem.conn.prepareStatement(query);
                CasesSystem.pst.execute();
                JOptionPane.showMessageDialog(null, "Data Deleted");
                CasesSystem.pst.close();
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null,e);
            }
                
        }
    }
    
    public static void SaveCases(){
        /*αποθηκεύει στη βαση τα στοιχεία που έχει καταχωρίσει 
        ο χρήστης στα jtextfields που περιγράφουν τα κρούσματα
        */
        
        try{
            String Serial = "CS"+new SimpleDateFormat("ddMMyyy").format(new Date())+DAO.generateserialId();
            String sqqql = "Insert into OVERALLCASES (ID,NAME,SURNAME,AGE,ADDRES,REGION,AMKA,PHONE,GENRE,DATE) values(?,?,?,?,?,?,?,?,?,?)";
            CasesSystem.pst = CasesSystem.conn.prepareStatement(sqqql);
            CasesSystem.pst.setString(1,Serial);
            CasesSystem.pst.setString(2,CasesSystem.jTextFieldNAME.getText());
            CasesSystem.pst.setString(3,CasesSystem.jTextFieldSURNAME.getText());
            CasesSystem.pst.setString(4,CasesSystem.jTextFieldAGE.getText());
            CasesSystem.pst.setString(5,CasesSystem.jTextFieldADDRES.getText());
            CasesSystem.pst.setString(6,CasesSystem.jComboBoxCITY.getSelectedItem().toString());
            CasesSystem.pst.setString(7,CasesSystem.jTextFieldAMKA.getText());
            CasesSystem.pst.setString(8,CasesSystem.jTextFieldPHONENUMBER.getText());
            CasesSystem.pst.setString(9,CasesSystem.genre);
            CasesSystem.pst.setString(10,new SimpleDateFormat("dd-MM-yyy").format(new Date()));
            CasesSystem.pst.execute(); 
            if(COUNT1 == 1 ){
                String b = "delete from PROB where AMKA = '"+CasesSystem.jTextFieldAMKA.getText()+"'";
                CasesSystem.pst = CasesSystem.conn.prepareStatement(b);
                CasesSystem.pst.execute();
                CasesSystem.pst.close();
            }
            String sql = "Insert into CURRENTCASES (ID,NAME,SURNAME, AGE, ADDRES, REGION, AMKA, PHONE,GENRE,DATE) values (?,?,?,?,?,?,?,?,?,?)";
            CasesSystem.pst = CasesSystem.conn.prepareStatement(sql);
            CasesSystem.pst.setString(1,Serial);
            CasesSystem.pst.setString(2,CasesSystem.jTextFieldNAME.getText());
            CasesSystem.pst.setString(3,CasesSystem.jTextFieldSURNAME.getText());
            CasesSystem.pst.setString(4,CasesSystem.jTextFieldAGE.getText());
            CasesSystem.pst.setString(5,CasesSystem.jTextFieldADDRES.getText());
            CasesSystem.pst.setString(6,CasesSystem.jComboBoxCITY.getSelectedItem().toString());
            CasesSystem.pst.setString(7,CasesSystem.jTextFieldAMKA.getText());
            CasesSystem.pst.setString(8,CasesSystem.jTextFieldPHONENUMBER.getText());
            CasesSystem.pst.setString(9,CasesSystem.genre);
            CasesSystem.pst.setString(10,new SimpleDateFormat("dd-MM-yyy").format(new Date()));
            CasesSystem.jComboBoxSEARCHID.addItem(Serial);
            CasesSystem.pst.execute();
            CasesSystem.pst.close();
            JOptionPane.showMessageDialog(null,"cases saved");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        
    }
    
    
} 
    


