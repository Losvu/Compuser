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
public class DAOEntrega {
    Conexion conectar=Conexion.getInstance();
    
    public List ObtenerDatos() throws SQLException{
        String proced = "SP_ListarEntregas()";
        List <Map> registros= new database().Listar(proced);
        List <Entrega> entregas= new ArrayList();
        for(Map registro : registros){
            Entrega ent = new Entrega ((int) registro.get("Id_entregaequi"),
            (Date) registro.get("Fecha"),
            (String) registro.get("Estado_entrega"),
            (int) registro.get("Id_equipocomp"),
            (int) registro.get("Id_cliente"),
            (int) registro.get("Id_empleado"));
            entregas.add(ent);
        }
        return entregas;
    }
    
    public int Insertar(Entrega ent) throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL SP_RegEntrega(?,?,?,?,?)}");
            st.setDate(1, ent.getFecha());
            st.setString(2, ent.getEstado_entrega());
            st.setInt(3, ent.getId_equipocomp());
            st.setInt(4, ent.getId_cliente());
            st.setInt(5, ent.getId_empleado());
            st.executeUpdate();
        } catch(SQLException e){
            System.out.println(e + " Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
    
    public int Actualizar (Entrega ent) throws SQLException {
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL SP_ActualizarEntrega(?,?,?,?,?,?)}");
            st.setInt(1, ent.getId_entregaequi());
            st.setDate(2, ent.getFecha());
            st.setString(3, ent.getEstado_entrega());
            st.setInt(4, ent.getId_equipocomp());
            st.setInt(5, ent.getId_cliente());
            st.setInt(6, ent.getId_empleado());
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
                    prepareCall("{CALL SP_BorrarEntrega(?)}");
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
    
    public int ObtenerUltimoIDEntrega() throws SQLException{
        int IDE = 0;
        ResultSet rs=null;
        try{
            CallableStatement st = conectar.conectar().
                    prepareCall("{CALL ObtenerUltimoIDEntrega()}");
            rs = st.executeQuery();
            if(rs.next()){
                IDE = rs.getInt(1);
            }
            return IDE;
        } catch(SQLException e){
            System.out.println("No se realizó la consulta: "+ e.getMessage());
            return 0;
        }
    }
    
    
}
