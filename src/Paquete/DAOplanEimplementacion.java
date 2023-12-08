
package Paquete;

import java.sql.*;

/**
 *
 * @author Leonardo
 */
public class DAOplanEimplementacion implements DAOplanEntrenamiento{
    
    Conexion conexion = Conexion.getInstance();

    @Override
    public void registrarPlanE(PlanEntrenamiento planentrenamiento) {
        
        try {
            
            Connection conectar = conexion.conectar();
            PreparedStatement insertarPlaE = conectar.prepareStatement("insert into plan_entrenamiento values(?,?,?,?)");
            
            insertarPlaE.setInt(1, planentrenamiento.getId_PlanE());
            insertarPlaE.setString(2, planentrenamiento.getNombreP());
            insertarPlaE.setFloat(3, planentrenamiento.getValorP());
            insertarPlaE.setInt(4, planentrenamiento.getDuracionP());
            insertarPlaE.executeUpdate();
            
            conexion.desconectar();
            
        } catch (SQLException e) {
        }
        
    }

    @Override
    public void modificarPlanE(PlanEntrenamiento planentrenamiento) {
     
        try {
            
            Connection conectar = conexion.conectar();
            PreparedStatement modificarPlanE = conectar.prepareStatement("UPDATE Plan_entrenamiento set Nombre_P = ?, Valor =?, Duracion = ? where Id_Plan = ? ");
            
            modificarPlanE.setString(1, planentrenamiento.getNombreP());
            modificarPlanE.setFloat(2, planentrenamiento.getValorP());
            modificarPlanE.setInt(3, planentrenamiento.getDuracionP());
            modificarPlanE.setInt(4, planentrenamiento.getId_PlanE());
            modificarPlanE.executeUpdate();
            
        } catch (SQLException e) {
        }
        
    }

    @Override
    public void eliminarPlanE(PlanEntrenamiento planentrenamiento) {
        
        try {
            
            Connection conectar = conexion.conectar();
            PreparedStatement eliminarPlanE = conectar.prepareStatement("DELETE from Plan_entrenamiento where Id_Plan= ? ");
            
            eliminarPlanE.setInt(1, planentrenamiento.getId_PlanE());
            eliminarPlanE.executeUpdate();
            
            conexion.desconectar();
            
        } catch (SQLException e) {
        }
        
    }

    @Override
    public void buscarPlanE(PlanEntrenamiento planentrenamiento) {
        
        try {
            
            Connection conectar = conexion.conectar();
            PreparedStatement buscarPlanE = conectar.prepareStatement("SELECT * FROM Plan_entrenamiento WHERE Id_Plan = ?");
            
            buscarPlanE.setInt(1, planentrenamiento.getId_PlanE());
            
            ResultSet consultaPlan = buscarPlanE.executeQuery();
            
            if(consultaPlan.next()){
                planentrenamiento.setId_PlanE(Integer.parseInt(consultaPlan.getString("Id_Plan")));
                planentrenamiento.setNombreP(consultaPlan.getString("Nombre_P"));
                planentrenamiento.setValorP(Float.parseFloat(consultaPlan.getString("Valor")));
                planentrenamiento.setDuracionP(Integer.parseInt(consultaPlan.getString("Duracion")));
                
                conexion.desconectar();
                
            }
            
        } catch (SQLException e) {
        }
        
    }
    
}
