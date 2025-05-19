package dto;

import java.sql.Timestamp;

public class AtencionDTO {

    private int idAtencion;
    private Timestamp fechaHoraInicio;
    private Timestamp fechaHoraFin;
    private String descripcion;
    private AsignacionDTO asignacion;

    public AtencionDTO() {
    }

    public AtencionDTO(int idAtencion) {
        this.idAtencion = idAtencion;
    }
    
    

    public AtencionDTO(Timestamp fechaHoraInicio, Timestamp fechaHoraFin, String descripcion, AsignacionDTO asignacion) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.descripcion = descripcion;
        this.asignacion = asignacion;
    }

    public AtencionDTO(int idAtencion, Timestamp fechaHoraInicio, Timestamp fechaHoraFin, String descripcion, AsignacionDTO asignacion) {
        this.idAtencion = idAtencion;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.descripcion = descripcion;
        this.asignacion = asignacion;
    }

    // Getters y Setters
    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public Timestamp getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Timestamp fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Timestamp getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Timestamp fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public AsignacionDTO getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(AsignacionDTO asignacion) {
        this.asignacion = asignacion;
    }
}
