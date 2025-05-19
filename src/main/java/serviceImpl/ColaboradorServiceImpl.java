package serviceImpl;


import dao.ColaboradorDao;
import dto.ColaboradorDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapper.ColaboradorMapper;
import model.Colaborador;
import service.ColaboradorService;

public class ColaboradorServiceImpl implements ColaboradorService {

    private ColaboradorDao colaboradorDao;

    public ColaboradorServiceImpl() {
        try {
            this.colaboradorDao = new ColaboradorDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ColaboradorDTO> listColaborador() {
        List<Colaborador> listColaborador = colaboradorDao.listarTodos();
        List<ColaboradorDTO> listColaboradorDTO = new ArrayList<>();
        for (Colaborador colaborador : listColaborador) {
            listColaboradorDTO.add(ColaboradorMapper.toDTO(colaborador));
        }
        return listColaboradorDTO;
    }

    @Override
    public ColaboradorDTO buscarColaboradorPorId(int idColaborador) {
        Colaborador colaborador = colaboradorDao.buscarPorIdUsuario(idColaborador);
        ColaboradorDTO colaboradorDto = new ColaboradorDTO();
        if (colaborador != null) {
            colaboradorDto = ColaboradorMapper.toDTO(colaborador);
        }

        return colaboradorDto;
    }

    @Override
    public boolean insertarColaborador(ColaboradorDTO colaboradorDto) {
        Colaborador colaborador = ColaboradorMapper.toEntity(colaboradorDto);
        boolean rpta = false;
        if (colaborador != null) {
            try {
                rpta = colaboradorDao.insertar(colaborador);
            } catch (SQLException ex) {
                Logger.getLogger(ColaboradorServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rpta;
    }

    @Override
    public boolean actualizarColaborador(ColaboradorDTO colaboradorDto) {
        Colaborador colaborador = ColaboradorMapper.toEntity(colaboradorDto);

        if (colaborador != null) {
            try {
                return colaboradorDao.actualizar(colaborador);
            } catch (SQLException ex) {
                Logger.getLogger(ColaboradorServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

}
