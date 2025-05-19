package service;

import dto.AplicacionDTO;
import dto.ClienteDTO;
import dto.TrabajadorDTO;
import java.util.List;

public interface ClienteService {

    List<ClienteDTO> listar();

    List<AplicacionDTO> listarAplicacionesPorCliente(int idCliente);
    
    List<TrabajadorDTO> listarTrabajadoresPorCliente (int idCliente) ;

    ClienteDTO obtenerPorId(int idCliente);

    boolean registrar(ClienteDTO dto);

    boolean actualizar(ClienteDTO dto);

    void eliminar(int id);
}
