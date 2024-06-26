/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Modelo.Conexion.Conexion;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author David
 */
public class DAODiagnostico {
    Conexion conectar=Conexion.getInstance();
    
    public List ObtenerDatos() throws SQLException{
        String proced = "SP_ListarDiagnosticos()";
        List <Map> registros= new database().Listar(proced);
        List<Diagnostico> diagnostico= new ArrayList();
        for (Map registro : registros){
            Diagnostico diag = new Diagnostico((int) registro.get("Id_diag"),
            (String) registro.get("Descripción"),
            (int) registro.get("Id_equipocomp"),
            (int) registro.get("Id_cliente"),
            (int) registro.get("Id_empleado"));
            diagnostico.add(diag);
        }
        return diagnostico;
    }
    
    public int Insertar(Diagnostico diag) throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL SP_RegDiagnostico(?,?,?,?)}");
            st.setString(1, diag.getDescripción());
            st.setInt(2, diag.getId_equipocomp());
            st.setInt(3, diag.getId_cliente());
            st.setInt(4, diag.getId_empleado());
            st.executeUpdate();
        } catch(SQLException e){
            System.out.println(e + " Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
    
    public int Actualizar(Diagnostico diag) throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL SP_ActualizarDiagnostico(?,?,?,?,?)}");
            st.setInt(1, diag.getId_diag());
            st.setString(2, diag.getDescripción());
            st.setInt(3, diag.getId_equipocomp());
            st.setInt(4, diag.getId_cliente());
            st.setInt(5, diag.getId_empleado());
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
                    prepareCall("{CALL SP_BorrarDiagnostico(?)}");
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
    
    public int insertarDiagnostico(Diagnostico diag) throws SQLException{
        try{
        CallableStatement st = conectar.conectar().
                prepareCall("{CALL insertarDiagnostico(?,?,?,?)}");
        st.setString(1, diag.getDescripción());
        st.setInt(2,diag.getId_equipocomp());
        st.setInt(3,diag.getId_cliente());
        st.setInt(4, diag.getId_empleado());
        st.executeUpdate();
    } catch(SQLException e){
        System.out.println(e+ " Error ");
        conectar.cerrarConexion();
        return -1;
    }
        conectar.cerrarConexion();
        return 0;
    }
    
    public int obtenerUltimoIDDiagnostico() throws SQLException{
        int IDDiagnostico = 0;
        ResultSet rs=null;
        try{
            //Llamada a procedimiento almacenado sin parámetros
            CallableStatement st = conectar.conectar().
                    prepareCall("{CALL ObtenerUltimoIDDiagnostico()}");
            //Ejecución del procedimiento / Obtención de datos
            rs = st.executeQuery();
            if(rs.next()){
                IDDiagnostico = rs.getInt(1);
            }
            return IDDiagnostico;
            
            //conectar.cerrarConexion();
        } catch(SQLException e){
            System.out.println("No se realizó la consulta: "+ e.getMessage());
            return 0;
        }
    }
    
    public void reporteFactura(int numFac) throws JRException{
        //se debe pasar el numero de factura como parametro
        conectar.conectar();
        //Ruta completa de ubicación del reporte de el proyecto NetBeans
        String path="D:\\Users\\David\\Documents\\NetBeansProjects\\VentaMD\\"
                + "src\\reportes\\Fact.jrxml";
        //Objeto de la clase JasperReport
        JasperReport jr;
        Map parametro = new HashMap();
        //pasa el numero de factura al nombre de parametro del reporte
        //parameterNumFact es el nombre del paramretro en el reporte
        parametro.put("ParameterNumFact", numFac);
        
        try{
            jr = JasperCompileManager.compileReport(path);
            //Me pasa el parametro del reporte al mostrarlo
            JasperPrint mostrarReporte = JasperFillManager.fillReport
                (jr, parametro, conectar.conectar());
            
            JasperViewer.viewReport(mostrarReporte, false);
        } catch(JRException e){
            JOptionPane.showMessageDialog(null,e);
            System.out.println("Error "+e);
        }
    }
    
}
