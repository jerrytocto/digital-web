package service;

import dto.TipoSolicitudDTO;
import java.util.List;

public interface TipoSolicitudService {

    List<TipoSolicitudDTO> listTipoSolicitud();

    TipoSolicitudDTO buscarTipoSolicitudPorId(int idTipoSolicitud);

    boolean insertarTipoSolicitud(TipoSolicitudDTO trabajadorDto);

    boolean actualizarTipoSolicitud(TipoSolicitudDTO trabajadorDto);
}
