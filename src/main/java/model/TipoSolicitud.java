
package model;


public class TipoSolicitud {
    private int idTipoSolicitud ; 
    private String nombre ; 
    private String descripcion ; 
    private boolean estado; 

    public TipoSolicitud() {
    }

    public TipoSolicitud(int idTipoSolicitud) {
        this.idTipoSolicitud = idTipoSolicitud;
    }

    public TipoSolicitud(int idTipoSolicitud, String nombre, String descripcion, boolean estado) {
        this.idTipoSolicitud = idTipoSolicitud;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public TipoSolicitud(String nombre, String descripcion, boolean estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getIdTipoSolicitud() {
        return idTipoSolicitud;
    }

    public void setIdTipoSolicitud(int idTipoSolicitud) {
        this.idTipoSolicitud = idTipoSolicitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "TipoSolicitud{" + "idTipoSolicitud=" + idTipoSolicitud + ", nombre=" + nombre + ", descripcion=" + descripcion + ", estado=" + estado + '}';
    }
    
    
    
}
