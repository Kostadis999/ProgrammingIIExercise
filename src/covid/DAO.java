/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid;


import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.awt.List;
import static java.rmi.Naming.list;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author stefm
 */
public class DAO {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
     Collection<Case> temp ;
    

    public DAO() {
        
        
        conn = javaconnect.ConnectDB();
        
    }
    public  ResultSet getCurrentcases(){
        
        try {
            String sql = "Select * FROM CURRENTCASES ";
            pst  = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            return rs;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        try{
            pst.close();            
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        return null;
    }
} 
    


