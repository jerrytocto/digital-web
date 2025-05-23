
package mapper;

import dto.UsuarioDTO;
import model.Usuario;


public class UsuarioMapper {
    
    public static UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();

        // Mapeamos campos de Usuario (superclase)
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setTipoDocumento(usuario.getTipoDocumento());
        dto.setNumeroDocumento(usuario.getNumeroDocumento());
        dto.setNombres(usuario.getNombres());
        dto.setApellidoPaterno(usuario.getApellidoPaterno());
        dto.setApellidoMaterno(usuario.getApellidoMaterno());
        dto.setTelefono(usuario.getTelefono());
        dto.setCorreo(usuario.getCorreo());
        dto.setFechaRegistro(usuario.getFechaRegistro());
        dto.setEstado(usuario.getEstado());
        dto.setUsename(usuario.getUsername());
        dto.setPassword(usuario.getPassword());

        return dto;
    }

    public static Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();

        // Mapeamos campos de Usuario (superclase)
        usuario.setIdUsuario(dto.getIdUsuario());
        usuario.setTipoDocumento(dto.getTipoDocumento());
        usuario.setNumeroDocumento(dto.getNumeroDocumento());
        usuario.setNombres(dto.getNombres());
        usuario.setApellidoPaterno(dto.getApellidoPaterno());
        usuario.setApellidoMaterno(dto.getApellidoMaterno());
        usuario.setTelefono(dto.getTelefono());
        usuario.setCorreo(dto.getCorreo());
        usuario.setFechaRegistro(dto.getFechaRegistro());
        usuario.setEstado(dto.getEstado());
        usuario.setUsername(dto.getUsename());
        usuario.setPassword(dto.getPassword());
        
        return usuario;
    }
    
}
