package dao;

import com.last.digital.resources.config.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Aplicacion;
import model.Solicitud;
import model.TipoSolicitud;
import model.Trabajador;

public class SolicitudDao {

    private Connection conn;

    public SolicitudDao() throws SQLException {
        conn = ConnectionDB.getConexion();
    }

    public boolean insertar(Solicitud solicitud) {
        String sql = "INSERT INTO solicitud (id_tipo_solicitud, motivo, fecha_registro, id_trabajador, id_aplicacion) VALUES ( ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, solicitud.getTipoSolicitud().getIdTipoSolicitud());
            stmt.setString(2, solicitud.getMotivo());
            stmt.setDate(3, new java.sql.Date(solicitud.getFechaRegistro().getTime()));
            stmt.setInt(4, solicitud.getTrabajador().getIdUsuario());
            stmt.setInt(5, solicitud.getAplicacion().getIdAplicacion());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    solicitud.setIdSolicitud(rs.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Solicitud> listarTodos() {
        List<Solicitud> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitud";
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Solicitud solicitud = new Solicitud();
                solicitud.setIdSolicitud(rs.getInt("id_solicitud"));
                solicitud.setMotivo(rs.getString("motivo"));
                solicitud.setEstado(rs.getString("estado"));
                solicitud.setFechaRegistro(rs.getDate("fecha_registro"));
                solicitud.setFechaCierre(rs.getDate("fecha_cierre"));

                // Cargar Aplicacion
                Aplicacion app = new Aplicacion();
                AplicacionDao aplicacionDao = new AplicacionDao();
                app = aplicacionDao.buscarPorId(rs.getInt("id_aplicacion"));
                solicitud.setAplicacion(app);

                // Cargar TipoSolicitud
                TipoSolicitud tipo = new TipoSolicitud();
                TipoSolicitudDao tipoSolicitudDao = new TipoSolicitudDao();
                tipo = tipoSolicitudDao.buscarPorId(rs.getInt("id_tipo_solicitud"));
                solicitud.setTipoSolicitud(tipo);

                // Cargar Trabajador
                Trabajador trabajador = new Trabajador();
                TrabajadorDao trabajadorDao = new TrabajadorDao();
                trabajador = trabajadorDao.buscarPorIdUsuario(rs.getInt("id_trabajador"));
                solicitud.setTrabajador(trabajador);

                lista.add(solicitud);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Solicitud buscarPorId(int id) {
        String sql = "SELECT * FROM solicitud WHERE id_solicitud = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Solicitud solicitud = new Solicitud();
                    solicitud.setIdSolicitud(rs.getInt("id_solicitud"));
                    solicitud.setMotivo(rs.getString("motivo"));
                    solicitud.setEstado(rs.getString("estado"));
                    solicitud.setFechaRegistro(rs.getDate("fecha_registro"));
                    solicitud.setFechaCierre(rs.getDate("fecha_cierre"));

                    // Cargar Aplicacion
                    Aplicacion app = new Aplicacion();
                    AplicacionDao aplicacionDao = new AplicacionDao();
                    app = aplicacionDao.buscarPorId(rs.getInt("id_aplicacion"));
                    solicitud.setAplicacion(app);

                    // Cargar TipoSolicitud
                    TipoSolicitud tipo = new TipoSolicitud();
                    TipoSolicitudDao tipoSolicitudDao = new TipoSolicitudDao();
                    tipo = tipoSolicitudDao.buscarPorId(rs.getInt("id_tipo_solicitud"));
                    solicitud.setTipoSolicitud(tipo);

                    // Cargar Trabajador
                    Trabajador trabajador = new Trabajador();
                    TrabajadorDao trabajadorDao = new TrabajadorDao();
                    trabajador = trabajadorDao.buscarPorIdUsuario(rs.getInt("id_trabajador"));
                    solicitud.setTrabajador(trabajador);

                    return solicitud;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizar(Solicitud solicitud) {
        String sql = "UPDATE solicitud SET  id_tipo_solicitud = ?, motivo = ?, fecha_cierre = ?, estado = ?, id_aplicacion = ?, id_trabajador = ? WHERE id_solicitud = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, solicitud.getTipoSolicitud().getIdTipoSolicitud());
            stmt.setString(2, solicitud.getMotivo());
            if (solicitud.getEstado() == "Cerrado") {
                stmt.setDate(3, new java.sql.Date(solicitud.getFechaRegistro().getTime()));
            } else {
                stmt.setNull(3, java.sql.Types.DATE);
            }
            stmt.setString(4, solicitud.getEstado());
            stmt.setInt(5, solicitud.getAplicacion().getIdAplicacion());
            stmt.setInt(6, solicitud.getTrabajador().getIdUsuario());
            stmt.setInt(7, solicitud.getIdSolicitud());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Solicitud> listarPorTipoSolicitud(int idTipoSolicitud) {
        List<Solicitud> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitud WHERE id_tipo_solicitud = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTipoSolicitud);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(cargarSolicitudDesdeResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Solicitud> listarPorEstado(String estado) {
        List<Solicitud> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitud WHERE estado = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, estado);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(cargarSolicitudDesdeResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Solicitud> listarPorTrabajador(int idTrabajador) {
        List<Solicitud> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitud WHERE id_trabajador = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTrabajador);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(cargarSolicitudDesdeResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Solicitud> listarPorAplicacion(int idAplicacion) {
        List<Solicitud> lista = new ArrayList<>();
        String sql = "SELECT * FROM solicitud WHERE id_aplicacion = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAplicacion);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(cargarSolicitudDesdeResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Solicitud> listarPorCliente(int idCliente) {
        List<Solicitud> lista = new ArrayList<>();
        String sql = "    SELECT s.* FROM solicitud s"
                + " JOIN trabajador t ON s.id_trabajador = t.id_usuario"
                + "   WHERE t.id_cliente = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(cargarSolicitudDesdeResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    private Solicitud cargarSolicitudDesdeResultSet(ResultSet rs) throws SQLException {
        Solicitud solicitud = new Solicitud();
        solicitud.setIdSolicitud(rs.getInt("id_solicitud"));
        solicitud.setMotivo(rs.getString("motivo"));
        solicitud.setEstado(rs.getString("estado"));
        solicitud.setFechaRegistro(rs.getDate("fecha_registro"));
        solicitud.setFechaCierre(rs.getDate("fecha_cierre"));

        AplicacionDao aplicacionDao = new AplicacionDao();
        solicitud.setAplicacion(aplicacionDao.buscarPorId(rs.getInt("id_aplicacion")));

        TipoSolicitudDao tipoSolicitudDao = new TipoSolicitudDao();
        solicitud.setTipoSolicitud(tipoSolicitudDao.buscarPorId(rs.getInt("id_tipo_solicitud")));

        TrabajadorDao trabajadorDao = new TrabajadorDao();
        solicitud.setTrabajador(trabajadorDao.buscarPorIdUsuario(rs.getInt("id_trabajador")));

        return solicitud;
    }
}
