package mapper;


import dto.VentaDTO;
import model.Aplicacion;
import model.Cliente;
import model.Venta;

public class VentaMapper {

    public static Venta toEntity(VentaDTO dto) {
        Venta venta = new Venta();

        venta.setIdVenta(dto.getIdVenta());
        venta.setFechaVenta(dto.getFechaVenta());

        // Asignamos los objetos relacionados solo con el ID
        venta.setCliente(new Cliente(dto.getCliente().getIdCliente()));
        venta.setAplicacion(new Aplicacion(dto.getAplicacion().getIdAplicacion()));

        return venta;
    }

    public static VentaDTO toDTO(Venta venta) {
        VentaDTO dto = new VentaDTO();

        dto.setIdVenta(venta.getIdVenta());
        dto.setFechaVenta(venta.getFechaVenta());
        dto.setAplicacion(venta.getAplicacion());
        dto.setCliente(venta.getCliente());
        return dto;
    }
}
