/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author David
 */
public class Servicios {
    private int Id_ser;
    private String Descripcion;
    private float Costo;

    public Servicios(int Id_ser, String Descripcion, float Costo) {
        this.Id_ser = Id_ser;
        this.Descripcion = Descripcion;
        this.Costo = Costo;
    }

    public Servicios(String Descripcion, float Costo) {
        this.Descripcion = Descripcion;
        this.Costo = Costo;
    }

    public int getId_ser() {
        return Id_ser;
    }

    public void setId_ser(int Id_ser) {
        this.Id_ser = Id_ser;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public float getCosto() {
        return Costo;
    }

    public void setCosto(float Costo) {
        this.Costo = Costo;
    }
    
    
}
