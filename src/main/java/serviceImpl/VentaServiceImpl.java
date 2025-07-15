package serviceImpl;

import dao.VentaDao;
import dto.VentaDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapper.VentaMapper;
import model.Venta;
import service.VentaService;

public class VentaServiceImpl implements VentaService {

    private VentaDao ventaDao;

    public VentaServiceImpl() {
        try {
            this.ventaDao = new VentaDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<VentaDTO> listVentas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public VentaDTO buscarVentaPorId(int idVenta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean insertarVenta(VentaDTO ventaDTO) {
        Venta venta = VentaMapper.toEntity(ventaDTO);
        boolean rpta = false;
        if (venta != null) {
            rpta = ventaDao.insertar(venta);
        }
        return rpta;
    }

    @Override
    public boolean actualizarVenta(VentaDTO ventaDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
