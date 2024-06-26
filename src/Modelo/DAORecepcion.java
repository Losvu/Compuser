/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Modelo.Conexion.Conexion;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.swing.JOptionPane;
/**
 *
 * @author David
 */
public class DAORecepcion {
    Conexion conectar=Conexion.getInstance();
    
    public List ObtenerDatos() throws SQLException{
        String proced = "SP_ListarRecepcion()";
        List <Map> registros= new database().Listar(proced);
        List <Recepcion> recepciones= new ArrayList();
        for(Map registro : registros){
            Recepcion rec = new Recepcion ((int) registro.get("Id_recepcion"),
            (Date) registro.get("Fecha"),
            (String) registro.get("Estado_recepcion"),
            (int) registro.get("Id_cliente"),
            (int) registro.get("Id_equipocomp"),
            (int) registro.get("Id_empleado"));
            recepciones.add(rec);
        }
        return recepciones;
    }
    
    public int Insertar(Recepcion rec) throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL SP_RegRecepcion(?,?,?,?,?)}");
            st.setDate(1, rec.getFecha());
            st.setString(2, rec.getEstado_recepcion());
            st.setInt(3, rec.getId_cliente());
            st.setInt(4, rec.getId_equipocomp());
            st.setInt(5, rec.getId_empleado());
            st.executeUpdate();
        } catch(SQLException e){
            System.out.println(e + " Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
    
    public int Actualizar(Recepcion rec) throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL SP_ActualizarRecepcion(?,?,?,?,?,?)}");
            st.setInt(1, rec.getId_recepcion());
            st.setDate(2, rec.getFecha());
            st.setString(3, rec.getEstado_recepcion());
            st.setInt(4, rec.getId_cliente());
            st.setInt(5, rec.getId_equipocomp());
            st.setInt(6, rec.getId_empleado());
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
                    prepareCall("{CALL SP_BorrarRecepcion(?)}");
            st.setInt(1, id);
            st.executeUpdate();
        }catch(SQLException e){
            System.out.println(e + " Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
    public int ObtenerUltimoIDRecepcion() throws SQLException{
        int IDR = 0;
        ResultSet rs=null;
        try{
            CallableStatement st = conectar.conectar().
                    prepareCall("{CALL ObtenerUltimoIDRecepcion()}");
            rs = st.executeQuery();
            if(rs.next()){
                IDR = rs.getInt(1);
            }
            return IDR;
        } catch(SQLException e){
            System.out.println("No se realizó la consulta: "+ e.getMessage());
            return 0;
        }
    }
}
