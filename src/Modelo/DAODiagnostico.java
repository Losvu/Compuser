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
public class DAODiagnostico {
    Conexion conectar=Conexion.getInstance();
    
    public List ObtenerDatos() throws SQLException{
        String proced = "SP_ListarDiagnosticos()";
        List <Map> registros= new database().Listar(proced);
        List<Diagnostico> diagnostico= new ArrayList();
        for (Map registro : registros){
            Diagnostico diag = new Diagnostico((int) registro.get("Id_diag"),
            (String) registro.get("Descripción"),
            (int) registro.get("Id_equipocomp"),
            (int) registro.get("Id_cliente"),
            (int) registro.get("Id_empleado"));
            diagnostico.add(diag);
        }
        return diagnostico;
    }
    
    public int Insertar(Diagnostico diag) throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL SP_RegDiagnostico(?,?,?,?)}");
            st.setString(1, diag.getDescripción());
            st.setInt(2, diag.getId_equipocomp());
            st.setInt(3, diag.getId_cliente());
            st.setInt(4, diag.getId_empleado());
            st.executeUpdate();
        } catch(SQLException e){
            System.out.println(e + " Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
    
    public int Actualizar(Diagnostico diag) throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL SP_ActualizarDiagnostico(?,?,?,?,?)}");
            st.setInt(1, diag.getId_diag());
            st.setString(2, diag.getDescripción());
            st.setInt(3, diag.getId_equipocomp());
            st.setInt(4, diag.getId_cliente());
            st.setInt(5, diag.getId_empleado());
            st.executeUpdate();
        } catch(SQLException e){
            System.out.println(e + " Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
    
    public int Eliminar (int id) throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL SP_BorrarDiagnostico(?)}");
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
