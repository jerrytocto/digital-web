package dao;

import com.last.digital.resources.config.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Trabajador;

public class TrabajadorDao {

    private Connection conn;

    public TrabajadorDao() throws SQLException {
        conn = ConnectionDB.getConexion();
    }

    public boolean insertar(Trabajador trabajador) throws SQLException {

        UsuarioDao usuarioDao = new UsuarioDao();
        boolean usuarioInsertado = usuarioDao.insertar(trabajador);

        if (usuarioInsertado) {
            String sql = "INSERT INTO trabajador (id_usuario, id_cliente) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, trabajador.getIdUsuario());
                stmt.setInt(2, trabajador.getCliente().getIdCliente());
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean actualizar(Trabajador trabajador) throws SQLException {
        UsuarioDao usuarioDao = new UsuarioDao();

        boolean usuarioActualizado = usuarioDao.actualizar(trabajador);

        if (usuarioActualizado) {
            String sql = "UPDATE trabajador SET id_cliente = ? WHERE id_usuario = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, trabajador.getCliente().getIdCliente());
                stmt.setInt(2, trabajador.getIdUsuario());
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

    public Trabajador buscarPorIdUsuario(int idUsuario) {
        String sql = "SELECT u.*, t.id_cliente FROM usuario u "
                + "JOIN trabajador t ON u.id_usuario = t.id_usuario "
                + "WHERE u.id_usuario = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Trabajador trabajador = new Trabajador();
                    trabajador.setIdUsuario(rs.getInt("id_usuario"));
                    trabajador.setTipoDocumento(rs.getString("tipo_documento"));
                    trabajador.setNumeroDocumento(rs.getString("num_documento"));
                    trabajador.setNombres(rs.getString("nombre"));
                    trabajador.setApellidoPaterno(rs.getString("apellido_paterno"));
                    trabajador.setApellidoMaterno(rs.getString("apellido_materno"));
                    trabajador.setTelefono(rs.getString("telefono"));
                    trabajador.setCorreo(rs.getString("correo"));
                    trabajador.setFechaRegistro(rs.getDate("fecha_registro"));
                    trabajador.setEstado(rs.getBoolean("estado"));

                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("id_cliente"));

                    //Extraer a qué cliente pertenece dicho usuario
                    ClienteDao clienteDao = new ClienteDao();
                    cliente = clienteDao.buscarPorId(rs.getInt("id_cliente"));

                    // Isertamos el trabajador al cliente
                    trabajador.setCliente(cliente);

                    return trabajador;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Trabajador> listarTodos() {
        List<Trabajador> lista = new ArrayList<>();
        String sql = "SELECT u.*, t.id_cliente "
                + "FROM usuario u "
                + "JOIN trabajador t ON u.id_usuario = t.id_usuario";

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Trabajador trabajador = new Trabajador();
                trabajador.setIdUsuario(rs.getInt("id_usuario"));
                trabajador.setTipoDocumento(rs.getString("tipo_documento"));
                trabajador.setNumeroDocumento(rs.getString("num_documento"));
                trabajador.setNombres(rs.getString("nombre"));
                trabajador.setApellidoPaterno(rs.getString("apellido_paterno"));
                trabajador.setApellidoMaterno(rs.getString("apellido_materno"));
                trabajador.setTelefono(rs.getString("telefono"));
                trabajador.setCorreo(rs.getString("correo"));
                trabajador.setFechaRegistro(rs.getDate("fecha_registro"));
                trabajador.setEstado(rs.getBoolean("estado"));

                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));

                //Extraer a qué cliente pertenece dicho usuario
                ClienteDao clienteDao = new ClienteDao();
                cliente = clienteDao.buscarPorId(rs.getInt("id_cliente"));
                trabajador.setCliente(cliente);

                lista.add(trabajador);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Trabajador> listarTrabajadorPorCliente(int idCliente) {
        List<Trabajador> lista = new ArrayList<>();
        String sql = "SELECT u.*, t.id_cliente "
                + "FROM usuario u "
                + "JOIN trabajador t ON u.id_usuario = t.id_usuario "
                + "WHERE t.id_cliente = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Trabajador trabajador = new Trabajador();
                    trabajador.setIdUsuario(rs.getInt("id_usuario"));
                    trabajador.setTipoDocumento(rs.getString("tipo_documento"));
                    trabajador.setNumeroDocumento(rs.getString("num_documento"));
                    trabajador.setNombres(rs.getString("nombre"));
                    trabajador.setApellidoPaterno(rs.getString("apellido_paterno"));
                    trabajador.setApellidoMaterno(rs.getString("apellido_materno"));
                    trabajador.setTelefono(rs.getString("telefono"));
                    trabajador.setCorreo(rs.getString("correo"));
                    trabajador.setFechaRegistro(rs.getDate("fecha_registro"));
                    trabajador.setEstado(rs.getBoolean("estado"));

                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("id_cliente"));
                    trabajador.setCliente(cliente);

                    lista.add(trabajador);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

}
