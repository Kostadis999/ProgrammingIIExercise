/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid;


import java.awt.Color;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
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
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;


/**
 *
 * @author stefm
 */
public class DAO  {
    static int getValue;
    static int COUNT1;
    static String serial;
    

    public DAO() {
        
        getValue = 0;
        COUNT1 = 0;
        serial = "";
        
    }
    public static void FillDeleltecombo(){
        //γεμίζει τα items του jComboBoxDeleteId με τα ID των ενεργών κρουσμάτων
        try{
            String sql = "Select * from CURRENTCASES";
            CasesSystem.pst = CasesSystem.conn.prepareStatement(sql);
            CasesSystem.rs = CasesSystem.pst.executeQuery();
            while(CasesSystem.rs.next()){
                String ID = CasesSystem.rs.getString("ID");
                CasesSystem.jComboBoxDeleteId.addItem(ID);
            }
            CasesSystem.rs.close();
            CasesSystem.pst.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
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
        jComboBoxDeletId, αν υπάρχει το κρούσμα διαγράφεται απο τα CURRENTCASES και εισάγετε στους θανάτους
        η στις Ιάσεις ανάλογα με τη τιμή της μεταβλητής Dbtable αλλιως εμφανίζεται ανάλογο μήνυμα*/
        try{
            String sql = "select count(*) from CURRENTCASES where ID =  '"+CasesSystem.jComboBoxDeleteId.getSelectedItem().toString()+"' ";
            CasesSystem.pst = CasesSystem.conn.prepareStatement(sql);
            CasesSystem.rs = CasesSystem.pst.executeQuery();
            CasesSystem.rs.next();
            int count = CasesSystem.rs.getInt(1);
            CasesSystem.rs.close();
            if(CasesSystem.jComboBoxDeleteId.getSelectedItem().toString().equals("") ){
                JOptionPane.showMessageDialog(null, "Select the ID of the active case\n"
                        + "that you want to deativate");
            } 
            else if (count == 0) {
                JOptionPane.showMessageDialog(null, "ID doesn't match any case");
                
            }
            else {
                String ObjButtons[] = {"Yes","No"};
                int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to deactivate \n"
                + "this case?" //conformation to delete
                ,"Deactivatig Cases",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                if(PromptResult==JOptionPane.YES_OPTION){
                    String quer = "insert into "+Dbtable+" select * from CURRENTCASES where ID    = '"+CasesSystem.jComboBoxDeleteId.getSelectedItem().toString()+"' ";
                    CasesSystem.pst = CasesSystem.conn.prepareStatement(quer);
                    CasesSystem.pst.execute();
                    String query= "delete from CURRENTCASES where ID = '" +CasesSystem.jComboBoxDeleteId.getSelectedItem().toString()+ "' ";
                    CasesSystem.pst = CasesSystem.conn.prepareStatement(query);
                    CasesSystem.pst.execute();
                    JOptionPane.showMessageDialog(null, "Process completed");
                    CasesSystem.pst.close();	
                    if(CasesSystem.jCheckBoxCurrentCases.isSelected()){
                        fillJtableCases("CURRENTCASES","ID","Active cases","");
                    }else if(CasesSystem.jCheckBoxPassed.isSelected()){
                        fillJtableCases("PASSED","ID","Passeg ","");
                    }else if(CasesSystem.jCheckBoxHealed.isSelected()){
                        fillJtableCases("HEAL","ID","Restored","");    
                    }    
                    CasesSystem.jComboBoxDeleteId.removeAllItems();
                    FillDeleltecombo();
                }
            }
        }catch(NullPointerException e){
            /*αν επιλεχτει  κενό item στο jComboBox3 η 
            jComboBox3.getSelectedItem().toString().equals("") δημιουργεί NullPointerException */
            JOptionPane.showMessageDialog(null,"Παρακαλώ επιλεξτε ID ");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public static void Searchcase(){
         /* η μέθοδος εμφανιζει στα textfields της casesSystem τα στοιχεία του κρούσματος του οποίου το ID έχει επιλεχτεί 
       στο jComboBoxSEARCHID τα  */
        try{
        int x =1;    
            if("".equals(CasesSystem.jComboBoxSEARCHID.getSelectedItem().toString())){
                x = 0;                       //αν επιλεχτει το κενο item στο combobox εμφανίζετε μήνυμα
                JOptionPane.showMessageDialog(null,"Please select ID ");  //και η μέθοδος σταματάει εδώ
            }
        if (x==1){  //αλλιώς πέρνει τα στοιχεία από τη βάση και τα εμφαίζει στα textfields
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
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null,e);
    }
    }
    public static void SaveCases(){
        /*αποθηκεύει στη βαση τα στοιχεία που έχει καταχωρίσει 
        ο χρήστης στα jtextfields που περιγράφουν τα κρούσματα
        */
        
        try{
            //δημιουργειτε serial ID της μορφής CS+Currentdate+serialnumber για το κρούσμα
            serial = "CS"+new SimpleDateFormat("ddMMyyy").format(new Date())+DAO.generateserialId();
            String sqqql = "Insert into OVERALLCASES (ID,NAME,SURNAME,AGE,ADDRES,REGION,AMKA,PHONE,GENRE,DATE) values(?,?,?,?,?,?,?,?,?,?)";
            //προστείθετε το κρούσμα στα συνολικά κρούσματα (πινακας OVERALLCASES της βάσης) 
            CasesSystem.pst = CasesSystem.conn.prepareStatement(sqqql);
            CasesSystem.pst.setString(1,serial);
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
                /*Αν το AMKA που έχει καταχωρίσει ο χρήστης αντιστοιχεί σε πιθανό κρούσμα  
                το κρούσμα διαγράφετε από τον πίνακα τών πιθανών κρουσμάτων*/
                String b = "delete from PROB where AMKA = '"+CasesSystem.jTextFieldAMKA.getText()+"'";
                CasesSystem.pst = CasesSystem.conn.prepareStatement(b);
                CasesSystem.pst.execute();
                CasesSystem.pst.close();
            }
            String sql = "Insert into CURRENTCASES (ID,NAME,SURNAME, AGE, ADDRES, REGION, AMKA, PHONE,GENRE,DATE) values (?,?,?,?,?,?,?,?,?,?)";
            //προστείθετε το κρούσμα στα ενεργά κρούσματα (πινακας CURRENTCASES της βάσης)
            CasesSystem.pst = CasesSystem.conn.prepareStatement(sql);
            CasesSystem.pst.setString(1,serial);
            CasesSystem.pst.setString(2,CasesSystem.jTextFieldNAME.getText());
            CasesSystem.pst.setString(3,CasesSystem.jTextFieldSURNAME.getText());
            CasesSystem.pst.setString(4,CasesSystem.jTextFieldAGE.getText());
            CasesSystem.pst.setString(5,CasesSystem.jTextFieldADDRES.getText());
            CasesSystem.pst.setString(6,CasesSystem.jComboBoxCITY.getSelectedItem().toString());
            CasesSystem.pst.setString(7,CasesSystem.jTextFieldAMKA.getText());
            CasesSystem.pst.setString(8,CasesSystem.jTextFieldPHONENUMBER.getText());
            CasesSystem.pst.setString(9,CasesSystem.genre);
            CasesSystem.pst.setString(10,new SimpleDateFormat("dd-MM-yyy").format(new Date()));
            CasesSystem.jComboBoxSEARCHID.addItem(serial);
            CasesSystem.pst.execute();
            CasesSystem.pst.close();
            JOptionPane.showMessageDialog(null,"cases saved");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        
    }


        public static void SaveProbCases(){
        try{
            
            int rows=CasesSystem.jTableProbableCases.getRowCount();
            String sqql = "Insert into PROB (RelatedID,NAME,SURNAME,AGE,ADDRES,REGION, AMKA, PHONE, GENRE,DATE) values (?,?,?,?,?,?,?,?,?,?)";
            CasesSystem.pst = CasesSystem.conn.prepareStatement(sqql);
            for(int roww = 0; roww< rows; roww++){
           // getting from Jtable1 
                String RelatedID = serial;
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
    public static void  createbarchartsCaseCategoryPerage(String ChartTitle, String Xlabel,String Ylabel,String Dbtable){
       /*H μέθοδος δημιουργεί bar chart (αριθμός κρουσμάτων ανα ηλικιακό group) 
        *το όρισμα dbtable δηλώνει από ποιό πίνακα της βάσης θα επιλεχτούν τα κρούσματα 
        *χωρίζει 12 ηλικιακά group για το άξονα Χ του chart και τα περνάει στον πίνακα D
        */
        try{
            int k = 10;
            int[] ds = new int[13];
            ds[0]=0;
            for(int i = 1; i<=12; i ++){
                ds[i] = k;
                k = k +10;
            }
            String [] D = new String[12];
            D[0] = "0-10"; 
            D[1] = "11-20";
            D[2]  = "21-30";
            D[3]  = "31-40";
            D[4]  = "41-50";
            D[5]  = "51-60";
            D[6]  = "61-70";
            D[7]  = "71-80";
            D[8]  = "81-90";
            D[9]  = "91-100";
            D[10] = "101-110";
            D[11] = "111-120";   
            int [] counts = new int[12];
            for (int i = 1; i<=12;  i++){
                String q = "select count(AGE) from "+Dbtable+" where AGE <= "+ds[i]+"  and AGE >"+ds[i-1]+"";
                CasesSystem.pst = CasesSystem.conn.prepareStatement(q);
                CasesSystem.pst.execute();
                CasesSystem.rs = CasesSystem.pst.executeQuery();
                CasesSystem.rs.next();
                /*πέρνει τα counts μου για τα αντίστοιχα ηλικιακά groups απ τη βάση 
                και τα περνάει στον πίνακα counts*/
                counts[i-1] = CasesSystem.rs.getInt("count(AGE)");
            }
            CasesSystem.pst.close();
            CasesSystem.rs.next();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for(int i = 0; i<=11; i++){
                //δημιουργεί το dataset για το chart με 
                //X: ηλικιακά group(D) Υ:αριθμος κρουσμάτων ανα ηλικιακό group(counts)
                dataset.setValue(counts[i],"Values",D[i]);
            }
            JFreeChart chart = ChartFactory.createBarChart3D(""+ChartTitle+"",""+Xlabel+"",""+Ylabel+"",dataset,PlotOrientation.VERTICAL,false,true,false);
            chart.setBackgroundPaint(Color.WHITE);
            chart.getTitle().setPaint(Color.red);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLUE);
            ChartFrame frame = new ChartFrame("bar chart",chart);
            frame.setVisible(true);
            frame.setSize(850,350);
            String ObjButtons[] = {"Yes","No"};  
            //ρωταει με OptionDialog αν θέλει ο χρήστη να αποθηκευτεί το διάγραμμα σε μορφή png
            int PromptResult = JOptionPane.showOptionDialog(null,"Do you want to save the chart","Save",JOptionPane
                    .DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,ObjButtons,ObjButtons[1]);
            if(PromptResult==JOptionPane.YES_OPTION){
                try{
                    final ChartRenderingInfo Info = new ChartRenderingInfo(new StandardEntityCollection());
                    String x = new SimpleDateFormat("yyMMddHHmmssZ").format(new Date());
                    final File file1 = new File("Chart"+x+".png");
                    ChartUtilities.saveChartAsPNG(file1,chart,600,400,Info);
                }catch(IOException e){
                    JOptionPane.showMessageDialog(null,e);
                }    
            }
            
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,e);
            }
        finally{
            try{
                CasesSystem.rs.close();
                CasesSystem.pst.close();              
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }
    public static void createpieChartscasecatperage(String dbtable,String label){
        /*η μεθοδος δημιουργεί και εμφανίζει Pie Chart (αριθμός κρουσμάτων ανα φύλο) 
        *το όρισμα dbtable δηλώνει από ποιό πίνακα της βάσης θα επιλεχτούν τα κρούσματα 
        */
        try{
            String d1 = "0 - 20";
            String d2 = "21 - 40";
            String d3 = "41 - 60";
            String d4 = "61 - 80";
            String d5 = "81 - 100";
            String d6 = "101 - 120";        
            DefaultPieDataset pieDataset = new DefaultPieDataset(); 
            String q = "select count(AGE) from "+dbtable+" where AGE <= 20";
            CasesSystem.pst = CasesSystem.conn.prepareStatement(q);
            CasesSystem.pst.execute();
            CasesSystem.rs = CasesSystem.pst.executeQuery();
            CasesSystem.rs.next();
            int c1 = CasesSystem.rs.getInt("count(AGE)");
            CasesSystem.rs.close();
            String q1 = "select count(AGE) from "+dbtable+" where AGE <= 40 and AGE > 20";
            CasesSystem.pst = CasesSystem.conn.prepareStatement(q1);
            CasesSystem.pst.execute();
            CasesSystem.rs = CasesSystem.pst.executeQuery();
            CasesSystem.rs.next();
            int c2 = CasesSystem.rs.getInt("count(AGE)");
            CasesSystem.rs.close();
            String q2 = "select count(AGE) from "+dbtable+" where AGE <= 60 and AGE > 40";
            CasesSystem.pst = CasesSystem.conn.prepareStatement(q2);
            CasesSystem.pst.execute();
            CasesSystem.rs = CasesSystem.pst.executeQuery();
            CasesSystem.rs.next();
            int c3 = CasesSystem.rs.getInt("count(AGE)");
            CasesSystem.rs.close();
            String q3 = "select count(AGE) from "+dbtable+" where AGE <= 80 and AGE > 60";
            CasesSystem.pst = CasesSystem.conn.prepareStatement(q3);
            CasesSystem.pst.execute();
            CasesSystem.rs = CasesSystem.pst.executeQuery();
            CasesSystem.rs.next();
            int c4 = CasesSystem.rs.getInt("count(AGE)");
            CasesSystem.rs.close();
            String q4 = "select count(AGE) from "+dbtable+" where AGE <= 100 and AGE > 80";
            CasesSystem.pst = CasesSystem.conn.prepareStatement(q4);
            CasesSystem.pst.execute();
            CasesSystem.rs = CasesSystem.pst.executeQuery();
            CasesSystem.rs.next();
            int c5 = CasesSystem.rs.getInt("count(AGE)");
            CasesSystem.rs.close();
            String q5 = "select count(AGE) from "+dbtable+" where AGE <= 120 and AGE > 100";
            CasesSystem.pst = CasesSystem.conn.prepareStatement(q5);
            CasesSystem.pst.execute();
            CasesSystem.rs = CasesSystem.pst.executeQuery();
            CasesSystem.rs.next();
            int c6 = CasesSystem.rs.getInt("count(AGE)");
            CasesSystem.rs.close();
            pieDataset.setValue(d1,c1);
            CasesSystem.rs.close();
            pieDataset.setValue(d2,c2);
            CasesSystem.rs.close();
            pieDataset.setValue(d3,c3);
            CasesSystem.rs.close();
            pieDataset.setValue(d4,c4);
            CasesSystem.rs.close();
            pieDataset.setValue(d5,c5);
            CasesSystem.rs.close();
            pieDataset.setValue(d6,c6);
            CasesSystem.rs.close();
            JFreeChart chart = ChartFactory.createPieChart3D("",pieDataset,true,true,true);
            PiePlot3D p = (PiePlot3D)chart.getPlot();
            ChartFrame frame = new ChartFrame("Pie Chart: "+label+"",chart);
            frame.setVisible(true);
            frame.setSize(450,500);
            String ObjButtons[] = {"Yes","No"};  //ρωταει με OptionDialog αν θελει να αποθηκευτει το διάγραμμα σε μορφή png
            int PromptResult = JOptionPane.showOptionDialog(null,"Do you want to save the chart","Save",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,ObjButtons,ObjButtons[1]);
            if(PromptResult==JOptionPane.YES_OPTION){
                try{
                    final ChartRenderingInfo Info = new ChartRenderingInfo(new StandardEntityCollection());
                    String x = new SimpleDateFormat("yyMMddHHmmssZ").format(new Date());
                    final File file1 = new File("Chart"+x+".png");
                    ChartUtilities.saveChartAsPNG(file1,chart,600,400,Info);
                }catch(IOException e){
                    JOptionPane.showMessageDialog(null,e);
                }    
            }
            
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,e);
            }
        finally{
            try{
                CasesSystem.rs.close();
                CasesSystem.pst.close();
              
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }
    public static void createBarChartGender(String dbtble,String ChartTitle, String Xlabel,String Ylabel){
       /*η μεθοδος δημιουργεί και εμφανίζει Bar Chart (αριθμός κρουσμάτων ανα φύλο) 
        *το όρισμα dbtable δηλώνει από ποιό πίνακα της βάσης θα επιλεχτούν τα κρούσματα 
        */
        
        try{
            String q = "select count(*) from "+dbtble+" WHERE GENRE = 'Male'";
            String m = "select count(*) from "+dbtble+" WHERE GENRE = 'Female'";
            CasesSystem.pst  = CasesSystem.conn.prepareStatement(q);
            CasesSystem.rs = CasesSystem.pst.executeQuery();
            CasesSystem.rs.next();
            int v = CasesSystem.rs.getInt("count(*)");
            CasesSystem.pst  = CasesSystem.conn.prepareStatement(m);
            CasesSystem.rs = CasesSystem.pst.executeQuery();
            CasesSystem.rs.next();
            int z = CasesSystem.rs.getInt("count(*)");
            String d1 = "Males";
            String d2 = "Females";
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(v,"Males",d1);
            dataset.setValue(z,"Females",d2);
            JFreeChart chart = ChartFactory.createBarChart3D(""+ChartTitle+"",""+Xlabel+"",""+Ylabel+"",dataset,PlotOrientation.VERTICAL,false,true,false);
            chart.setBackgroundPaint(Color.WHITE);
            chart.getTitle().setPaint(Color.red);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLUE);
            ChartFrame frame = new ChartFrame("bar chart",chart);
            frame.setVisible(true);
            frame.setSize(450,350);
            String ObjButtons[] = {"Yes","No"};
            //ρωταει με OptionDialog για την αποθήκευση του διαγράμματος, το διάγραμμα θα αποθηκευτεί σε μορφή png
            int PromptResult = JOptionPane.showOptionDialog(null,"Do you want to save the chart","Save",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,ObjButtons,ObjButtons[1]);
            if(PromptResult==JOptionPane.YES_OPTION){
                try{
                    final ChartRenderingInfo Info = new ChartRenderingInfo(new StandardEntityCollection());
                    //
                    String x = new SimpleDateFormat("yyMMddHHmmssZ").format(new Date());
                    /*το διάγραμμα θα αποθηκευτεί σε μορφή png
                    με όνομα την χρονική στιγμή της αποθήκευσης για να είναι uniq το όνομα του αρχείου*/
                    final File file1 = new File("Chart"+new SimpleDateFormat("yyMMddHHmmssZ").format(new Date())+".png");
                    ChartUtilities.saveChartAsPNG(file1,chart,600,400,Info);
                }catch(IOException e){
                    JOptionPane.showMessageDialog(null,"couldn't save Chart");
                }
            }

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"η βάση δε μπόρεσε να ανταποκριθεί παρακαλώ");
        }
        
    }
    public static void createpieChartsGender(String dbtble,String Label){
       /*η μεθοδος δημιουργεί και εμφανίζει Pie Chart (αριθμός κρουσμάτων ανα φύλο) 
        *το όρισμα dbtable δηλώνει από ποιό πίνακα της βάσης θα επιλεχτούν τα κρούσματα 
        */
        try{
            String q = "select count(*) from "+dbtble+" WHERE GENRE = 'Male'";
            String m = "select count(*) from "+dbtble+" WHERE GENRE = 'Female'";
            CasesSystem.pst  = CasesSystem.conn.prepareStatement(m);
            CasesSystem.rs = CasesSystem.pst.executeQuery();
            CasesSystem.rs.next();
            int v = CasesSystem.rs.getInt("count(*)");
            CasesSystem.pst  = CasesSystem.conn.prepareStatement(q);
            CasesSystem.rs = CasesSystem.pst.executeQuery();
            CasesSystem.rs.next();
            int z = CasesSystem.rs.getInt("count(*)");
            DefaultPieDataset pieDataset = new DefaultPieDataset();
            pieDataset.setValue("Males",v);
            pieDataset.setValue("Females",z);
            JFreeChart chart = ChartFactory.createPieChart3D("",pieDataset,true,true,true);
            PiePlot3D p = (PiePlot3D)chart.getPlot();
            ChartFrame frame = new ChartFrame(Label,chart);
            frame.setVisible(true);
            frame.setSize(450,500);

            String ObjButtons[] = {"Yes","No"};//ρωταει με OptionDialog αν θελει να αποθηκευτει το κρούσμα
            int PromptResult = JOptionPane.showOptionDialog(null,"Do you want to save the chart","Save",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,ObjButtons,ObjButtons[1]);
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
    }
    
    
} 
    






