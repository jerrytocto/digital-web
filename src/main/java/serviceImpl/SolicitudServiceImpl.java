package serviceImpl;


import dao.SolicitudDao;
import dto.SolicitudDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mapper.SolicitudMapper;
import model.Solicitud;
import service.SolicitudService;

public class SolicitudServiceImpl implements SolicitudService {

    private SolicitudDao solicitudDao;

    public SolicitudServiceImpl() {
        try {
            this.solicitudDao = new SolicitudDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SolicitudDTO> listSolicitud() {
        List<Solicitud> listSolicitud = solicitudDao.listarTodos();
        List<SolicitudDTO> listSolicitudDTO = new ArrayList<>();
        for (Solicitud solicitud : listSolicitud) {
            listSolicitudDTO.add(SolicitudMapper.toDTO(solicitud));
        }
        return listSolicitudDTO;
    }

    @Override
    public SolicitudDTO buscarSolicitudPorId(int idSolicitud) {
        Solicitud solicitud = solicitudDao.buscarPorId(idSolicitud);
        SolicitudDTO solicitudDto = new SolicitudDTO();
        if (solicitud != null) {
            solicitudDto = SolicitudMapper.toDTO(solicitud);
        }

        return solicitudDto;
    }

    @Override
    public boolean insertarSolicitud(SolicitudDTO solicitudDto) {
        Solicitud solicitud = SolicitudMapper.toEntity(solicitudDto);
        boolean rpta = false;
        if (solicitud != null) {

            rpta = solicitudDao.insertar(solicitud);

        }
        return rpta;
    }

    @Override
    public boolean actualizarSolicitud(SolicitudDTO solicitudDto) {
        Solicitud solicitud = SolicitudMapper.toEntity(solicitudDto);

        if (solicitud != null) {

            return solicitudDao.actualizar(solicitud);

        }
        return false;
    }

    @Override
    public List<SolicitudDTO> listSolicitudesPorEstado(String estado) {
        List<Solicitud> listSolicitud = solicitudDao.listarPorEstado(estado);
        List<SolicitudDTO> listSolicitudDTO = new ArrayList<>();
        for (Solicitud solicitud : listSolicitud) {
            listSolicitudDTO.add(SolicitudMapper.toDTO(solicitud));
        }
        return listSolicitudDTO;
    }

    @Override
    public List<SolicitudDTO> listSolicitudesPorTipo(int tipoSolicitud) {
        List<Solicitud> listSolicitud = solicitudDao.listarPorTipoSolicitud(tipoSolicitud);
        List<SolicitudDTO> listSolicitudDTO = new ArrayList<>();
        for (Solicitud solicitud : listSolicitud) {
            listSolicitudDTO.add(SolicitudMapper.toDTO(solicitud));
        }
        return listSolicitudDTO;
    }

    @Override
    public List<SolicitudDTO> listSolicitudesPorTrabajador(int idTrabajador) {
        List<Solicitud> listSolicitud = solicitudDao.listarPorTrabajador(idTrabajador);
        List<SolicitudDTO> listSolicitudDTO = new ArrayList<>();
        for (Solicitud solicitud : listSolicitud) {
            listSolicitudDTO.add(SolicitudMapper.toDTO(solicitud));
        }
        return listSolicitudDTO;
    }

    @Override
    public List<SolicitudDTO> listSolicitudesPorAplicacion(int idAplicacion) {
        List<Solicitud> listSolicitud = solicitudDao.listarPorAplicacion(idAplicacion);
        List<SolicitudDTO> listSolicitudDTO = new ArrayList<>();
        for (Solicitud solicitud : listSolicitud) {
            listSolicitudDTO.add(SolicitudMapper.toDTO(solicitud));
        }
        return listSolicitudDTO;
    }

    @Override
    public List<SolicitudDTO> listaSolicitudesPorCliente(int idCliente) {
        List<Solicitud> listSolicitud = solicitudDao.listarPorCliente(idCliente);
        List<SolicitudDTO> listSolicitudDTO = new ArrayList<>();
        for (Solicitud solicitud : listSolicitud) {
            listSolicitudDTO.add(SolicitudMapper.toDTO(solicitud));
        }
        return listSolicitudDTO;
    }

    @Override
    public List<SolicitudDTO> listarSolicitudesAsignadasPorColaborador(int idColaborador) {
        List<Solicitud> listSolicitud = solicitudDao.listarSolicitudesAsignadasPorColaborador(idColaborador);
        List<SolicitudDTO> listSolicitudDTO = new ArrayList<>();
        for (Solicitud solicitud : listSolicitud) {
            listSolicitudDTO.add(SolicitudMapper.toDTO(solicitud));
        }
        return listSolicitudDTO;
    }
}
