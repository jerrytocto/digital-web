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
import model.Cliente;

public class ClienteDao {

    private Connection conn;

    public ClienteDao() throws SQLException {
        conn = ConnectionDB.getConexion();
    }

    public boolean insertar(Cliente cliente) {
        String sql = "INSERT INTO cliente (tipo_cliente, razon_social, tipo_documento, numero_documento, correo,direccion,telefono, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getTipoCliente());
            stmt.setString(2, cliente.getRazonSocial());
            stmt.setString(3, cliente.getTipoDocumento());
            stmt.setString(4, cliente.getNumeroDocumento());
            stmt.setString(5, cliente.getCorreo());
            stmt.setString(6, cliente.getDireccion());
            stmt.setString(7, cliente.getTelefono());
            stmt.setDate(8, new java.sql.Date(cliente.getFechaRegistro().getTime()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setTipoCliente(rs.getString("tipo_cliente"));
                cliente.setRazonSocial(rs.getString("razon_social"));
                cliente.setTipoDocumento(rs.getString("tipo_documento"));
                cliente.setNumeroDocumento(rs.getString("numero_documento"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setFechaRegistro(rs.getDate("fecha_registro"));
                lista.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("id_cliente"));
                    cliente.setTipoCliente(rs.getString("tipo_cliente"));
                    cliente.setRazonSocial(rs.getString("razon_social"));
                    cliente.setTipoDocumento(rs.getString("tipo_documento"));
                    cliente.setNumeroDocumento(rs.getString("numero_documento"));
                    cliente.setTelefono(rs.getString("telefono"));
                    cliente.setCorreo(rs.getString("correo"));
                    cliente.setDireccion(rs.getString("direccion"));
                    cliente.setFechaRegistro(rs.getDate("fecha_registro"));
                    return cliente;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET tipo_cliente = ?, razon_social = ?, tipo_documento = ?, numero_documento = ?, telefono = ?, correo = ?, direccion = ?, fecha_registro = ? WHERE id_cliente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getTipoCliente());
            stmt.setString(2, cliente.getRazonSocial());
            stmt.setString(3, cliente.getTipoDocumento());
            stmt.setString(4, cliente.getNumeroDocumento());
            stmt.setString(5, cliente.getTelefono());
            stmt.setString(6, cliente.getCorreo());
            stmt.setString(7, cliente.getDireccion());
            stmt.setDate(8, new java.sql.Date(cliente.getFechaRegistro().getTime()));
            stmt.setInt(9, cliente.getIdCliente());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
