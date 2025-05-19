package model;

public class Aplicacion {

    private int idAplicacion;
    private String nombre;

    public Aplicacion() {
    }

    public Aplicacion(int idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public Aplicacion(String nombre) {
        this.nombre = nombre;
    }

    public int getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(int idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    
}
