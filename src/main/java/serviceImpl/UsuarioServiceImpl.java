
package serviceImpl;

import dao.UsuarioDao;
import dto.UsuarioDTO;
import java.sql.SQLException;
import mapper.UsuarioMapper;
import model.Usuario;
import service.UsuarioService;


public class UsuarioServiceImpl implements UsuarioService{
    
    private UsuarioDao usuarioDao;

    public UsuarioServiceImpl() {
        try {
            this.usuarioDao = new UsuarioDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UsuarioDTO buscarUsuarioPorUsernamePassword(String username, String password) {
        
        Usuario usuario = usuarioDao.buscarUsuarioPorUsernamePassword(username, password);
        
        if(usuario != null){
            return UsuarioMapper.toDTO(usuario) ;
        }
        
        return null ; 
    }
    
}
