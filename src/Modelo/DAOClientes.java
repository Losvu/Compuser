/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Modelo.Conexion.Conexion;
import java.sql.*;
import java.util.*;
import java.lang.String;
/**
 *
 * @author David
 */
public class DAOClientes {
    Conexion conectar=Conexion.getInstance();
    
    public List ObtenerDatos() throws SQLException{
        String proced = "SP_ListarClientes()";
        List <Map> registros= new database().Listar(proced);
        List<Clientes> cliente= new ArrayList();
        for (Map registro : registros){
            Clientes cli = new Clientes((int) registro.get("Id_cliente"),
            (String) registro.get("Nombre"),
            (String) registro.get("Apellido"),
            (String) registro.get("Direccion"),
            (String) registro.get("tipo_cli"),
            (String) registro.get("Telefono"),
            (String) registro.get("Cedula"));
            cliente.add(cli);
        }
        return cliente;
    }
    
    public int Insertar(Clientes cli) throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL SP_RegClientes(?,?,?,?,?,?)}");
            st.setString(1, cli.getNombre());
            st.setString(2, cli.getApellido());
            st.setString(3, cli.getDireccion());
            st.setString(4, cli.getTipo_cli());
            st.setString(5, cli.getTelefono());
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
            st.setString(6, cli.getTelefono());
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
    
    public List<Clientes> Buscar(Integer Id_cliente, String Nombre,
            String Apellido, String Direccion, String tipo_cli, String Telefono,
            String Cedula) throws SQLException{
        List<Clientes> clientes = new ArrayList<>();
        CallableStatement st = null;
        ResultSet rs= null;
        
        try{
            st = conectar.conectar().prepareCall("{CALL sp_BuscarClientes(?,?,?,?,?,?,?)}");
            //Establecer parámetros
            if(Id_cliente !=null){
                st.setInt(1, Id_cliente);
            } else{
                st.setNull(1, java.sql.Types.INTEGER);
            }
            st.setString(2, Nombre != null ? Nombre : "");
            st.setString(3, Apellido != null ? Apellido : "");
            st.setString(4, Direccion != null ? Direccion : "");
            st.setString(5, tipo_cli != null ? tipo_cli : "");
            st.setString(6, Telefono != null ? Telefono : "");
            st.setString(7, Cedula != null ? Cedula : "");
            
            rs = st.executeQuery();
            
            while (rs.next()){
                Clientes cliente = new Clientes(
                rs.getInt("Id_cliente"),
                rs.getString("Nombre"),
                rs.getString("Apellido"),
                rs.getString("Direccion"),
                rs.getString("tipo_cli"),
                rs.getString("Telefono"),
                rs.getString("Cedula"));
                clientes.add(cliente);
            }
        } catch (SQLException e){
            System.out.println(e + " Error ");
            return null;
        } finally{
            if (rs != null) rs.close();
            if(st != null) st.close();
            conectar.cerrarConexion();
        }
        return clientes;
    }
    
    
    //Para Maestro Detalle
    
    
    public List buscarDialogoClientes (String parametroBusqueda) throws SQLException{
        ResultSet rs=null;
        List<Map> registros=null;
        List<Clientes> clientes = new ArrayList();
        //Arreglo de elementos en el que se almacenan los datos obtenidos de BD
        List resultado=new ArrayList();
        try{
            //Llamada a procedimiento almacenado
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL buscarDialogoClientes(?)}");
            //Ejecución del procedimiento / Obtención de datos en ResultSet
            st.setString(1, parametroBusqueda);
            rs=st.executeQuery();
            resultado=OrganizarDatos(rs); //Llamada a método que organiza datos
            registros=resultado;
            //Ciclo que recorre cada registro y los agrega al arreglo cliente
            for(Map registro : registros){
                Clientes cli = new Clientes((int) registro.get("Id_cliente"),
                          (String) registro.get("Nombre"),
                          (String) registro.get("Apellido"),
                          (String) registro.get("Direccion"),
                          (String) registro.get("tipo_cli"),
                          (String) registro.get("Telefono"),
                          (String) registro.get("Cedula"));
                clientes.add(cli);
            }
        } catch(SQLException e){
            System.out.println("No se realizó la consulta: "+e.getMessage());
        }
        return clientes;
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
