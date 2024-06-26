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
    
    public List<Servicios> Buscar(Integer Id_ser, String Descripcion,
            Float Costo) throws SQLException{
        List<Servicios> servicios = new ArrayList<>();
        CallableStatement st = null;
        ResultSet rs= null;
        
        try{
            st = conectar.conectar().prepareCall("{CALL SP_BuscarServicios(?,?,?)}");
            //Establecer parámetros
            if(Id_ser !=null){
                st.setInt(1, Id_ser);
            } else{
                st.setNull(1, java.sql.Types.INTEGER);
            }
            st.setString(2, Descripcion != null ? Descripcion : "");
            if(Costo != null){
                st.setFloat(3, Costo);
            } else{
                st.setNull(3, java.sql.Types.FLOAT);
            }
            
            rs = st.executeQuery();
            
            while (rs.next()){
                Servicios servicio = new Servicios(
                rs.getInt("Id_ser"),
                rs.getString("Descripcion"),
                rs.getFloat("Costo"));
                servicios.add(servicio);
            }
        } catch (SQLException e){
            System.out.println(e + " Error ");
            return null;
        } finally{
            if (rs != null) rs.close();
            if(st != null) st.close();
            conectar.cerrarConexion();
        }
        return servicios;
    }
    
    public List buscarDialogoServicios (String parametroBusqueda) throws SQLException{
        ResultSet rs=null;
        List<Map> registros=null;
        List<Servicios> servicios = new ArrayList();
        List resultado = new ArrayList();
        try{
            CallableStatement st = conectar.conectar().
                    prepareCall("{CALL buscarDialogoServicios(?)}");
            //Ejecución del procedimiento / Obtención de datos en ResultSet
            st.setString(1,parametroBusqueda);
            rs=st.executeQuery();
            resultado=OrganizarDatos(rs);
            registros=resultado;
            for(Map registro : registros){
                Servicios ser = new Servicios((int) registro.get("Id_ser"),
                        (String) registro.get("Descripcion"),
                        (float) registro.get("Costo"));
                servicios.add(ser);
            }
        } catch(SQLException e){
            System.out.println("No se realizó la consulta: "+e.getMessage());
        }
        return servicios;
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
