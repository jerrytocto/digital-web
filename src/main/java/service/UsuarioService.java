
package service;

import dto.UsuarioDTO;

public interface UsuarioService {
    
    UsuarioDTO buscarUsuarioPorUsernamePassword(String username, String password);
}
