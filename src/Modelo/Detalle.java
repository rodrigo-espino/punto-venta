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
public class Detalle {
    private int ID, Cantidad, ID_V;
    private String C_producto;
    private double Precio;

    public Detalle() {
    }

    public Detalle(int ID, int Cantidad, int ID_V, String C_producto, double Precio) {
        this.ID = ID;
        this.Cantidad = Cantidad;
        this.ID_V = ID_V;
        this.C_producto = C_producto;
        this.Precio = Precio;
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

    public int getID_V() {
        return ID_V;
    }

    public void setID_V(int ID_V) {
        this.ID_V = ID_V;
    }

    public String getC_producto() {
        return C_producto;
    }

    public void setC_producto(String C_producto) {
        this.C_producto = C_producto;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }
    
}
