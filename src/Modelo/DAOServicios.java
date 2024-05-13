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
public class DAOServicios {
    Conexion conectar=Conexion.getInstance();
    
    public List ObtenerDatos() throws SQLException{
        String proced = "SP_ListarServicios()";
        List <Map> registros= new database().Listar(proced);
        List<Servicios> servicio= new ArrayList();
        for (Map registro : registros){
            Servicios ser = new Servicios((int) registro.get("Id_ser"),
            (String) registro.get("Descripcion"),
            (float) registro.get("Costo"));
            servicio.add(ser);
        }
        return servicio;
    }
    
    public int Insertar(Servicios ser) throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL SP_RegServicios(?,?)}");
            st.setString(1, ser.getDescripcion());
            st.setFloat(2, ser.getCosto());
            st.executeUpdate();
        } catch(SQLException e){
            System.out.println(e + " Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
    
    public int Actualizar (Servicios ser) throws SQLException{
        try{
            CallableStatement st= conectar.conectar().
                    prepareCall("{CALL SP_ActualizarServicios(?,?,?)}");
            st.setInt(1, ser.getId_ser());
            st.setString(2, ser.getDescripcion());
            st.setFloat(3, ser.getCosto());
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
                    prepareCall("{CALL SP_BorrarServicio(?)}");
            st.setInt(1,id);
            st.executeUpdate();
        } catch(SQLException e){
            System.out.println(e + " Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
}
