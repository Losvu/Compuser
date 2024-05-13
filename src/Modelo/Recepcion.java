/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.Date;
/**
 *
 * @author David
 */
public class Recepcion {
    private int Id_recepcion;
    private Date Fecha;
    private String Estado_recepcion;
    private int Id_cliente;
    private int Id_equipocomp;
    private int Id_empleado;

    public Recepcion(int Id_recepcion, Date Fecha, String Estado_recepcion, int Id_cliente, int Id_equipocomp, int Id_empleado) {
        this.Id_recepcion = Id_recepcion;
        this.Fecha = Fecha;
        this.Estado_recepcion = Estado_recepcion;
        this.Id_cliente = Id_cliente;
        this.Id_equipocomp = Id_equipocomp;
        this.Id_empleado = Id_empleado;
    }

    public Recepcion(Date Fecha, String Estado_recepcion, int Id_cliente, int Id_equipocomp, int Id_empleado) {
        this.Fecha = Fecha;
        this.Estado_recepcion = Estado_recepcion;
        this.Id_cliente = Id_cliente;
        this.Id_equipocomp = Id_equipocomp;
        this.Id_empleado = Id_empleado;
    }

    public int getId_recepcion() {
        return Id_recepcion;
    }

    public void setId_recepcion(int Id_recepcion) {
        this.Id_recepcion = Id_recepcion;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public String getEstado_recepcion() {
        return Estado_recepcion;
    }

    public void setEstado_recepcion(String Estado_recepcion) {
        this.Estado_recepcion = Estado_recepcion;
    }

    public int getId_cliente() {
        return Id_cliente;
    }

    public void setId_cliente(int Id_cliente) {
        this.Id_cliente = Id_cliente;
    }

    public int getId_equipocomp() {
        return Id_equipocomp;
    }

    public void setId_equipocomp(int Id_equipocomp) {
        this.Id_equipocomp = Id_equipocomp;
    }

    public int getId_empleado() {
        return Id_empleado;
    }

    public void setId_empleado(int Id_empleado) {
        this.Id_empleado = Id_empleado;
    }
    
    
}
