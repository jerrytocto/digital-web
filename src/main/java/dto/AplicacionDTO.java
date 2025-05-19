package dto;

public class AplicacionDTO {

    private int idAplicacion;
    private String nombre;

    public AplicacionDTO() {
    }

    public AplicacionDTO(int idAplicacion, String nombre) {
        this.idAplicacion = idAplicacion;
        this.nombre = nombre;
    }
    public AplicacionDTO(String nombre) {
        this.nombre = nombre;
    }

    public AplicacionDTO(int idAplicacion) {
        this.idAplicacion = idAplicacion;
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
