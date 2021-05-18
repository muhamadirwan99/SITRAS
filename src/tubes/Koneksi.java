package tubes;

import java.sql.*;
import javax.swing.*;

public class Koneksi {
    private static Connection conn;
    public static Connection getKoneksi(){
        String host = "jdbc:mysql://localhost/sitras",
               user = "root",
               pass = "";
        try {
            conn = (Connection) DriverManager.getConnection(host,user,pass);
        }catch (SQLException err){
            JOptionPane.showMessageDialog(null,err.getMessage());
        }
        return conn;
    }
}
