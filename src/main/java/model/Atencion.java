package model;

import java.sql.Timestamp;

public class Atencion {

    private int idAtencion;
    private Timestamp fechaHoraInicio;
    private Timestamp fechaHoraFin;
    private String descripcion;

    private Asignacion asignacion;

    public Atencion() {
    }

    public Atencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public Atencion(Timestamp fechaHoraInicio, Timestamp fechaHoraFin, String descripcion, Asignacion asignacion) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.descripcion = descripcion;
        this.asignacion = asignacion;
    }

    public Atencion(int idAtencion, Timestamp fechaHoraInicio, Timestamp fechaHoraFin, String descripcion, Asignacion asignacion) {
        this.idAtencion = idAtencion;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.descripcion = descripcion;
        this.asignacion = asignacion;
    }

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

    public Asignacion getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Asignacion asignacion) {
        this.asignacion = asignacion;
    }
}
