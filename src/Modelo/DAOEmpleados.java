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
        String proced = "SP_ListarEmpleado()";
        List <Map> registros= new database().Listar(proced);
        List<Empleados> empleado= new ArrayList();
        for (Map registro : registros){
            Empleados emp = new Empleados((int) registro.get("Id_empleado"),
            (String) registro.get("Nombre"),
            (String) registro.get("Apellido"),
            (String) registro.get("Telefono"),
            (String) registro.get("Direccion"),
            (String) registro.get("Cedula"));
            empleado.add(emp);
        }
        return empleado;
    }
    
    public int Insertar(Empleados emp) throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL SP_RegEmpleado(?,?,?,?,?)}");
            st.setString(1, emp.getNombre());
            st.setString(2, emp.getApellido());
            st.setString(3, emp.getTelefono());
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
            st.setString(4, emp.getTelefono());
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
    
    public List<Empleados> Buscar(Integer Id_empleado, String Nombre,
            String Apellido, String Telefono, String Direccion,
            String Cedula) throws SQLException{
        List<Empleados> empleados = new ArrayList<>();
        CallableStatement st = null;
        ResultSet rs= null;
        
        try{
            st = conectar.conectar().prepareCall("{CALL SP_BuscarEmpleados(?,?,?,?,?,?)}");
            //Establecer parámetros
            if(Id_empleado !=null){
                st.setInt(1, Id_empleado);
            } else{
                st.setNull(1, java.sql.Types.INTEGER);
            }
            st.setString(2, Nombre != null ? Nombre : "");
            st.setString(3, Apellido != null ? Apellido : "");
            st.setString(4, Telefono != null ? Telefono : "");
            st.setString(5, Direccion != null ? Direccion : "");
            st.setString(6, Cedula != null ? Cedula : "");
            
            rs = st.executeQuery();
            
            while (rs.next()){
                Empleados empleado = new Empleados(
                rs.getInt("Id_empleado"),
                rs.getString("Nombre"),
                rs.getString("Apellido"),
                rs.getString("Telefono"),
                rs.getString("Direccion"),
                rs.getString("Cedula"));
                empleados.add(empleado);
            }
        } catch (SQLException e){
            System.out.println(e + " Error ");
            return null;
        } finally{
            if (rs != null) rs.close();
            if(st != null) st.close();
            conectar.cerrarConexion();
        }
        return empleados;
    }
    
    public List buscarDialogoEmpleado (String parametroBusqueda) throws SQLException{
        ResultSet rs=null;
        List<Map> registros=null;
        List<Empleados> empleados = new ArrayList();
        //Arreglo de elementos en el que se almacenan los datos obtenidos de BD
        List resultado=new ArrayList();
        try{
            //Llamada a procedimiento almacenado
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL buscarDialogoEmpleado(?)}");
            //Ejecución del procedimiento / Obtención de datos en ResultSet
            st.setString(1, parametroBusqueda);
            rs=st.executeQuery();
            resultado=OrganizarDatos(rs); //Llamada a método que organiza datos
            registros=resultado;
            //Ciclo que recorre cada registro y los agrega al arreglo cliente
            for(Map registro : registros){
                Empleados emp = new Empleados((int) registro.get("Id_empleado"),
                          (String) registro.get("Nombre"),
                          (String) registro.get("Apellido"),
                          (String) registro.get("Telefono"),
                          (String) registro.get("Direccion"),
                          (String) registro.get("Cedula"));
                empleados.add(emp);
            }
        } catch(SQLException e){
            System.out.println("No se realizó la consulta: "+e.getMessage());
        }
        return empleados;
    }
    
    private List OrganizarDatos(ResultSet rs){
        List filas=new ArrayList(); //Arreglo de elementos
        try{
            int numColumnas=rs.getMetaData().getColumnCount();
            while(rs.next()){ //Recorre cada registro de la tabla
                Map<String, Object> renglon=new HashMap();
                for(int i=1; i<=numColumnas; i++){
                    //Se obtiene nombre de campo en la BD
                    String nombreCampo=rs.getMetaData().getColumnName(i);
                    Object valor=rs.getObject(nombreCampo);
                    // por cada campo, se obtiene el nombre y el valor del mismo
                    renglon.put(nombreCampo, valor);
                }
                filas.add(renglon); //Se agrega al arreglo cada registro
            }
        } catch(SQLException e){
            System.out.println(e+ "Error ");
        }
        return filas;
    }
}

