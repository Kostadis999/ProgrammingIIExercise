/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid;


import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;


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
        }catch(SQLException e){
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
    


