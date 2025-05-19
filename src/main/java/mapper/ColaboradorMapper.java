package mapper;

import dto.ColaboradorDTO;
import model.Colaborador;

public class ColaboradorMapper {

    public static ColaboradorDTO toDTO(Colaborador colaborador) {
        ColaboradorDTO dto = new ColaboradorDTO();

        // Mapeamos campos de Usuario (superclase)
        dto.setIdUsuario(colaborador.getIdUsuario());
        dto.setTipoDocumento(colaborador.getTipoDocumento());
        dto.setNumeroDocumento(colaborador.getNumeroDocumento());
        dto.setNombres(colaborador.getNombres());
        dto.setApellidoPaterno(colaborador.getApellidoPaterno());
        dto.setApellidoMaterno(colaborador.getApellidoMaterno());
        dto.setTelefono(colaborador.getTelefono());
        dto.setCorreo(colaborador.getCorreo());
        dto.setFechaRegistro(colaborador.getFechaRegistro());
        dto.setEstado(colaborador.getEstado());
        dto.setTipoColaborador(colaborador.getTipoColaborador());

        return dto;
    }

    public static Colaborador toEntity(ColaboradorDTO dto) {
        Colaborador trabajador = new Colaborador();

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
        trabajador.setTipoColaborador(dto.getTipoColaborador());
        return trabajador;
    }
}
