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
public class DAOReportes {
    Conexion conectar=Conexion.getInstance();
    
    
    
    public void EntregasMesEspecifico(java.sql.Date fechaI, java.sql.Date fechaF) throws JRException{
        conectar.conectar();
        String path="D:\\Users\\David\\Documents\\NetBeansProjects\\"
                + "Compuser\\src\\Reportes\\EntregasSeptiembre2_Letter.jrxml";
        
        JasperReport jr;
        Map parametro=new HashMap();
        
        parametro.put("FechaInicio",fechaI); 
        parametro.put("FechaFin",fechaF);
        
        try{
        jr=JasperCompileManager.compileReport(path);
            JasperPrint mostrarReporte =JasperFillManager.fillReport
                (jr, parametro, conectar.conectar());
        
            JasperViewer.viewReport(mostrarReporte, false);
        
    }catch(JRException e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println("Error"+e);
    }
     }
    
    public void RecepcionMesEspecifico(java.sql.Date fechaIn, java.sql.Date fechaFi) throws JRException{
        conectar.conectar();
        String path="D:\\Users\\David\\Documents\\NetBeansProjects\\"
                + "Compuser\\src\\Reportes\\RecepcionesSeptiembre_Letter.jrxml";
        
        JasperReport jr;
        Map parametro=new HashMap();
        
        parametro.put("FechaInicio",fechaIn); 
        parametro.put("FechaFin",fechaFi);
        
        try{
        jr=JasperCompileManager.compileReport(path);
            JasperPrint mostrarReporte =JasperFillManager.fillReport
                (jr, parametro, conectar.conectar());
        
            JasperViewer.viewReport(mostrarReporte, false);
        
    }catch(JRException e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println("Error"+e);
    }
     }
    
    public void DiagnosticosEmpleados(int Id_empleado) throws JRException{
        //se debe pasar el numero de factura como parametro
        conectar.conectar();
        //Ruta completa de ubicación del reporte de el proyecto NetBeans
        String path="D:\\Users\\David\\Documents\\NetBeansProjects\\"
                + "Compuser\\src\\Reportes\\DiagnosticosPorEmpleados_Letter.jrxml";
        //Objeto de la clase JasperReport
        JasperReport jr;
        Map parametro = new HashMap();
        //pasa el numero de factura al nombre de parametro del reporte
        //parameterNumFact es el nombre del parametro en el reporte
        parametro.put("ParameterDiagnosticos", Id_empleado);
        
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
    
    public void ServiciosEmpleados(int Id_empleados) throws JRException{
        //se debe pasar el numero de factura como parametro
        conectar.conectar();
        //Ruta completa de ubicación del reporte de el proyecto NetBeans
        String path="D:\\Users\\David\\Documents\\NetBeansProjects\\Compuser"
                + "\\src\\Reportes\\ServiciosPorEmpl_Letter.jrxml";
        //Objeto de la clase JasperReport
        JasperReport jr;
        Map parametro = new HashMap();
        //pasa el numero de factura al nombre de parametro del reporte
        //parameterNumFact es el nombre del parametro en el reporte
        parametro.put("ParameterEmpleado", Id_empleados);
        
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
