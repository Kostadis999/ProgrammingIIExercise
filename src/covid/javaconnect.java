package covid;
import java.sql.*;
import javax.swing.*; 
public class javaconnect {
    Connection conn = null;
    public static Connection ConnectDB(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Covidcases.sqlite");
            return conn;
        }catch (ClassNotFoundException | SQLException e){
          JOptionPane.showMessageDialog(null,e);
          return null;
        }
    }
}
