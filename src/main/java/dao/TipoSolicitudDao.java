package dao;

import com.last.digital.resources.config.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.TipoSolicitud;

public class TipoSolicitudDao {

    private Connection conn;

    public TipoSolicitudDao() throws SQLException {
        conn = ConnectionDB.getConexion();
    }

    public boolean insertar(TipoSolicitud tipoSolicitud) {
        String sql = "INSERT INTO tipo_solicitud (nombre, descripcion, estado) "
                + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, tipoSolicitud.getNombre());
            stmt.setString(2, tipoSolicitud.getDescripcion());
            stmt.setBoolean(3, tipoSolicitud.getEstado());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    tipoSolicitud.setIdTipoSolicitud(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<TipoSolicitud> listarTodos() {
        List<TipoSolicitud> lista = new ArrayList<>();
        String sql = "SELECT * FROM tipo_solicitud";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                TipoSolicitud tipoSolicitud = new TipoSolicitud();
                tipoSolicitud.setIdTipoSolicitud(rs.getInt("id_tipo_solicitud"));
                tipoSolicitud.setNombre(rs.getString("nombre"));
                tipoSolicitud.setDescripcion(rs.getString("descripcion"));
                tipoSolicitud.setEstado(rs.getBoolean("estado"));
                lista.add(tipoSolicitud);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // BUSCAR USUARIO POR ID
    public TipoSolicitud buscarPorId(int id) {
        String sql = "SELECT * FROM tipo_solicitud WHERE id_tipo_solicitud=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                TipoSolicitud tipoSolicitud = new TipoSolicitud();
                tipoSolicitud.setIdTipoSolicitud(rs.getInt("id_tipo_solicitud"));
                tipoSolicitud.setNombre(rs.getString("nombre"));
                tipoSolicitud.setDescripcion(rs.getString("descripcion"));
                tipoSolicitud.setEstado(rs.getBoolean("estado"));
                return tipoSolicitud;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ACTUALIZAR USUARIO
    public boolean actualizar(TipoSolicitud tipoSolicitud) {
        String sql = "UPDATE tipo_solicitud SET nombre=?, descripcion=?, estado=?  WHERE id_tipo_solicitud=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipoSolicitud.getNombre());
            stmt.setString(2, tipoSolicitud.getDescripcion());
            stmt.setBoolean(3, tipoSolicitud.getEstado());
            stmt.setInt(4, tipoSolicitud.getIdTipoSolicitud());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
