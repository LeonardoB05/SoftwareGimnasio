package Paquete;

import java.sql.*;

public class DAOclienteImplementacion implements DAOcliente{
    
    Conexion conexion = Conexion.getInstance();

    @Override
    public void registrarC(Cliente cliente) {
        
        try {
            
            Connection conectar = conexion.conectar();
            PreparedStatement insertarC = conectar.prepareStatement("insert into Cliente values(?,?,?,?,?)");
            
            insertarC.setInt(1, cliente.getId_cliente());
            insertarC.setString(2, cliente.getNombreC());
            insertarC.setInt(3, cliente.getEdadC());
            insertarC.setFloat(4, cliente.getPesoC());
            insertarC.setInt(5, cliente.getTelefonoC());
            insertarC.executeUpdate();
            
            conexion.desconectar();
            
        } catch (SQLException e) {
            
        }
        
    }

    @Override
    public void modificarC(Cliente cliente) {
        
        try {
            
            Connection conectar = conexion.conectar();
            PreparedStatement modificarC = conectar.prepareStatement("UPDATE Cliente set Nombre_Cli = ?, Edad_Cli = ?, Peso_Cli = ?, Telefono_Clie = ? where Id_Cliente = ? ");
            
            modificarC.setString(1, cliente.getNombreC() );
            modificarC.setInt(2, cliente.getEdadC());
            modificarC.setFloat(3, cliente.getPesoC());
            modificarC.setInt(4, cliente.getTelefonoC());
            modificarC.setInt(5, cliente.getId_cliente());
            modificarC.executeUpdate();
            
            conexion.desconectar();
            
        } catch (SQLException e) {
        }
        
    }

    @Override
    public void eliminarC(Cliente cliente) {
         
        try {
            
            Connection conectar = conexion.conectar();
            PreparedStatement eliminarC = conectar.prepareStatement("DELETE from Cliente where Id_Cliente = ? ");
            
            eliminarC.setInt(1, cliente.getId_cliente());
            eliminarC.executeUpdate();
            
            conexion.desconectar();
            
        } catch (SQLException e) {
        }
    }

    @Override
    public void buscarC(Cliente cliente) {
        
        try {
            
            Connection conectar = conexion.conectar();
            PreparedStatement buscarC = conectar.prepareStatement("SELECT * FROM Cliente WHERE Id_Cliente = ?");
            
            buscarC.setInt(1, cliente.getId_cliente());
            
            ResultSet consultaCli = buscarC.executeQuery();
            
            if(consultaCli.next()){
                cliente.setId_cliente(Integer.parseInt(consultaCli.getString("Id_Cliente")));
                cliente.setNombreC(consultaCli.getString("Nombre_Cli"));
                cliente.setEdadC(Integer.parseInt(consultaCli.getString("Edad_Cli")));
                cliente.setPesoC(Float.parseFloat(consultaCli.getString("Peso_Cli")));
                cliente.setTelefonoC(Integer.parseInt(consultaCli.getString("Telefono_Clie")));
                
                conexion.desconectar();
            }
            
        } catch (SQLException e) {
        }
        
    }
    
}
