package service;

import dto.AplicacionDTO;
import dto.ClienteDTO;
import dto.VentaDTO;
import java.util.List;

public interface AplicacionService {

    List<AplicacionDTO> listar();
    
    List<ClienteDTO> listarClientesPorAplicacion(int aplicacion);
    
    List<AplicacionDTO> listarAplicacionesPorCliente(int idCliente);

    AplicacionDTO obtenerPorId(int id);

    boolean registrar(AplicacionDTO dto);

    boolean actualizar(AplicacionDTO dto);

    void eliminar(int id);
}
