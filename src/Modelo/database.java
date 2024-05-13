/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Modelo.Conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David
 */
public class database {
    //Crear una instancia de conexión
    //Hace llamado al metodo getInstance
    Conexion conectar=Conexion.getInstance();
    
    public List Listar(String procedimiento) throws SQLException{
        ResultSet rs=null;
        //Arreglo de elementos en el que se almacenan los datos obtenidos de BD
        List resultado=new ArrayList();
        try{
            //Llamada a procedimiento almacenado sin parametros
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL "+procedimiento+"}");
            // Ejecución del procedimiento / Obtención de datos en ResultSet
            rs=st.executeQuery();
            resultado=OrganizarDatos(rs); //Llamada a metodo que organiza datos
            
            //Conectar.cerrarConexion();
        } catch(SQLException e){
            System.out.println("No se realizo la consulta: " + e.getMessage());
        }
        return resultado;
    }
    
    private List OrganizarDatos(ResultSet rs){
        List filas=new ArrayList(); //Arreglo de elementos
        try{
            int numColumnas=rs.getMetaData().getColumnCount();
            while(rs.next()){//Recorre cada registro de la tabla
                Map<String, Object> renglon=new HashMap();
                for(int i=1; i<=numColumnas; i++){
                    //Se obtiene nombre de campo de la BD
                    String nombreCampo=rs.getMetaData().getColumnName(i);
                    Object valor=rs.getObject(nombreCampo);
                    //Por cada campo, se obtiene el nombre y el valor del mismo
                    renglon.put(nombreCampo, valor);
                }
                filas.add(renglon);//Se agrega al arreglo cada registro
            }
        } catch(SQLException e){
            System.out.println(e+ "Error ");
        }
        return filas;
    }
}
