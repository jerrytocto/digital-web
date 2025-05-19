package dto;

public class TrabajadorDTO extends UsuarioDTO {

    private ClienteDTO cliente;

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
}
