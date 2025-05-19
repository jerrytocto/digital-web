package serviceImpl;


import dao.AplicacionDao;
import dao.ClienteDao;
import dao.TrabajadorDao;
import dao.VentaDao;
import dto.AplicacionDTO;
import dto.ClienteDTO;
import dto.TrabajadorDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mapper.AplicacionMapper;
import mapper.ClienteMapper;
import mapper.TrabajadorMapper;
import model.Aplicacion;
import model.Cliente;
import model.Trabajador;
import model.Venta;
import service.ClienteService;

public class ClienteServiceImpl implements ClienteService {

    private ClienteDao clienteDao;
    private VentaDao ventaDao;
    private AplicacionDao aplicacionDao;
    private TrabajadorDao trabajadorDao ; 

    public ClienteServiceImpl() {
        try {
            this.clienteDao = new ClienteDao();
            this.ventaDao = new VentaDao();
            this.aplicacionDao = new AplicacionDao();
            this.trabajadorDao = new TrabajadorDao();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ClienteDTO> listar() {
        List<Cliente> listaEntidades = clienteDao.listarTodos();
        List<ClienteDTO> listaDTO = new ArrayList<>();
        for (Cliente app : listaEntidades) {
            listaDTO.add(ClienteMapper.toDTO(app));
        }
        return listaDTO;
    }

    @Override
    public List<AplicacionDTO> listarAplicacionesPorCliente(int idCliente) {
        List<Venta> listVentas = ventaDao.listarVentasPorCliente(idCliente);
        List<AplicacionDTO> listAppDto = new ArrayList<>();
        if (listVentas.size() > 0) {
            for (Venta venta : listVentas) {
                Aplicacion app = new Aplicacion();
                app = aplicacionDao.buscarPorId(venta.getAplicacion().getIdAplicacion());
                if (app != null) {
                    listAppDto.add(AplicacionMapper.toDTO(app));
                }
            }
        }
        return listAppDto;
    }

    @Override
    public ClienteDTO obtenerPorId(int idCliente) {
        Cliente cliente = clienteDao.buscarPorId(idCliente);
        return cliente != null ? ClienteMapper.toDTO(cliente) : null;
    }

    @Override
    public boolean registrar(ClienteDTO dto) {
        Cliente cliente = ClienteMapper.toEntity(dto);
        return clienteDao.insertar(cliente);

    }

    @Override
    public boolean actualizar(ClienteDTO dto) {
        Cliente cliente = ClienteMapper.toEntity(dto);
        return clienteDao.actualizar(cliente);
    }

    @Override
    public List<TrabajadorDTO> listarTrabajadoresPorCliente(int idCliente) {
        
        List<Trabajador> listTrabajador =  trabajadorDao.listarTrabajadorPorCliente(idCliente);
        List<TrabajadorDTO> listTrabajadorDTO = new ArrayList<>();
        
        if(listTrabajador.size() > 0 ){
            for(Trabajador trabajador : listTrabajador){
                listTrabajadorDTO.add(TrabajadorMapper.toDTO(trabajador));
            }
        }
        return listTrabajadorDTO;
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
