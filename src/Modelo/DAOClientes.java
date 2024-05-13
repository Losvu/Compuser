/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Modelo.Conexion.Conexion;
import java.sql.*;
import java.util.*;
/**
 *
 * @author David
 */
public class DAOClientes {
    Conexion conectar=Conexion.getInstance();
    
    public List ObtenerDatos() throws SQLException{
        String proced = "SP_ListarCliente()";
        List <Map> registros= new database().Listar(proced);
        List<Clientes> cliente= new ArrayList();
        for (Map registro : registros){
            Clientes cli = new Clientes((int) registro.get("Id_cliente"),
            (String) registro.get("Nombre"),
            (String) registro.get("Apellido"),
            (String) registro.get("Direccion"),
            (String) registro.get("tipo_cli"),
            (int) registro.get("Telefono"),
            (String) registro.get("Cedula"));
            cliente.add(cli);
        }
        return cliente;
    }
    
    public int Insertar(Clientes cli) throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL SP_RegCliente(?,?,?,?,?,?)}");
            st.setString(1, cli.getNombre());
            st.setString(2, cli.getApellido());
            st.setString(3, cli.getDireccion());
            st.setString(4, cli.getTipo_cli());
            st.setInt(5, cli.getTelefono());
            st.setString(6, cli.getCedula());
            st.executeUpdate();
        } catch(SQLException e){
            System.out.println(e + " Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
    
    public int Actualizar (Clientes cli) throws SQLException {
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL SP_ActualizarCliente(?,?,?,?,?,?,?)}");
            st.setInt(1, cli.getId_cliente());
            st.setString(2, cli.getNombre());
            st.setString(3, cli.getApellido());
            st.setString(4, cli.getDireccion());
            st.setString(5, cli.getTipo_cli());
            st.setInt(6, cli.getTelefono());
            st.setString(7, cli.getCedula());
            st.executeUpdate();
        } catch(SQLException e){
            System.out.println(e + " Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
    
    public int Eliminar(int id) throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL SP_BorrarCliente(?)}");
            st.setInt(1, id);
            st.executeUpdate();
        } catch(SQLException e){
            System.out.println(e+" Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
}
