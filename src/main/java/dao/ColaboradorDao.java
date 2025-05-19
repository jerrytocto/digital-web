package dao;

import com.last.digital.resources.config.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Colaborador;

public class ColaboradorDao {

    private Connection conn;

    public ColaboradorDao() throws SQLException {
        conn = ConnectionDB.getConexion();
    }

    public boolean insertar(Colaborador colaborador) throws SQLException {

        UsuarioDao usuarioDao = new UsuarioDao();
        boolean usuarioInsertado = usuarioDao.insertar(colaborador);

        if (usuarioInsertado) {
            String sql = "INSERT INTO colaborador (id_usuario, tipo_colaborador) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, colaborador.getIdUsuario());
                stmt.setString(2, colaborador.getTipoColaborador());
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean actualizar(Colaborador colaborador) throws SQLException {
        UsuarioDao usuarioDao = new UsuarioDao();
        boolean colaboradorActualizado = usuarioDao.actualizar(colaborador);

        if (colaboradorActualizado) {
            String sql = "UPDATE colaborador SET tipo_colaborador = ? WHERE id_usuario = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, colaborador.getTipoColaborador());
                stmt.setInt(2, colaborador.getIdUsuario());
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Colaborador buscarPorIdUsuario(int idUsuario) {
        String sql = "SELECT u.*, c.tipo_colaborador FROM usuario u "
                + "JOIN colaborador c ON u.id_usuario = c.id_usuario "
                + "WHERE u.id_usuario = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Colaborador colaborador = new Colaborador();
                    colaborador.setIdUsuario(rs.getInt("id_usuario"));
                    colaborador.setTipoDocumento(rs.getString("tipo_documento"));
                    colaborador.setNumeroDocumento(rs.getString("num_documento"));
                    colaborador.setNombres(rs.getString("nombre"));
                    colaborador.setApellidoPaterno(rs.getString("apellido_paterno"));
                    colaborador.setApellidoMaterno(rs.getString("apellido_materno"));
                    colaborador.setTelefono(rs.getString("telefono"));
                    colaborador.setCorreo(rs.getString("correo"));
                    colaborador.setFechaRegistro(rs.getDate("fecha_registro"));
                    colaborador.setEstado(rs.getBoolean("estado"));

                    colaborador.setTipoColaborador(rs.getString("tipo_colaborador"));

                    return colaborador;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Colaborador> listarTodos() {
        List<Colaborador> lista = new ArrayList<>();
        String sql = "SELECT u.*, c.tipo_colaborador "
                + "FROM usuario u "
                + "JOIN colaborador c ON u.id_usuario = c.id_usuario";

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Colaborador trabajador = new Colaborador();
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
                trabajador.setTipoColaborador(rs.getString("tipo_colaborador"));

                lista.add(trabajador);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

}
