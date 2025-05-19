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
import model.Atencion;
import model.Colaborador;
import model.Solicitud;

public class AtencionDao {

    private Connection conn;

    public AtencionDao() throws SQLException {
        conn = ConnectionDB.getConexion();
    }

    public boolean insertarAtencion(Atencion atencion) {
        String sql = "INSERT INTO atencion (id_colaborador, id_solicitud, hora_inicio, hora_fin, descripcion) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, atencion.getAsignacion().getColaborador().getIdUsuario());
            stmt.setInt(2, atencion.getAsignacion().getSolicitud().getIdSolicitud());
            stmt.setTimestamp(3, atencion.getFechaHoraInicio());
            stmt.setTimestamp(4, atencion.getFechaHoraFin());
            stmt.setString(5, atencion.getDescripcion());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    atencion.setIdAtencion(rs.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Atencion buscarPorId(int idAtencion) {
        String sql = "SELECT * FROM atencion WHERE id_atencion = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAtencion);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Atencion atencion = new Atencion();
                atencion.setIdAtencion(rs.getInt("id_atencion"));
                atencion.setFechaHoraInicio(rs.getTimestamp("hora_inicio"));
                atencion.setFechaHoraFin(rs.getTimestamp("hora_fin"));
                atencion.setDescripcion(rs.getString("descripcion"));

                Asignacion asignacion = new Asignacion();

                Colaborador colaborador = new Colaborador();
                ColaboradorDao colaboradorDao = new ColaboradorDao();
                colaborador = colaboradorDao.buscarPorIdUsuario(rs.getInt("id_colaborador"));

                Solicitud solicitud = new Solicitud();
                SolicitudDao solicitudDao = new SolicitudDao();
                solicitud = solicitudDao.buscarPorId(rs.getInt("id_solicitud"));

                asignacion.setColaborador(colaborador);
                asignacion.setSolicitud(solicitud);

                atencion.setAsignacion(asignacion);

                return atencion;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Atencion> listarPorColaborador(int idColaborador) {
        List<Atencion> lista = new ArrayList<>();
        String sql = "SELECT * FROM atencion a "
                + "INNER JOIN asignacion ag ON a.id_colaborador = ag.id_colaborador AND a.id_solicitud = ag.id_solicitud "
                + "WHERE ag.id_colaborador = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idColaborador);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Atencion atencion = new Atencion();
                atencion.setIdAtencion(rs.getInt("id_atencion"));
                atencion.setFechaHoraInicio(rs.getTimestamp("hora_inicio"));
                atencion.setFechaHoraFin(rs.getTimestamp("hora_fin"));
                atencion.setDescripcion(rs.getString("descripcion"));

                // Cargar asignación mínima
                Asignacion asignacion = new Asignacion();

                Colaborador colaborador = new Colaborador();
                ColaboradorDao colaboradorDao = new ColaboradorDao();
                colaborador = colaboradorDao.buscarPorIdUsuario(rs.getInt("id_colaborador"));

                Solicitud solicitud = new Solicitud();
                SolicitudDao solicitudDao = new SolicitudDao();
                solicitud = solicitudDao.buscarPorId(rs.getInt("id_solicitud"));

                asignacion.setColaborador(colaborador);
                asignacion.setSolicitud(solicitud);

                atencion.setAsignacion(asignacion);

                lista.add(atencion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Atencion> listarAtencionesPorSolicitud(int idSolicitud) {

        List<Atencion> lista = new ArrayList<>();
        String sql = "SELECT * FROM atencion a "
                + "INNER JOIN asignacion ag ON a.id_colaborador = ag.id_colaborador AND a.id_solicitud = ag.id_solicitud "
                + "WHERE ag.id_solicitud = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idSolicitud);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Atencion atencion = new Atencion();
                atencion.setIdAtencion(rs.getInt("id_atencion"));
                atencion.setFechaHoraInicio(rs.getTimestamp("hora_inicio"));
                atencion.setFechaHoraFin(rs.getTimestamp("hora_fin"));
                atencion.setDescripcion(rs.getString("descripcion"));

                // Cargar asignación mínima
                Asignacion asignacion = new Asignacion();

                Colaborador colaborador = new Colaborador();
                ColaboradorDao colaboradorDao = new ColaboradorDao();
                colaborador = colaboradorDao.buscarPorIdUsuario(rs.getInt("id_colaborador"));

                Solicitud solicitud = new Solicitud();
                SolicitudDao solicitudDao = new SolicitudDao();
                solicitud = solicitudDao.buscarPorId(rs.getInt("id_solicitud"));

                asignacion.setColaborador(colaborador);
                asignacion.setSolicitud(solicitud);

                atencion.setAsignacion(asignacion);

                lista.add(atencion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Atencion> listarTodos() {
        List<Atencion> lista = new ArrayList<>();
        String sql = "SELECT * FROM atencion";
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Atencion atencion = new Atencion();
                atencion.setIdAtencion(rs.getInt("id_atencion"));
                atencion.setFechaHoraInicio(rs.getTimestamp("hora_inicio"));
                atencion.setFechaHoraFin(rs.getTimestamp("hora_fin"));
                atencion.setDescripcion(rs.getString("descripcion"));

                // Cargar asignación mínima
                Asignacion asignacion = new Asignacion();

                Colaborador colaborador = new Colaborador();
                ColaboradorDao colaboradorDao = new ColaboradorDao();
                colaborador = colaboradorDao.buscarPorIdUsuario(rs.getInt("id_colaborador"));

                Solicitud solicitud = new Solicitud();
                SolicitudDao solicitudDao = new SolicitudDao();
                solicitud = solicitudDao.buscarPorId(rs.getInt("id_solicitud"));

                asignacion.setColaborador(colaborador);
                asignacion.setSolicitud(solicitud);

                atencion.setAsignacion(asignacion);

                lista.add(atencion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
