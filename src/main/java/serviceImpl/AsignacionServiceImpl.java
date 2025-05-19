package serviceImpl;

import dao.AsignacionDao;
import dto.AsignacionDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mapper.AsignacionMapper;
import model.Asignacion;
import service.AsignacionService;

public class AsignacionServiceImpl implements AsignacionService {

    private AsignacionDao asignacionDao;

    public AsignacionServiceImpl() {
        try {
            this.asignacionDao = new AsignacionDao();
        } catch (SQLException e) {
            e.printStackTrace();  // manejo simple de excepción 
        }
    }

    @Override
    public List<AsignacionDTO> listAsignacion() {

        List<AsignacionDTO> listAsignacionDto = new ArrayList<>();
        List<Asignacion> listAsignacion = asignacionDao.listar();

        if (listAsignacion.size() > 0) {
            for (Asignacion asignacion : listAsignacion) {
                listAsignacionDto.add(AsignacionMapper.toDTO(asignacion));
            }
        }
        return listAsignacionDto;
    }

    @Override
    public List<AsignacionDTO> listAsignacionesPorColaborador(int idColaborador) {
        List<AsignacionDTO> listAsignacionDto = new ArrayList<>();
        List<Asignacion> listAsignacion = asignacionDao.listarAsignacionesPorColaborador(idColaborador);

        if (listAsignacion.size() > 0) {
            for (Asignacion asignacion : listAsignacion) {
                listAsignacionDto.add(AsignacionMapper.toDTO(asignacion));
            }
        }
        return listAsignacionDto;
    }

    @Override
    public List<AsignacionDTO> listAsignacionesPorColaboradorCoordinador(int idColaborador) {
        List<AsignacionDTO> listAsignacionDto = new ArrayList<>();
        List<Asignacion> listAsignacion = asignacionDao.listarAsignacionesPorColaborador(idColaborador);

        if (listAsignacion.size() > 0) {
            for (Asignacion asignacion : listAsignacion) {
                if (asignacion.getIsCoordiandor()) {
                    listAsignacionDto.add(AsignacionMapper.toDTO(asignacion));
                }
            }
        }
        return listAsignacionDto;
    }

    @Override
    public AsignacionDTO buscarAsignacionPorId(int idColaborador, int idSolicitud) {

        AsignacionDTO asignacionDTO = new AsignacionDTO();
        Asignacion asignacion = asignacionDao.buscarAsignacionPorId(idColaborador, idSolicitud);

        if (asignacion != null) {
            asignacionDTO = AsignacionMapper.toDTO(asignacion);
        }

        return asignacionDTO;
    }

    @Override
    public boolean insertarAsignacion(AsignacionDTO asignacionDto) {
        Asignacion asignacion = AsignacionMapper.toEntity(asignacionDto);
        return asignacionDao.insertar(asignacion);
    }

    @Override
    public boolean actualizarAsignacion(AsignacionDTO asignacionDto) {
        Asignacion asignacion = AsignacionMapper.toEntity(asignacionDto);
        return asignacionDao.actualizar(asignacion);
    }

}
