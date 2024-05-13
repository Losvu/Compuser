/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author David
 */
public class Equipos_Computarizados {
    private int Id_equipocomp;
    private String Tipo;
    private String Marca;
    private String Color;
    private String Modelo;
    private int Id_cliente;

    public Equipos_Computarizados(int Id_equipocomp, String Tipo, String Marca, String Color, String Modelo, int Id_cliente) {
        this.Id_equipocomp = Id_equipocomp;
        this.Tipo = Tipo;
        this.Marca = Marca;
        this.Color = Color;
        this.Modelo = Modelo;
        this.Id_cliente = Id_cliente;
    }

    public Equipos_Computarizados(String Tipo, String Marca, String Color, String Modelo, int Id_cliente) {
        this.Tipo = Tipo;
        this.Marca = Marca;
        this.Color = Color;
        this.Modelo = Modelo;
        this.Id_cliente = Id_cliente;
    }

    public int getId_equipocomp() {
        return Id_equipocomp;
    }

    public void setId_equipocomp(int Id_equipocomp) {
        this.Id_equipocomp = Id_equipocomp;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public int getId_cliente() {
        return Id_cliente;
    }

    public void setId_cliente(int Id_cliente) {
        this.Id_cliente = Id_cliente;
    }
    
    
    
}
