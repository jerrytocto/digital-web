package service;

import dto.AsignacionDTO;
import java.util.List;

public interface AsignacionService {

    List<AsignacionDTO> listAsignacion();
    
    List<AsignacionDTO> listAsignacionesPorColaborador(int idColaborador) ;
    
    List<AsignacionDTO> listAsignacionesPorColaboradorCoordinador(int idColaborador) ;
    
    List<AsignacionDTO> listAsignacionesPorSolicitud(int idColaborador) ;

    AsignacionDTO buscarAsignacionPorId(int idColaborador, int idSolicitud);

    boolean insertarAsignacion(AsignacionDTO asignacionDto);

    boolean actualizarAsignacion(AsignacionDTO asignacionDto);
    
    boolean verificarAsignaciones(int idSolicitud,List<Integer> idColaboradores, int idCoordinador);
    
    boolean eliminarAsignacion(int idSolicitud, int idColaborador);
    
    List<AsignacionDTO> listarTodasAsignacionesPorSolicitud(int idSolicitud) ;
    
    
    
}
