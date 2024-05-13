/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author David
 */
public class Diagnostico {
    private int Id_diag;
    private String Descripción;
    private int Id_equipocomp;
    private int Id_cliente;
    private int Id_empleado;

    public Diagnostico(int Id_diag, String Descripción, int Id_equipocomp, int Id_cliente, int Id_empleado) {
        this.Id_diag = Id_diag;
        this.Descripción = Descripción;
        this.Id_equipocomp = Id_equipocomp;
        this.Id_cliente = Id_cliente;
        this.Id_empleado = Id_empleado;
    }

    public Diagnostico(String Descripción, int Id_equipocomp, int Id_cliente, int Id_empleado) {
        this.Descripción = Descripción;
        this.Id_equipocomp = Id_equipocomp;
        this.Id_cliente = Id_cliente;
        this.Id_empleado = Id_empleado;
    }

    public int getId_diag() {
        return Id_diag;
    }

    public void setId_diag(int Id_diag) {
        this.Id_diag = Id_diag;
    }

    public String getDescripción() {
        return Descripción;
    }

    public void setDescripción(String Descripción) {
        this.Descripción = Descripción;
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
