package model;

import java.util.Date;

public class Asignacion {

    private boolean isCoordiandor;
    private Date fechaAsignacion;

    private Colaborador colaborador;
    private Solicitud solicitud;

    public Asignacion(boolean isCoordiandor, Date fechaAsignacion, Colaborador colaborador, Solicitud solicitud) {
        this.isCoordiandor = isCoordiandor;
        this.fechaAsignacion = fechaAsignacion;
        this.colaborador = colaborador;
        this.solicitud = solicitud;
    }

    public Asignacion(boolean isCoordiandor, Date fechaAsignacion) {
        this.isCoordiandor = isCoordiandor;
        this.fechaAsignacion = fechaAsignacion;
    }

    public Asignacion(Colaborador colaborador, Solicitud solicitud) {
        this.colaborador = colaborador;
        this.solicitud = solicitud;
    }

    public Asignacion() {
    }

    public boolean getIsCoordiandor() {
        return isCoordiandor;
    }

    public void setIsCoordiandor(boolean isCoordiandor) {
        this.isCoordiandor = isCoordiandor;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

}
