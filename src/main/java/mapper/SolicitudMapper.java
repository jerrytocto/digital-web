package mapper;


import dto.SolicitudDTO;
import model.Solicitud;

public class SolicitudMapper {

    public static SolicitudDTO toDTO(Solicitud entity) {
        if (entity == null) {
            return null;
        }

        SolicitudDTO dto = new SolicitudDTO();
        dto.setIdSolicitud(entity.getIdSolicitud());
        dto.setMotivo(entity.getMotivo());
        dto.setFechaRegistro(entity.getFechaRegistro());
        dto.setFechaCierre(entity.getFechaCierre());
        dto.setEstado(entity.getEstado());

        dto.setAplicacion(AplicacionMapper.toDTO(entity.getAplicacion()));
        dto.setTipoSolicitud(TipoSolicitudMapper.toDTO(entity.getTipoSolicitud()));
        dto.setTrabajador(TrabajadorMapper.toDTO(entity.getTrabajador()));

        return dto;
    }

    public static Solicitud toEntity(SolicitudDTO dto) {
        if (dto == null) {
            return null;
        }

        Solicitud entity = new Solicitud();
        entity.setIdSolicitud(dto.getIdSolicitud());
        entity.setMotivo(dto.getMotivo());
        entity.setFechaRegistro(dto.getFechaRegistro());
        entity.setFechaCierre(dto.getFechaCierre());
        entity.setEstado(dto.getEstado());

        entity.setAplicacion(AplicacionMapper.toEntity(dto.getAplicacion()));
        entity.setTipoSolicitud(TipoSolicitudMapper.toEntity(dto.getTipoSolicitud()));
        entity.setTrabajador(TrabajadorMapper.toEntity(dto.getTrabajador()));

        return entity;
    }
}
