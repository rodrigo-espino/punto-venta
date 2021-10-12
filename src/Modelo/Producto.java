/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author rodrigosantacruzespino
 */
public class Producto {
   int ID, Cantidad;
   float Precio;
   String Codigo, Nombre, Proveedor;

    public Producto() {
    }

    public Producto(int ID, int Cantidad, float Precio, String Codigo, String Nombre, String Proveedor) {
        this.ID = ID;
        this.Cantidad = Cantidad;
        this.Precio = Precio;
        this.Codigo = Codigo;
        this.Nombre = Nombre;
        this.Proveedor = Proveedor;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getProveedor() {
        return Proveedor;
    }

    public void setProveedor(String Proveedor) {
        this.Proveedor = Proveedor;
    }
   
}
