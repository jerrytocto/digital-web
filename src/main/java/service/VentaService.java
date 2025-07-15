
package service;

import dto.VentaDTO;
import java.util.List;


public interface VentaService {
    
    List<VentaDTO> listVentas();

    VentaDTO buscarVentaPorId(int idVenta);

    boolean insertarVenta(VentaDTO ventaDTO);

    boolean actualizarVenta(VentaDTO ventaDTO);
    
}
