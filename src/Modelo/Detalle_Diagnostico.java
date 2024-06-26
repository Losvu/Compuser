/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author David
 */
public class Detalle_Diagnostico {
    private int Id_detallediag;
    private float Costo;
    private int Id_diag;
    private int Id_ser;

    public Detalle_Diagnostico(int Id_detallediag, float Costo, int Id_diag, int Id_ser) {
        this.Id_detallediag = Id_detallediag;
        this.Costo = Costo;
        this.Id_diag = Id_diag;
        this.Id_ser = Id_ser;
    }

    public Detalle_Diagnostico(float Costo, int Id_diag, int Id_ser) {
        this.Costo = Costo;
        this.Id_diag = Id_diag;
        this.Id_ser = Id_ser;
    }

    public int getId_detallediag() {
        return Id_detallediag;
    }

    public void setId_detallediag(int Id_detallediag) {
        this.Id_detallediag = Id_detallediag;
    }

    public float getCosto() {
        return Costo;
    }

    public void setCosto(float Costo) {
        this.Costo = Costo;
    }

    public int getId_diag() {
        return Id_diag;
    }

    public void setId_diag(int Id_diag) {
        this.Id_diag = Id_diag;
    }

    public int getId_ser() {
        return Id_ser;
    }

    public void setId_ser(int Id_ser) {
        this.Id_ser = Id_ser;
    }
    
    
}
