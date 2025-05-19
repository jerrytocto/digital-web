package dao;

import com.last.digital.resources.config.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Asignacion;
import model.Colaborador;
import model.Solicitud;

public class AsignacionDao {

    private Connection conn;

    public AsignacionDao() throws SQLException {
        conn = ConnectionDB.getConexion();
    }

    // Insertar asignación
    // Insertar asignación
    public boolean insertar(Asignacion asignacion) {
        String sql = "INSERT INTO asignacion ( id_solicitud, id_colaborador, fecha_asignacion, coordinador) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, asignacion.getSolicitud().getIdSolicitud());
            stmt.setInt(2, asignacion.getColaborador().getIdUsuario());
            stmt.setDate(3, new java.sql.Date(asignacion.getFechaAsignacion().getTime()));
            stmt.setBoolean(4, asignacion.getIsCoordiandor());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Buscar por ID compuesto
    public Asignacion buscarAsignacionPorId(int idColaborador, int idSolicitud) {
        String sql = "SELECT * FROM asignacion WHERE id_colaborador = ? AND id_solicitud = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idColaborador);
            stmt.setInt(2, idSolicitud);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Asignacion asignacion = new Asignacion();
                    asignacion.setIsCoordiandor(rs.getBoolean("coordinador"));
                    asignacion.setFechaAsignacion(rs.getDate("fecha_asignacion"));

                    Colaborador col = new Colaborador();
                    ColaboradorDao colaboradorDao = new ColaboradorDao();
                    col = colaboradorDao.buscarPorIdUsuario(idColaborador);
                    asignacion.setColaborador(col);

                    Solicitud sol = new Solicitud();
                    SolicitudDao solicitudDao = new SolicitudDao();
                    sol = solicitudDao.buscarPorId(idSolicitud);
                    asignacion.setSolicitud(sol);

                    return asignacion;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Actualizar
    public boolean actualizar(Asignacion asignacion) {
        String sql = "UPDATE asignacion SET coordinador = ? WHERE id_colaborador = ? AND id_solicitud = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, asignacion.getIsCoordiandor());
            stmt.setInt(2, asignacion.getColaborador().getIdUsuario());
            stmt.setInt(3, asignacion.getSolicitud().getIdSolicitud());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Listar todas las asignaciones
    public List<Asignacion> listar() {
        List<Asignacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM asignacion";
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Asignacion asignacion = new Asignacion();
                asignacion.setIsCoordiandor(rs.getBoolean("coordinador"));
                asignacion.setFechaAsignacion(rs.getDate("fecha_asignacion"));

                Colaborador col = new Colaborador();
                ColaboradorDao colaboradorDao = new ColaboradorDao();
                col = colaboradorDao.buscarPorIdUsuario(rs.getInt("id_colaborador"));
                asignacion.setColaborador(col);

                Solicitud sol = new Solicitud();
                SolicitudDao solicitudDao = new SolicitudDao();
                sol = solicitudDao.buscarPorId(rs.getInt("id_solicitud"));
                asignacion.setSolicitud(sol);

                lista.add(asignacion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Asignacion> listarAsignacionesPorColaborador(int idColaborador) {
        List<Asignacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM asignacion WHERE id_colaborador = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idColaborador);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(cargarAsignacionesdeResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Asignacion> listarAsignacionesPorColaboradorCoordinador(int idColaborador) {
        List<Asignacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM asignacion WHERE id_colaborador = ? AND coordinador = 'true'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idColaborador);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(cargarAsignacionesdeResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Asignacion cargarAsignacionesdeResultSet(ResultSet rs) throws SQLException {
        Asignacion asignacion = new Asignacion();
        asignacion.setIsCoordiandor(rs.getBoolean("coordinador"));
        asignacion.setFechaAsignacion(rs.getDate("fecha_asignacion"));

        Colaborador col = new Colaborador();
        ColaboradorDao colaboradorDao = new ColaboradorDao();
        col = colaboradorDao.buscarPorIdUsuario(rs.getInt("id_colaborador"));
        asignacion.setColaborador(col);

        Solicitud sol = new Solicitud();
        SolicitudDao solicitudDao = new SolicitudDao();
        sol = solicitudDao.buscarPorId(rs.getInt("id_solicitud"));
        asignacion.setSolicitud(sol);

        return asignacion;
    }

}
