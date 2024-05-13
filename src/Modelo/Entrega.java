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
public class Entrega {
    private int Id_entregaequi;
    private Date Fecha;
    private String Estado_entrega;
    private int Id_equipocomp;
    private int Id_cliente;
    private int Id_empleado;

    public Entrega(int Id_entregaequi, Date Fecha, String Estado_entrega, int Id_equipocomp, int Id_cliente, int Id_empleado) {
        this.Id_entregaequi = Id_entregaequi;
        this.Fecha = Fecha;
        this.Estado_entrega = Estado_entrega;
        this.Id_equipocomp = Id_equipocomp;
        this.Id_cliente = Id_cliente;
        this.Id_empleado = Id_empleado;
    }

    public Entrega(Date Fecha, String Estado_entrega, int Id_equipocomp, int Id_cliente, int Id_empleado) {
        this.Fecha = Fecha;
        this.Estado_entrega = Estado_entrega;
        this.Id_equipocomp = Id_equipocomp;
        this.Id_cliente = Id_cliente;
        this.Id_empleado = Id_empleado;
    }

    public int getId_entregaequi() {
        return Id_entregaequi;
    }

    public void setId_entregaequi(int Id_entregaequi) {
        this.Id_entregaequi = Id_entregaequi;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public String getEstado_entrega() {
        return Estado_entrega;
    }

    public void setEstado_entrega(String Estado_entrega) {
        this.Estado_entrega = Estado_entrega;
    }

    public int getId_equipocomp() {
        return Id_equipocomp;
    }

    public void setId_equipocomp(int Id_equipocomp) {
        this.Id_equipocomp = Id_equipocomp;
    }

    public int getId_cliente() {
        return Id_cliente;
    }

    public void setId_cliente(int Id_cliente) {
        this.Id_cliente = Id_cliente;
    }

    public int getId_empleado() {
        return Id_empleado;
    }

    public void setId_empleado(int Id_empleado) {
        this.Id_empleado = Id_empleado;
    }
    
    
}
