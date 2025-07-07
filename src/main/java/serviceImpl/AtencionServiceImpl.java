package serviceImpl;

import dao.AtencionDao;
import dto.AtencionDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mapper.AtencionMapper;
import model.Atencion;
import service.AtencionService;

public class AtencionServiceImpl implements AtencionService {

    private AtencionDao atencionDao;

    public AtencionServiceImpl() {
        try {
            this.atencionDao = new AtencionDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<AtencionDTO> listAtenciones() {
        List<Atencion> listAtencion = atencionDao.listarTodos();
        List<AtencionDTO> listAtencionDTO = new ArrayList<>();
        for (Atencion atencion : listAtencion) {
            listAtencionDTO.add(AtencionMapper.toDTO(atencion));
        }
        return listAtencionDTO;
    }

    @Override
    public List<AtencionDTO> listAtencionesPorSolicitud(int idSolicitud) {
        List<Atencion> listAtencion = atencionDao.listarAtencionesPorSolicitud(idSolicitud);
        List<AtencionDTO> listAtencionDTO = new ArrayList<>();
        for (Atencion atencion : listAtencion) {
            listAtencionDTO.add(AtencionMapper.toDTO(atencion));
        }
        return listAtencionDTO;
    }

    @Override
    public List<AtencionDTO> listAtencionesPorColaborador(int idColaborador) {
        List<Atencion> listAtencion = atencionDao.listarPorColaborador(idColaborador);
        List<AtencionDTO> listAtencionDTO = new ArrayList<>();
        for (Atencion atencion : listAtencion) {
            listAtencionDTO.add(AtencionMapper.toDTO(atencion));
        }
        return listAtencionDTO;
    }

    @Override
    public AtencionDTO buscarAtencionPorId(int idAtencion) {
        AtencionDTO atencionDTO = new AtencionDTO();
        Atencion atencion = atencionDao.buscarPorId(idAtencion);

        if (atencion != null) {
            atencionDTO = AtencionMapper.toDTO(atencion);
        }
        return atencionDTO;
    }

    @Override
    public boolean insertarAtencion(AtencionDTO dto) {

        Atencion atencion = AtencionMapper.toEntity(dto);
        return atencionDao.insertarAtencion(atencion);

    }

    @Override
    public boolean actualizarAtencion(AtencionDTO atencion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<AtencionDTO> listarAtencionesPorSolicitudYColaborador(int idSolicitud, int idColaborador) {
        List<Atencion> listAtencion = atencionDao.listarAtencionesPorSolicitudYColaborador(idSolicitud, idColaborador);
        List<AtencionDTO> listAtencionDTO = new ArrayList<>();
        for (Atencion atencion : listAtencion) {
            listAtencionDTO.add(AtencionMapper.toDTO(atencion));
        }
        return listAtencionDTO;
    }

}
