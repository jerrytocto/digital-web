package dto;

import java.util.Date;

public class AsignacionDTO {

    private boolean isCoordinador;
    private Date fechaAsignacion;

    private ColaboradorDTO colaborador;
    private SolicitudDTO solicitud;

    public AsignacionDTO() {
    }

    public AsignacionDTO(ColaboradorDTO colaborador, SolicitudDTO solicitud) {
        this.colaborador = colaborador;
        this.solicitud = solicitud;
    }
    
    

    public AsignacionDTO(boolean isCoordinador, Date fechaAsignacion, ColaboradorDTO colaborador, SolicitudDTO solicitud) {
        this.isCoordinador = isCoordinador;
        this.fechaAsignacion = fechaAsignacion;
        this.colaborador = colaborador;
        this.solicitud = solicitud;
    }

    public boolean isIsCoordinador() {
        return isCoordinador;
    }

    public void setIsCoordinador(boolean isCoordinador) {
        this.isCoordinador = isCoordinador;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public ColaboradorDTO getColaborador() {
        return colaborador;
    }

    public void setColaborador(ColaboradorDTO colaborador) {
        this.colaborador = colaborador;
    }

    public SolicitudDTO getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudDTO solicitud) {
        this.solicitud = solicitud;
    }
    
    

}
