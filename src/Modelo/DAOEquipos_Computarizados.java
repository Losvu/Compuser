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
    
    public List<Equipos_Computarizados> Buscar(Integer Id_equipocomp, String Tipo,
            String Marca, String Color, String Modelo,
            Integer Id_cliente) throws SQLException{
        List<Equipos_Computarizados> equipos = new ArrayList<>();
        CallableStatement st = null;
        ResultSet rs= null;
        
        try{
            st = conectar.conectar().prepareCall("{CALL SP_BuscarEquipo(?,?,?,?,?,?)}");
            //Establecer parámetros
            if(Id_equipocomp !=null){
                st.setInt(1, Id_equipocomp);
            } else{
                st.setNull(1, java.sql.Types.INTEGER);
            }
            st.setString(2, Tipo != null ? Tipo : "");
            st.setString(3, Marca != null ? Marca : "");
            st.setString(4, Color != null ? Color : "");
            st.setString(5, Modelo != null ? Modelo : "");
            if(Id_cliente != null){
                st.setInt(6, Id_cliente);
            } else{
                st.setNull(6, java.sql.Types.INTEGER);
            }
            
            rs = st.executeQuery();
            
            while (rs.next()){
                Equipos_Computarizados equipo = new Equipos_Computarizados(
                rs.getInt("Id_equipocomp"),
                rs.getString("Tipo"),
                rs.getString("Marca"),
                rs.getString("Color"),
                rs.getString("Modelo"),
                rs.getInt("Id_cliente"));
                equipos.add(equipo);
            }
        } catch (SQLException e){
            System.out.println(e + " Error ");
            return null;
        } finally{
            if (rs != null) rs.close();
            if(st != null) st.close();
            conectar.cerrarConexion();
        }
        return equipos;
    }
    
    
    //Para Maestro Detalle
    
    
    public List buscarDialogoEquipo (String parametroBusqueda) throws SQLException{
        ResultSet rs=null;
        List<Map> registros=null;
        List<Equipos_Computarizados> equipos = new ArrayList();
        //Arreglo de elementos en el que se almacenan los datos obtenidos de BD
        List resultado=new ArrayList();
        try{
            //Llamada a procedimiento almacenado
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL buscarDialogoEquipo(?)}");
            //Ejecución del procedimiento / Obtención de datos en ResultSet
            st.setString(1, parametroBusqueda);
            rs=st.executeQuery();
            resultado=OrganizarDatos(rs); //Llamada a método que organiza datos
            registros=resultado;
            //Ciclo que recorre cada registro y los agrega al arreglo cliente
            for(Map registro : registros){
                Equipos_Computarizados equi = new Equipos_Computarizados(
                          (int) registro.get("Id_equipocomp"),
                          (String) registro.get("Tipo"),
                          (String) registro.get("Marca"),
                          (String) registro.get("Color"),
                          (String) registro.get("Modelo"),
                          (int) registro.get("Id_cliente"));
                equipos.add(equi);
            }
        } catch(SQLException e){
            System.out.println("No se realizó la consulta: "+e.getMessage());
        }
        return equipos;
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
