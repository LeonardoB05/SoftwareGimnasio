
package Paquete;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;


public class Conexion {
    
    private Conexion(){
        
    }
    
    private static Connection conexion;
    
    private static Conexion instancia;
    
    private static final String URL = "jdbc:mysql://localhost/gimnasio";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    public Connection conectar(){
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            //JOptionPane.showMessageDialog(null, "Acceso Exitoso!");
            
            return conexion;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
        }
        return conexion;
    }
    
    
    
    public void desconectar() throws SQLException{
        try {
            
            conexion.close();
            //JOptionPane.showMessageDialog(null, "Se ha desconectado de la base de datos");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
            conexion.close();
        } finally {
            conexion.close();
        }
    }
    
    // PATRÓN SINGLETON
    public static Conexion getInstance(){
        if(instancia == null){
            instancia = new Conexion();
        }
        return instancia;
    }
}
