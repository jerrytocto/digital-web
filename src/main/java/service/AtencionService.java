package service;

import dto.AtencionDTO;
import java.util.List;

public interface AtencionService {

    List<AtencionDTO> listAtenciones();

    List<AtencionDTO> listAtencionesPorSolicitud(int idSolicitud);

    List<AtencionDTO> listAtencionesPorColaborador(int idColaborador);

    AtencionDTO buscarAtencionPorId(int idAtencion);

    boolean insertarAtencion(AtencionDTO atencion);

    boolean actualizarAtencion(AtencionDTO atencion);

}
