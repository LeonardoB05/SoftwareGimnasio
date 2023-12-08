
package Paquete;

import java.sql.*;

/**
 *
 * @author Leonardo
 */
public class DAOentrenadorImplementacion implements DAOentrenador{
    
    Conexion conexion = Conexion.getInstance();

    @Override
    public void registrarEnt(Entrenador entrenador) {
        
        try {
            
            Connection conectar = conexion.conectar();
            PreparedStatement insertarEnt = conectar.prepareStatement("insert into Entrenador values(?,?,?,?)");
            
            insertarEnt.setInt(1, entrenador.getId_entrenador());
            insertarEnt.setString(2, entrenador.getNombreEnt());
            insertarEnt.setInt(3, entrenador.getTelefonoEnt());
            insertarEnt.setString(4, entrenador.getEspecialidadEnt());
            insertarEnt.executeUpdate();
            
            conexion.desconectar();
            
        } catch (SQLException e) {
            
        }
        
    }

    @Override
    public void modificarEnt(Entrenador entrenador) {
        
        try {
            
            Connection conectar = conexion.conectar();
            PreparedStatement modificarEnt = conectar.prepareStatement("UPDATE Entrenador set Nombre_E = ?, Telefono_E =?, Especialidad = ? where Id_Entrenador = ? ");
            
            modificarEnt.setString(1, entrenador.getNombreEnt());
            modificarEnt.setInt(2, entrenador.getTelefonoEnt());
            modificarEnt.setString(3, entrenador.getEspecialidadEnt());
            modificarEnt.setInt(4, entrenador.getId_entrenador());
            modificarEnt.executeUpdate();
            
            conexion.desconectar();
            
        } catch (SQLException e) {
        }
        
    }

    @Override
    public void eliminarEnt(Entrenador entrenador) {
        
        try {
            
            Connection conectar = conexion.conectar();
            PreparedStatement eliminarEnt = conectar.prepareStatement("DELETE from Entrenador where Id_Entrenador= ? ");
            
            eliminarEnt.setInt(1, entrenador.getId_entrenador());
            eliminarEnt.executeUpdate();
            
            conexion.desconectar();
            
        } catch (SQLException e) {
        }
        
    }

    @Override
    public void buscarEnt(Entrenador entrenador) {
        
        try {
            
            Connection conectar = conexion.conectar();
            PreparedStatement buscarEnt = conectar.prepareStatement("SELECT * FROM Entrenador WHERE Id_Entrenador = ?");
            
            buscarEnt.setInt(1, entrenador.getId_entrenador());
            
            ResultSet consultaEnt = buscarEnt.executeQuery();
            
            if(consultaEnt.next()){
                entrenador.setId_entrenador(Integer.parseInt(consultaEnt.getString("Id_Entrenador")));
                entrenador.setNombreEnt(consultaEnt.getString("Nombre_E"));
                entrenador.setTelefonoEnt(Integer.parseInt(consultaEnt.getString("Telefono_E")));
                entrenador.setEspecialidadEnt(consultaEnt.getString("Especialidad"));
                
                conexion.desconectar();
            }
            
        } catch (SQLException e) {
        }
        
    }
    
}
