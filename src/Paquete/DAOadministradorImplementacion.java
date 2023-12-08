
package Paquete;

import java.sql.*;
/**
 *
 * @author Leonardo
 */
public class DAOadministradorImplementacion implements DAOadministrador{
    
    Conexion conexion = Conexion.getInstance();

    @Override
    public void registrarAdmin(Administrador administrador) {
        
        try {
            
            Connection conectar = conexion.conectar();
            PreparedStatement insertarAdmin = conectar.prepareStatement("insert into Administrador values(?,?,?,?)");
            
            insertarAdmin.setInt(1, administrador.getId_Admin());
            insertarAdmin.setString(2, administrador.getContraseñaAdmin());
            insertarAdmin.setString(3, administrador.getNombreAdmin());
            insertarAdmin.setInt(4, administrador.getTelefonoAdmin());
            insertarAdmin.executeUpdate();
            
            conexion.desconectar();
            
        } catch (SQLException e) {
        }
        
    }

    @Override
    public void modificarAdmin(Administrador administrador) {
        
        try {
            
            Connection conectar = conexion.conectar();
            PreparedStatement modificarAdmin = conectar.prepareStatement("UPDATE Administrador set Contraseña = ?, Nombre_Admin =?, Telefono_Admin = ? where Id_Admin = ? ");
            
            modificarAdmin.setString(1, administrador.getContraseñaAdmin());
            modificarAdmin.setString(2, administrador.getNombreAdmin());
            modificarAdmin.setInt(3, administrador.getTelefonoAdmin());
            modificarAdmin.setInt(4, administrador.getId_Admin());
            modificarAdmin.executeUpdate();
            
        } catch (SQLException e) {
        }
        
    }

    @Override
    public void eliminarAdmin(Administrador administrador) {
        
        try {
            
            Connection conectar = conexion.conectar();
            PreparedStatement eliminarAdmin = conectar.prepareStatement("DELETE from Administrador where Id_Admin= ? ");
            
            eliminarAdmin.setInt(1, administrador.getId_Admin());
            eliminarAdmin.executeUpdate();
            
        } catch (SQLException e) {
        }
        
    }

    @Override
    public void buscarAdmin(Administrador administrador) {
        
        try {
            
            Connection conectar = conexion.conectar();
            PreparedStatement buscarAdmin = conectar.prepareStatement("SELECT * FROM Administrador WHERE Id_Admin = ?");
            
            buscarAdmin.setInt(1, administrador.getId_Admin());
            
            ResultSet consultaAdmin = buscarAdmin.executeQuery();
            
            if(consultaAdmin.next()){
                administrador.setId_Admin(Integer.parseInt(consultaAdmin.getString("Id_Admin")));
                administrador.setContraseñaAdmin(consultaAdmin.getString("Contraseña"));
                administrador.setNombreAdmin(consultaAdmin.getString("Nombre_Admin"));
                administrador.setTelefonoAdmin(Integer.parseInt(consultaAdmin.getString("Telefono_Admin")));
                
                conexion.desconectar();
            }
            
        } catch (SQLException e) {
        }
        
    }
    
}
