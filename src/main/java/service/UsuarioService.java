
package service;

import dto.ColaboradorDTO;
import dto.UsuarioDTO;
import model.Usuario;

public interface UsuarioService {
    
    UsuarioDTO buscarUsuarioPorUsernamePassword(String username, String password);
    
    ColaboradorDTO isColaborador(int idUsuario);
    
    UsuarioDTO insertarUsuario(UsuarioDTO usuarioDTO);
    
    
    boolean actualizarUsuario(UsuarioDTO usuarioDTO);
}
