package dto;


import java.util.Date;

public class SolicitudDTO {

    private int idSolicitud;
    private String motivo;
    private Date fechaRegistro;
    private Date fechaCierre;
    private String estado;

    private AplicacionDTO aplicacion;
    private TipoSolicitudDTO tipoSolicitud;
    private TrabajadorDTO trabajador;

    public SolicitudDTO() {
    }

    public SolicitudDTO(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public SolicitudDTO(String motivo, Date fechaRegistro, Date fechaCierre, String estado, AplicacionDTO aplicacion, TipoSolicitudDTO tipoSolicitud, TrabajadorDTO trabajador) {
        this.motivo = motivo;
        this.fechaRegistro = fechaRegistro;
        this.fechaCierre = fechaCierre;
        this.estado = estado;
        this.aplicacion = aplicacion;
        this.tipoSolicitud = tipoSolicitud;
        this.trabajador = trabajador;
    }

    public SolicitudDTO(int idSolicitud, String motivo, Date fechaRegistro, Date fechaCierre, String estado, AplicacionDTO aplicacion, TipoSolicitudDTO tipoSolicitud, TrabajadorDTO trabajador) {
        this.idSolicitud = idSolicitud;
        this.motivo = motivo;
        this.fechaRegistro = fechaRegistro;
        this.fechaCierre = fechaCierre;
        this.estado = estado;
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

    public AplicacionDTO getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(AplicacionDTO aplicacion) {
        this.aplicacion = aplicacion;
    }

    public TipoSolicitudDTO getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(TipoSolicitudDTO tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public TrabajadorDTO getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(TrabajadorDTO trabajador) {
        this.trabajador = trabajador;
    }

    
}
