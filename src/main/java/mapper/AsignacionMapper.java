package mapper;


import dto.AsignacionDTO;
import dto.SolicitudDTO;
import model.Asignacion;
import model.Solicitud;

public class AsignacionMapper {

    public static AsignacionDTO toDTO(Asignacion asignacion) {
        AsignacionDTO dto = new AsignacionDTO();
        dto.setIsCoordinador(asignacion.getIsCoordiandor());
        dto.setFechaAsignacion(asignacion.getFechaAsignacion());

        if (asignacion.getColaborador() != null) {
            dto.setColaborador(ColaboradorMapper.toDTO(asignacion.getColaborador()));
        }

        if (asignacion.getSolicitud() != null) {
            dto.setSolicitud(SolicitudMapper.toDTO(asignacion.getSolicitud()));
            
        }

        return dto;
    }

    public static Asignacion toEntity(AsignacionDTO dto) {
        Asignacion asignacion = new Asignacion();
        asignacion.setIsCoordiandor(dto.isIsCoordinador());
        asignacion.setFechaAsignacion(dto.getFechaAsignacion());

        if (dto.getColaborador() != null) {
            asignacion.setColaborador(ColaboradorMapper.toEntity(dto.getColaborador()));
        }

        if (dto.getSolicitud() != null) {
            
            asignacion.setSolicitud( new Solicitud(dto.getSolicitud().getIdSolicitud()));
        }

        return asignacion;
    }
}
