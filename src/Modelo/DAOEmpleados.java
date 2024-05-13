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
public class DAOEmpleados {
    Conexion conectar=Conexion.getInstance();
    
    public List ObtenerDatos() throws SQLException{
        String proced = "SP_ListarEmpleados()";
        List <Map> registros= new database().Listar(proced);
        List<Empleados> empleado= new ArrayList();
        for (Map registro : registros){
            Empleados emp = new Empleados((int) registro.get("Id_empleado"),
            (String) registro.get("Nombre"),
            (String) registro.get("Apellido"),
            (int) registro.get("Telefono"),
            (String) registro.get("Direccion"),
            (String) registro.get("Cedula"));
            empleado.add(emp);
        }
        return empleado;
    }
    
    public int Insertar(Empleados emp) throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL SP_RegCliente(?,?,?,?,?)}");
            st.setString(1, emp.getNombre());
            st.setString(2, emp.getApellido());
            st.setInt(3, emp.getTelefono());
            st.setString(4, emp.getDireccion());
            st.setString(5, emp.getCedula());
            st.executeUpdate();
        } catch(SQLException e){
            System.out.println(e + " Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
    
    public int Actualizar (Empleados emp) throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL SP_ActualizarEmpleado(?,?,?,?,?,?)}");
            st.setInt(1, emp.getId_empleado());
            st.setString(2, emp.getNombre());
            st.setString(3, emp.getApellido());
            st.setInt(4, emp.getTelefono());
            st.setString(5, emp.getDireccion());
            st.setString(6, emp.getCedula());
            st.executeUpdate();
        } catch (SQLException e) {
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
                    prepareCall("{CALL SP_BorrarEmpleado(?)}");
            st.setInt(1, id);
            st.executeUpdate();
        } catch(SQLException e){
            System.out.println(e+ " Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
}

