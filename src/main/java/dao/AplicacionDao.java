package dao;

import com.last.digital.resources.config.ConnectionDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Aplicacion;
import model.Cliente;

public class AplicacionDao {

    private Connection conn;

    public AplicacionDao() throws SQLException {
        conn = ConnectionDB.getConexion();
    }

    public boolean insertar(Aplicacion aplicacion) {
        String sql = "INSERT INTO aplicacion (nombre) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aplicacion.getNombre());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Aplicacion> listarTodos() {
        List<Aplicacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM aplicacion";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Aplicacion aplicacion = new Aplicacion();
                aplicacion.setIdAplicacion(rs.getInt("id_aplicacion"));
                aplicacion.setNombre(rs.getString("nombre"));
                lista.add(aplicacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Aplicacion buscarPorId(int id) {
        String sql = "SELECT * FROM aplicacion WHERE id_aplicacion = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Aplicacion aplicacion = new Aplicacion();
                    aplicacion.setIdAplicacion(rs.getInt("id_aplicacion"));
                    aplicacion.setNombre(rs.getString("nombre"));
                    return aplicacion;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizar(Aplicacion aplicacion) {
        String sql = "UPDATE aplicacion SET nombre = ? WHERE id_aplicacion = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aplicacion.getNombre());
            stmt.setInt(2, aplicacion.getIdAplicacion());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
