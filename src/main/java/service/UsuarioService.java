
package service;

import dto.ColaboradorDTO;
import dto.UsuarioDTO;

public interface UsuarioService {
    
    UsuarioDTO buscarUsuarioPorUsernamePassword(String username, String password);
    
    ColaboradorDTO isColaborador(int idUsuario);
}
