package mapper;

import dto.AplicacionDTO;
import model.Aplicacion;

public class AplicacionMapper {

    public static AplicacionDTO toDTO(Aplicacion aplicacion) {
        AplicacionDTO dto = new AplicacionDTO();
        dto.setIdAplicacion(aplicacion.getIdAplicacion());
        dto.setNombre(aplicacion.getNombre());
        return dto;
    }

    public static Aplicacion toEntity(AplicacionDTO dto) {
        Aplicacion aplicacion = new Aplicacion();

        aplicacion.setIdAplicacion(dto.getIdAplicacion());

        aplicacion.setNombre(dto.getNombre());
        return aplicacion;
    }
}
