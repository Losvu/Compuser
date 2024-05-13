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
public class DAOEquipos_Computarizados {
    Conexion conectar=Conexion.getInstance();
    
    public List ObtenerDatos() throws SQLException{
        String proced = "SP_ListarEquipos()";
        List <Map> registros= new database().Listar(proced);
        List <Equipos_Computarizados> equipos= new ArrayList();
        for(Map registro: registros){
            Equipos_Computarizados equi = new Equipos_Computarizados
            ((int) registro.get("Id_equipocomp"),
            (String) registro.get("Tipo"),
            (String) registro.get("Marca"),
            (String) registro.get("Color"),
            (String) registro.get("Modelo"),
            (int) registro.get("Id_cliente"));
            equipos.add(equi);
        }
        return equipos;
    }
    
    public int Insertar(Equipos_Computarizados equi) throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL SP_RegEquipoComp(?,?,?,?,?)}");
            st.setString(1, equi.getTipo());
            st.setString(2, equi.getMarca());
            st.setString(3, equi.getColor());
            st.setString(4, equi.getModelo());
            st.setInt(5, equi.getId_cliente());
            st.executeUpdate();
        } catch (SQLException e){
            System.out.print(e + " Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
    
    public int Actualizar (Equipos_Computarizados equi) throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL SP_ActualizarEquipos(?,?,?,?,?,?)}");
            st.setInt(1, equi.getId_equipocomp());
            st.setString(2, equi.getTipo());
            st.setString(3, equi.getMarca());
            st.setString(4, equi.getColor());
            st.setString(5, equi.getModelo());
            st.setInt(6, equi.getId_cliente());
            st.executeUpdate();
        } catch(SQLException e) {
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
                    prepareCall("{CALL SP_BorrarEquipo(?)}");
            st.setInt(1, id);
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
