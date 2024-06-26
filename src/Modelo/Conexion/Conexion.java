/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author David
 */
public class Conexion {
    private static final String URL="jdbc:sqlserver://localhost:1433;" +
            "databaseName=DB_COMPUSER;" + "integratedSecurity=true;" +
            "encrypt=true;trustServerCertificate=true";
    private static Conexion instancia=null;
    private static Connection conex=null;
    
    private Conexion(){}
    
    public Connection conectar(){
        try{
            //usando Driver y cadena de conexión para conectar BD 
            conex=DriverManager.getConnection(URL);
            System.out.println("Conexión establecida");
            return conex;
        } catch(SQLException e){
            System.out.println("Error de conexión" + e);
        }
        return conex;
    }
    
    public void cerrarConexion() throws SQLException{
        try{
            conex.close();
            System.out.println("Conexión cerrada");
        } catch(SQLException e){
            System.out.println("Error al cerrar conexión " + e);
            conex.close();
        } finally{
            conex.close();
        }
    }
    
    //Crear una unica instancia de conexión
    public static Conexion getInstance(){
        if(instancia==null){
            instancia=new Conexion();
        }
        return instancia;
    }    
}
