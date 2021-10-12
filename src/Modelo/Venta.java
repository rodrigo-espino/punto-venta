/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


public class Venta {
    private int ID;
    private String Cliente, Vendedor;
    private Double Total;

    public Venta() {
    }

    public Venta(int ID, String Cliente, String Vendedor, Double Total) {
        this.ID = ID;
        this.Cliente = Cliente;
        this.Vendedor = Vendedor;
        this.Total = Total;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public String getVendedor() {
        return Vendedor;
    }

    public void setVendedor(String Vendedor) {
        this.Vendedor = Vendedor;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double Total) {
        this.Total = Total;
    }
    
    
}
