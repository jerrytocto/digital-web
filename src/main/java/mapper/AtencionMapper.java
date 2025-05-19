package mapper;


import dto.AtencionDTO;
import model.Asignacion;
import model.Atencion;
import model.Colaborador;
import model.Solicitud;

public class AtencionMapper {

    public static AtencionDTO toDTO(Atencion atencion) {
        AtencionDTO dto = new AtencionDTO();
        dto.setIdAtencion(atencion.getIdAtencion());
        dto.setFechaHoraInicio(atencion.getFechaHoraInicio());
        dto.setFechaHoraFin(atencion.getFechaHoraFin());
        dto.setDescripcion(atencion.getDescripcion());

        if (atencion.getAsignacion() != null) {
            dto.setAsignacion(AsignacionMapper.toDTO(atencion.getAsignacion()));
        }

        return dto;
    }

    public static Atencion toEntity(AtencionDTO dto) {
        Atencion atencion = new Atencion();
        atencion.setIdAtencion(dto.getIdAtencion());
        atencion.setFechaHoraInicio(dto.getFechaHoraInicio());
        atencion.setFechaHoraFin(dto.getFechaHoraFin());
        atencion.setDescripcion(dto.getDescripcion());

        if (dto.getAsignacion() != null) {
            Colaborador colaborador = new Colaborador () ; 
            colaborador.setIdUsuario(dto.getAsignacion().getColaborador().getIdUsuario());
            atencion.setAsignacion(new Asignacion(colaborador, new Solicitud(dto.getAsignacion().getSolicitud().getIdSolicitud())));
        }

        return atencion;
    }
}
