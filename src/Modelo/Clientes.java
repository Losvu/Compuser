/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author David
 */
public class Clientes {
    private int Id_cliente;
    private String Nombre;
    private String Apellido;
    private String Direccion;
    private String tipo_cli;
    private int Telefono;
    private String Cedula;

    public Clientes(int Id_cliente, String Nombre, String Apellido,
            String Direccion, String tipo_cli, int Telefono, String Cedula) {
        this.Id_cliente = Id_cliente;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Direccion = Direccion;
        this.tipo_cli = tipo_cli;
        this.Telefono = Telefono;
        this.Cedula = Cedula;
    }

    public Clientes(String Nombre, String Apellido, String Direccion, String tipo_cli, int Telefono, String Cedula) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Direccion = Direccion;
        this.tipo_cli = tipo_cli;
        this.Telefono = Telefono;
        this.Cedula = Cedula;
    }

    public int getId_cliente() {
        return Id_cliente;
    }

    public void setId_cliente(int ID_cliente) {
        this.Id_cliente = ID_cliente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTipo_cli() {
        return tipo_cli;
    }

    public void setTipo_cli(String tipo_cli) {
        this.tipo_cli = tipo_cli;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }
    
    
}
