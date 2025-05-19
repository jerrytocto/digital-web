package service;

import dto.AsignacionDTO;
import java.util.List;

public interface AsignacionService {

    List<AsignacionDTO> listAsignacion();
    
    List<AsignacionDTO> listAsignacionesPorColaborador(int idColaborador) ;
    
    List<AsignacionDTO> listAsignacionesPorColaboradorCoordinador(int idColaborador) ;

    AsignacionDTO buscarAsignacionPorId(int idColaborador, int idSolicitud);

    boolean insertarAsignacion(AsignacionDTO asignacionDto);

    boolean actualizarAsignacion(AsignacionDTO asignacionDto);
}
