package serviceImpl;


import dao.AplicacionDao;
import dao.ClienteDao;
import dao.VentaDao;
import dto.AplicacionDTO;
import dto.ClienteDTO;
import dto.VentaDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mapper.AplicacionMapper;
import mapper.ClienteMapper;
import mapper.VentaMapper;
import model.Aplicacion;
import model.Cliente;
import model.Venta;
import service.AplicacionService;

public class AplicacionServiceImpl implements AplicacionService {

    //private AplicacionDao aplicacionDao = new AplicacionDao();
    private AplicacionDao aplicacionDao;
    private VentaDao ventaDao ; 
    private ClienteDao clienteDao ; 

    public AplicacionServiceImpl() {
        try {
            this.aplicacionDao = new AplicacionDao();  // inicializas la dependencia
            this.ventaDao = new VentaDao();
            this.clienteDao = new ClienteDao();
        } catch (SQLException e) {
            e.printStackTrace();  // manejo simple de excepción 
        }
    }

    @Override
    public List<AplicacionDTO> listar() {
        List<Aplicacion> listaEntidades = aplicacionDao.listarTodos();
        List<AplicacionDTO> listaDTO = new ArrayList<>();
        for (Aplicacion app : listaEntidades) {
            listaDTO.add(AplicacionMapper.toDTO(app));
        }
        return listaDTO;
    }

    @Override
    public AplicacionDTO obtenerPorId(int id) {
        Aplicacion aplicacion = aplicacionDao.buscarPorId(id);
        return aplicacion != null ? AplicacionMapper.toDTO(aplicacion) : null;
    }

    @Override
    public boolean registrar(AplicacionDTO aplicacionDTO) {
        Aplicacion aplicacion = AplicacionMapper.toEntity(aplicacionDTO);
        return aplicacionDao.insertar(aplicacion);
    }

    @Override
    public boolean actualizar(AplicacionDTO aplicacionDTO) {
        Aplicacion aplicacion = AplicacionMapper.toEntity(aplicacionDTO);
        return aplicacionDao.actualizar(aplicacion);
    }

    @Override
    public void eliminar(int id) {
        
    }

    @Override
    public List<ClienteDTO> listarClientesPorAplicacion(int idAplicacion) {
        List<Venta> listVentas = ventaDao.listarVentasPorAplicacion(idAplicacion) ; 
        
        List<ClienteDTO> listClientesDTO = new ArrayList<>();
        for(Venta venta: listVentas){
            Cliente cliente = new Cliente() ; 
            cliente = clienteDao.buscarPorId(venta.getCliente().getIdCliente()) ; 
            if(cliente != null){
                listClientesDTO.add(ClienteMapper.toDTO(cliente)) ;
            }
        }
        return listClientesDTO ; 
    }

}
