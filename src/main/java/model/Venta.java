
package model;

import java.util.Date;

public class Venta {
    
    private int idVenta ; 
    private Date fechaVenta ; 
    
    private Cliente cliente ; 
    private Aplicacion aplicacion ; 

    public Venta() {
    }

    public Venta(int idVenta, Date fechaVenta, Cliente cliente, Aplicacion aplicacion) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.cliente = cliente;
        this.aplicacion = aplicacion;
    }

    public Venta(Date fechaVenta, Cliente cliente, Aplicacion aplicacion) {
        this.fechaVenta = fechaVenta;
        this.cliente = cliente;
        this.aplicacion = aplicacion;
    }

    public Venta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Aplicacion getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
    }
    
    
    
    
    
}
