package dto;


import java.util.Date;
import model.Aplicacion;
import model.Cliente;

public class VentaDTO {

    private int idVenta;
    private Date fechaVenta;
    private Cliente cliente ; 
    private Aplicacion aplicacion;

    public VentaDTO() {
    }

    public VentaDTO(int idVenta, Date fechaVenta, Cliente cliente, Aplicacion aplicacion) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.cliente = cliente;
        this.aplicacion = aplicacion;
    }

    public VentaDTO(Date fechaVenta, Cliente cliente, Aplicacion aplicacion) {
        this.fechaVenta = fechaVenta;
        this.cliente = cliente;
        this.aplicacion = aplicacion;
    }

    public VentaDTO(int idVenta) {
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
