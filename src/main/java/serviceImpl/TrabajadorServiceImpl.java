package serviceImpl;


import dao.TrabajadorDao;
import dto.TrabajadorDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapper.TrabajadorMapper;
import model.Trabajador;
import service.TrabajadorService;

public class TrabajadorServiceImpl implements TrabajadorService {

    private TrabajadorDao trabajadorDao;

    public TrabajadorServiceImpl() {
        try {
            this.trabajadorDao = new TrabajadorDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TrabajadorDTO> listTrabajador() {
        List<Trabajador> listTrabajador = trabajadorDao.listarTodos();
        List<TrabajadorDTO> listTrabajadorDTO = new ArrayList<>();
        for (Trabajador trabajador : listTrabajador) {
            listTrabajadorDTO.add(TrabajadorMapper.toDTO(trabajador));
        }
        return listTrabajadorDTO;
    }

    @Override
    public TrabajadorDTO buscarTrabajadorPorId(int idTrabajador) {
        Trabajador trabajador = trabajadorDao.buscarPorIdUsuario(idTrabajador);
        TrabajadorDTO trabajadorDto = new TrabajadorDTO();
        if (trabajador != null) {
            trabajadorDto = TrabajadorMapper.toDTO(trabajador);
        }

        return trabajadorDto;
    }

    @Override
    public boolean insertarTrabajador(TrabajadorDTO trabajadorDto) {

        Trabajador trabajador = TrabajadorMapper.toEntity(trabajadorDto);
        boolean rpta = false;
        if (trabajador != null) {
            try {
                rpta = trabajadorDao.insertar(trabajador);
            } catch (SQLException ex) {
                Logger.getLogger(TrabajadorServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rpta;
    }

    @Override
    public boolean actualizarTrabajador(TrabajadorDTO trabajadorDto) {
        
        Trabajador trabajador = TrabajadorMapper.toEntity(trabajadorDto);
        
        if(trabajador != null){
            try {
                return trabajadorDao.actualizar(trabajador);
            } catch (SQLException ex) {
                Logger.getLogger(TrabajadorServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       return true ;
    }
}
