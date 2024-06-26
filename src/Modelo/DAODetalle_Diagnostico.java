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
public class DAODetalle_Diagnostico {
    Conexion conectar=Conexion.getInstance();
    
    public List ObtenerDatos() throws SQLException{
        String proced = "SP_ListarDetallediag()";
        List <Map> registros= new database().Listar(proced);
        List<Detalle_Diagnostico> detalle= new ArrayList();
        for (Map registro : registros){
            Detalle_Diagnostico detdiag = new Detalle_Diagnostico
            ((int) registro.get("Id_detallediag"),
            (float) registro.get("Costo"),
            (int) registro.get("Id_diag"),
            (int) registro.get("Id_ser"));
            detalle.add(detdiag);
        }
        return detalle;
    }
    
    public int Insertar(Detalle_Diagnostico detdiag) throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL SP_RegDetalleDiag(?,?,?)}");
            st.setFloat(1, detdiag.getCosto());
            st.setInt(2, detdiag.getId_diag());
            st.setInt(3, detdiag.getId_ser());
            st.executeUpdate();
        } catch(SQLException e){
            System.out.println(e + " Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
    
    public int Actualizar (Detalle_Diagnostico det) throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL SP_ActualizarDetallediag(?,?,?,?)}");
            st.setInt(1, det.getId_detallediag());
            st.setFloat(2, det.getCosto());
            st.setInt(3,det.getId_diag());
            st.setInt(4, det.getId_ser());
            st.executeUpdate();
        }catch(SQLException e){
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
                    prepareCall("{CALL SP_BorrarDetallediag(?)}");
            st.setInt(1, id);
            st.executeUpdate();
        }catch (SQLException e){
            System.out.println( e + " Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
    
    public int insertarDetalleDiagnostico (Detalle_Diagnostico detalle) throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL insertarDetalleDiagnostico(?,?,?)}");
            st.setFloat(1, detalle.getCosto());
            st.setInt(2,detalle.getId_diag());
            st.setInt(3,detalle.getId_ser());
            st.executeUpdate();
        } catch(SQLException e){
            System.out.println(e +" Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
}
