package mapper;


import dto.TrabajadorDTO;
import model.Trabajador;

public class TrabajadorMapper {

    public static TrabajadorDTO toDTO(Trabajador trabajador) {
        TrabajadorDTO dto = new TrabajadorDTO();

        // Mapeamos campos de Usuario (superclase)
        dto.setIdUsuario(trabajador.getIdUsuario());
        dto.setTipoDocumento(trabajador.getTipoDocumento());
        dto.setNumeroDocumento(trabajador.getNumeroDocumento());
        dto.setNombres(trabajador.getNombres());
        dto.setApellidoPaterno(trabajador.getApellidoPaterno());
        dto.setApellidoMaterno(trabajador.getApellidoMaterno());
        dto.setTelefono(trabajador.getTelefono());
        dto.setCorreo(trabajador.getCorreo());
        dto.setFechaRegistro(trabajador.getFechaRegistro());
        dto.setEstado(trabajador.getEstado());

        // Mapeamos Cliente
        if (trabajador.getCliente() != null) {
            dto.setCliente(ClienteMapper.toDTO(trabajador.getCliente()));
        }

        return dto;
    }

    public static Trabajador toEntity(TrabajadorDTO dto) {
        Trabajador trabajador = new Trabajador();

        // Mapeamos campos de Usuario (superclase)
        trabajador.setIdUsuario(dto.getIdUsuario());
        trabajador.setTipoDocumento(dto.getTipoDocumento());
        trabajador.setNumeroDocumento(dto.getNumeroDocumento());
        trabajador.setNombres(dto.getNombres());
        trabajador.setApellidoPaterno(dto.getApellidoPaterno());
        trabajador.setApellidoMaterno(dto.getApellidoMaterno());
        trabajador.setTelefono(dto.getTelefono());
        trabajador.setCorreo(dto.getCorreo());
        trabajador.setFechaRegistro(dto.getFechaRegistro());
        trabajador.setEstado(dto.getEstado());

        // Mapeamos Cliente
        if (dto.getCliente() != null) {
            trabajador.setCliente(ClienteMapper.toEntity(dto.getCliente()));
        }

        return trabajador;
    }
}
