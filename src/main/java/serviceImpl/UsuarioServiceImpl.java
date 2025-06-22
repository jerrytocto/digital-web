
package serviceImpl;

import dao.ColaboradorDao;
import dao.UsuarioDao;
import dto.ColaboradorDTO;
import dto.UsuarioDTO;
import java.sql.SQLException;
import mapper.ColaboradorMapper;
import mapper.UsuarioMapper;
import model.Colaborador;
import model.Usuario;
import service.UsuarioService;


public class UsuarioServiceImpl implements UsuarioService{
    
    private UsuarioDao usuarioDao;
    private ColaboradorDao colaboradorDao ; 

    public UsuarioServiceImpl() {
        try {
            this.usuarioDao = new UsuarioDao();
            this.colaboradorDao = new ColaboradorDao();
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

    @Override
    public ColaboradorDTO isColaborador(int idUsuario) {
       Colaborador colaborador = colaboradorDao.buscarPorIdUsuario(idUsuario);
       
       if(colaborador != null){
           return ColaboradorMapper.toDTO(colaborador);
       }
       return null ; 
    }
    
}
