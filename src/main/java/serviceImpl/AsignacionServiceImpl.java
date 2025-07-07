package serviceImpl;

import dao.AsignacionDao;
import dao.AtencionDao;
import dto.AsignacionDTO;
import dto.AtencionDTO;
import dto.ColaboradorDTO;
import dto.SolicitudDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mapper.AsignacionMapper;
import model.Asignacion;
import service.AsignacionService;
import service.AtencionService;
import service.SolicitudService;

public class AsignacionServiceImpl implements AsignacionService {

    private AsignacionDao asignacionDao;
    private AtencionService atencionService;
    private SolicitudService solicitudService;

    public AsignacionServiceImpl() {
        try {
            this.asignacionDao = new AsignacionDao();
            this.atencionService = new AtencionServiceImpl();
            this.solicitudService = new SolicitudServiceImpl();
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

    /*@Override
    public boolean insertarAsignacion(AsignacionDTO asignacionDto) {
        Asignacion asignacion = AsignacionMapper.toEntity(asignacionDto);

        SolicitudDTO solicitudPorId = solicitudService.buscarSolicitudPorId(asignacionDto.getSolicitud().getIdSolicitud());
        if (solicitudPorId != null) {
            solicitudPorId.setEstado("Pendiente");
            solicitudService.actualizarSolicitud(solicitudPorId);
        }
        return asignacionDao.insertar(asignacion);
    }*/
    @Override
    public boolean insertarAsignacion(AsignacionDTO asignacionDto) {
        int idSolicitud = asignacionDto.getSolicitud().getIdSolicitud();
        int idColab = asignacionDto.getColaborador().getIdUsuario();

        // Verificación redundante para evitar inserción duplicada
        List<AsignacionDTO> existentes = listAsignacionesPorSolicitud(idSolicitud);
        for (AsignacionDTO existente : existentes) {
            if (existente.getColaborador().getIdUsuario() == idColab) {
                System.out.println("Ya existe esta asignación: " + idSolicitud + " - " + idColab);
                return false;
            }
        }

        Asignacion asignacion = AsignacionMapper.toEntity(asignacionDto);

        SolicitudDTO solicitud = solicitudService.buscarSolicitudPorId(idSolicitud);
        if (solicitud != null) {
            solicitud.setEstado("Pendiente");
            solicitudService.actualizarSolicitud(solicitud);
        }

        return asignacionDao.insertar(asignacion);
    }

    @Override
    public boolean actualizarAsignacion(AsignacionDTO asignacionDto) {
        Asignacion asignacion = AsignacionMapper.toEntity(asignacionDto);
        return asignacionDao.actualizar(asignacion);
    }

    @Override
    public List<AsignacionDTO> listAsignacionesPorSolicitud(int idSolicitud) {
        List<AsignacionDTO> listAsignacionDto = new ArrayList<>();
        List<Asignacion> listAsignacion = asignacionDao.listarAsignacionesPorSolicitud(idSolicitud);

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
    public boolean verificarAsignaciones(int idSolicitud, List<Integer> nuevosColaboradores, int nuevoCoordinador) {
        List<AsignacionDTO> asignacionesExistentes = listarTodasAsignacionesPorSolicitud(idSolicitud);
        Map<Integer, AsignacionDTO> asignacionesMap = new HashMap<>();

        for (AsignacionDTO a : asignacionesExistentes) {
            System.out.println(" idColaborador" + a.getColaborador().getIdUsuario() + " - " + a.isIsCoordinador());
            asignacionesMap.put(a.getColaborador().getIdUsuario(), a);
        }
        System.out.println("Lista de asignaciones existentes DB: " + asignacionesExistentes);
        System.out.println("Lista de asignaciones existentes MAP: " + asignacionesMap);
        // 1. Eliminar colaboradores que ya no están
        for (AsignacionDTO asignacion : asignacionesExistentes) {
            int idUsuario = asignacion.getColaborador().getIdUsuario();

            if (!nuevosColaboradores.contains(idUsuario)) {
                List<AtencionDTO> atenciones = atencionService.listarAtencionesPorSolicitudYColaborador(idSolicitud, idUsuario);
                if (!atenciones.isEmpty()) {
                    throw new IllegalStateException("No se puede remover al colaborador con ID " + idUsuario + " porque tiene atenciones registradas.");
                } else {
                    eliminarAsignacion(idSolicitud, idUsuario);
                }
            }
        }

        // 2. Insertar nuevos o actualizar existentes
        for (int idColab : nuevosColaboradores) {
            boolean esCoordinador = (idColab == nuevoCoordinador);
            ColaboradorDTO colab = new ColaboradorDTO();
            colab.setIdUsuario(idColab);
            AsignacionDTO nuevaAsignacion = new AsignacionDTO(
                    esCoordinador,
                    new Date(),
                    colab,
                    new SolicitudDTO(idSolicitud)
            );

            AsignacionDTO asignacionExistente = asignacionesMap.get(idColab);

            if (asignacionExistente == null) {
                // No existía → insertar
                insertarAsignacion(nuevaAsignacion);
            } else {
                // Ya existe → verificar cambio de coordinador
                if (asignacionExistente.isIsCoordinador() != esCoordinador) {
                    actualizarAsignacion(nuevaAsignacion);
                }
            }
        }

        // 3. Asegurar que ningún otro quede como coordinador
        for (AsignacionDTO asignacion : asignacionesExistentes) {
            int idUsuario = asignacion.getColaborador().getIdUsuario();
            if (idUsuario != nuevoCoordinador && asignacion.isIsCoordinador() && nuevosColaboradores.contains(idUsuario)) {
                AsignacionDTO quitarCoordinador = new AsignacionDTO(false, new Date(), asignacion.getColaborador(), new SolicitudDTO(idSolicitud));
                actualizarAsignacion(quitarCoordinador);
            }
        }

        return true;
    }

    @Override
    public List<AsignacionDTO> listarTodasAsignacionesPorSolicitud(int idSolicitud) {
        List<AsignacionDTO> listAsignacionDto = new ArrayList<>();
        List<Asignacion> listAsignacion = asignacionDao.listarAsignacionesPorSolicitud(idSolicitud);

        for (Asignacion asignacion : listAsignacion) {
            listAsignacionDto.add(AsignacionMapper.toDTO(asignacion));
        }

        return listAsignacionDto;
    }

    @Override
    public boolean eliminarAsignacion(int idSolicitud, int idColaborador) {
        return asignacionDao.eliminarAsignacion(idSolicitud, idColaborador);
    }

}
