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
import model.Usuario;

public class UsuarioDao {

    private Connection conn;

    public UsuarioDao() throws SQLException {
        conn = ConnectionDB.getConexion();
    }

    public boolean insertar(Usuario usuario) {
        String sql = "INSERT INTO usuario (tipo_documento, num_documento, nombre, apellido_paterno, apellido_materno, telefono,correo,fecha_registro, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getTipoDocumento());
            stmt.setString(2, usuario.getNumeroDocumento());
            stmt.setString(3, usuario.getNombres());
            stmt.setString(4, usuario.getApellidoPaterno());
            stmt.setString(5, usuario.getApellidoMaterno());
            stmt.setString(6, usuario.getTelefono());
            stmt.setString(7, usuario.getCorreo());
            stmt.setDate(8, new java.sql.Date(usuario.getFechaRegistro().getTime()));
            stmt.setBoolean(9, usuario.getEstado());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    usuario.setIdUsuario(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ACTUALIZAR USUARIO
    public boolean actualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET tipo_documento=?, num_documento=?, nombre=?, apellido_paterno=?, "
                + "apellido_materno=?,telefono=?, correo=?, fecha_registro=?, estado=? WHERE id_usuario=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getTipoDocumento());
            stmt.setString(2, usuario.getNumeroDocumento());
            stmt.setString(3, usuario.getNombres());
            stmt.setString(4, usuario.getApellidoPaterno());
            stmt.setString(5, usuario.getApellidoMaterno());
            stmt.setString(6, usuario.getTelefono());
            stmt.setString(7, usuario.getCorreo());
            stmt.setDate(8, new java.sql.Date(usuario.getFechaRegistro().getTime()));
            stmt.setBoolean(9, usuario.getEstado());
            stmt.setInt(10, usuario.getIdUsuario());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // BUSCAR USUARIO POR ID
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id_usuario=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setTipoDocumento(rs.getString("tipo_documento"));
                usuario.setNumeroDocumento(rs.getString("num_documento"));
                usuario.setNombres(rs.getString("nombre"));
                usuario.setApellidoPaterno(rs.getString("apellido_paterno"));
                usuario.setApellidoMaterno(rs.getString("apellido_materno"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setFechaRegistro(rs.getDate("fecha_registro"));
                usuario.setEstado(rs.getBoolean("estado"));
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // LISTAR TODOS LOS USUARIOS
    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setTipoDocumento(rs.getString("tipo_documento"));
                usuario.setNumeroDocumento(rs.getString("num_documento"));
                usuario.setNombres(rs.getString("nombre"));
                usuario.setApellidoPaterno(rs.getString("apellido_paterno"));
                usuario.setApellidoMaterno(rs.getString("apellido_materno"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setFechaRegistro(rs.getDate("fecha_registro"));
                usuario.setEstado(rs.getBoolean("estado"));
                lista.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    // BUSCAR USUARIO POR USERNAME Y CONTRASEÑA 
    public Usuario buscarUsuarioPorUsernamePassword(String username, String contrasenia){
        String sql = "SELECT * FROM usuario WHERE username=? AND contrasenia=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, contrasenia);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setTipoDocumento(rs.getString("tipo_documento"));
                usuario.setNumeroDocumento(rs.getString("num_documento"));
                usuario.setNombres(rs.getString("nombre"));
                usuario.setApellidoPaterno(rs.getString("apellido_paterno"));
                usuario.setApellidoMaterno(rs.getString("apellido_materno"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setFechaRegistro(rs.getDate("fecha_registro"));
                usuario.setEstado(rs.getBoolean("estado"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("contrasenia"));
                
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
        // LISTAR TODOS LOS USUARIOS QUE SON COLABORADORES
    public List<Usuario> listarTodosColaboradores() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario u"
                + "   INNER JOIN colaborador c "
                + "   ON u.id_usuario = c.id_usuario"
                + "   WHERE u.estado ='true'";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setTipoDocumento(rs.getString("tipo_documento"));
                usuario.setNumeroDocumento(rs.getString("num_documento"));
                usuario.setNombres(rs.getString("nombre"));
                usuario.setApellidoPaterno(rs.getString("apellido_paterno"));
                usuario.setApellidoMaterno(rs.getString("apellido_materno"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setFechaRegistro(rs.getDate("fecha_registro"));
                usuario.setEstado(rs.getBoolean("estado"));
                lista.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
