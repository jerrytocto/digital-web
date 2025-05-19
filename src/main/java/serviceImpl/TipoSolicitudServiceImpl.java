package serviceImpl;


import dao.TipoSolicitudDao;
import dto.TipoSolicitudDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mapper.TipoSolicitudMapper;
import model.TipoSolicitud;
import service.TipoSolicitudService;

public class TipoSolicitudServiceImpl implements TipoSolicitudService {

    private TipoSolicitudDao tipoSolicitudDao;

    public TipoSolicitudServiceImpl() {
        try {
            this.tipoSolicitudDao = new TipoSolicitudDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TipoSolicitudDTO> listTipoSolicitud() {
        List<TipoSolicitud> listTipoSolicitud = tipoSolicitudDao.listarTodos();
        List<TipoSolicitudDTO> listTipoSolicitudDTO = TipoSolicitudMapper.toDTOList(listTipoSolicitud);
        return listTipoSolicitudDTO;
    }

    @Override
    public TipoSolicitudDTO buscarTipoSolicitudPorId(int idTipoSolicitud) {
        TipoSolicitud tipoSolicitud = tipoSolicitudDao.buscarPorId(idTipoSolicitud);
        TipoSolicitudDTO tipoSolicitudDto = new TipoSolicitudDTO();
        if (tipoSolicitud != null) {
            tipoSolicitudDto = TipoSolicitudMapper.toDTO(tipoSolicitud);
        }

        return tipoSolicitudDto;
    }

    @Override
    public boolean insertarTipoSolicitud(TipoSolicitudDTO tipoSolicitudDto) {
        TipoSolicitud tipoSolicitud = TipoSolicitudMapper.toEntity(tipoSolicitudDto);
        boolean rpta = false;
        if (tipoSolicitud != null) {
            rpta = tipoSolicitudDao.insertar(tipoSolicitud);
        }
        return rpta;
    }

    @Override
    public boolean actualizarTipoSolicitud(TipoSolicitudDTO tipoSolicitudDto) {
        TipoSolicitud tipoSolicitud = TipoSolicitudMapper.toEntity(tipoSolicitudDto);

        if (tipoSolicitud != null) {

            return tipoSolicitudDao.actualizar(tipoSolicitud);

        }
        return false;
    }

}
