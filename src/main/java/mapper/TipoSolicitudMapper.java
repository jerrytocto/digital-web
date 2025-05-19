package mapper;


import dto.TipoSolicitudDTO;
import java.util.List;
import java.util.stream.Collectors;
import model.TipoSolicitud;

public class TipoSolicitudMapper {

    public static TipoSolicitudDTO toDTO(TipoSolicitud entity) {
        TipoSolicitudDTO dto = new TipoSolicitudDTO();
        dto.setIdTipoSolicitud(entity.getIdTipoSolicitud());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setEstado(entity.getEstado());
        return dto;
    }

    public static TipoSolicitud toEntity(TipoSolicitudDTO dto) {
        TipoSolicitud entity = new TipoSolicitud();
        entity.setIdTipoSolicitud(dto.getIdTipoSolicitud());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setEstado(dto.getEstado());
        return entity;
    }

    public static List<TipoSolicitudDTO> toDTOList(List<TipoSolicitud> entities) {
        return entities.stream()
                .map(TipoSolicitudMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<TipoSolicitud> toEntityList(List<TipoSolicitudDTO> dtos) {
        return dtos.stream()
                .map(TipoSolicitudMapper::toEntity)
                .collect(Collectors.toList());
    }
}
