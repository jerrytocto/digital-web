
package dto;

import java.util.Date;

public class ClienteDTO {
       private int idCliente ; 
   private String tipoCliente ; 
   private String razonSocial ; 
   private String tipoDocumento ;
   private String numeroDocumento ;
   private String telefono ;
   private String correo ;
   private String direccion ; 
   private Date fechaRegistro ; 

    public ClienteDTO() {
    }

    public ClienteDTO(int idCliente) {
        this.idCliente = idCliente;
    }

    public ClienteDTO(int idCliente,String tipoCliente, String razonSocial, String tipoDocumento, String numeroDocumento, String telefono, String correo, String direccion, Date fechaRegistro) {
        this.idCliente = idCliente;
        this.tipoCliente = tipoCliente;
        this.razonSocial = razonSocial;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
    }
   public ClienteDTO(String tipoCliente, String razonSocial, String tipoDocumento, String numeroDocumento, String telefono, String correo, String direccion, Date fechaRegistro) {
        this.idCliente = idCliente;
        this.tipoCliente = tipoCliente;
        this.razonSocial = razonSocial;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
   
   
}
