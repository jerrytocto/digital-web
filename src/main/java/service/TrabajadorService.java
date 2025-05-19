package service;

import dto.TrabajadorDTO;
import java.util.List;

public interface TrabajadorService {

    List<TrabajadorDTO> listTrabajador();

    TrabajadorDTO buscarTrabajadorPorId(int idTrabajador);

    boolean insertarTrabajador(TrabajadorDTO trabajadorDto);

    boolean actualizarTrabajador(TrabajadorDTO trabajadorDto);
    
    
    
}
