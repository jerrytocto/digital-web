
package model;

import java.util.Date;


public class Solicitud {
    
    private int idSolicitud ; 
    private String  motivo ; 
    private Date fechaRegistro ; 
    private Date fechaCierre ; 
    private String estado ; 
    
    private Aplicacion aplicacion ; 
    private TipoSolicitud tipoSolicitud ; 
    private Trabajador trabajador ; 

    public Solicitud() {
    }

    public Solicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Solicitud(String motivo, Date fechaRegistro, Date fechaCierre, String estado, Aplicacion aplicacion, TipoSolicitud tipoSolicitud, Trabajador trabajador) {
        this.motivo = motivo;
        this.fechaRegistro = fechaRegistro;
        this.fechaCierre = fechaCierre;
        this.estado = estado;
        this.aplicacion = aplicacion;
        this.tipoSolicitud = tipoSolicitud;
        this.trabajador = trabajador;
    }

    public Solicitud(int idSolicitud, String motivo, Date fechaRegistro, Date fechaCierre, String estado, Aplicacion aplicacion, TipoSolicitud tipoSolicitud, Trabajador trabajador) {
        this.idSolicitud = idSolicitud;
        this.motivo = motivo;
        this.fechaRegistro = fechaRegistro;
        this.fechaCierre = fechaCierre;
        this.estado = estado;
        this.aplicacion = aplicacion;
        this.tipoSolicitud = tipoSolicitud;
        this.trabajador = trabajador;
    }

    public Solicitud(String motivo, Date fechaRegistro, Aplicacion aplicacion, TipoSolicitud tipoSolicitud, Trabajador trabajador) {
        this.motivo = motivo;
        this.fechaRegistro = fechaRegistro;
        this.aplicacion = aplicacion;
        this.tipoSolicitud = tipoSolicitud;
        this.trabajador = trabajador;
    }
    
    

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Aplicacion getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
    }

    public TipoSolicitud getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(TipoSolicitud tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    @Override
    public String toString() {
        return "Solicitud{" + "idSolicitud=" + idSolicitud + ", motivo=" + motivo + ", fechaRegistro=" + fechaRegistro + ", fechaCierre=" + fechaCierre + ", estado=" + estado + ", aplicacion=" + aplicacion + ", tipoSolicitud=" + tipoSolicitud + ", trabajador=" + trabajador + '}';
    }

    
    
    
    
}
