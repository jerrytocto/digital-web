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
import model.Venta;

public class VentaDao {

    private Connection conn;

    public VentaDao() throws SQLException {
        conn = ConnectionDB.getConexion();
    }

    public boolean insertar(Venta venta) {
        String sql = "INSERT INTO venta (fecha, id_cliente, id_aplicacion) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, (Date) venta.getFechaVenta());
            stmt.setInt(2, venta.getCliente().getIdCliente());
            stmt.setInt(3, venta.getAplicacion().getIdAplicacion());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Venta> listarTodos() {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM venta";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setFechaVenta(rs.getDate("fecha"));

                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                venta.setCliente(cliente);

                Aplicacion aplicacion = new Aplicacion();
                aplicacion.setIdAplicacion(rs.getInt("id_aplicacion"));
                venta.setAplicacion(aplicacion);

                lista.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Venta buscarPorId(int id) {
        String sql = "SELECT * FROM venta WHERE id_venta = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Venta venta = new Venta();
                    venta.setIdVenta(rs.getInt("id_venta"));
                    venta.setFechaVenta(rs.getDate("fecha"));

                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("id_cliente"));
                    venta.setCliente(cliente);

                    Aplicacion aplicacion = new Aplicacion();
                    aplicacion.setIdAplicacion(rs.getInt("id_aplicacion"));
                    venta.setAplicacion(aplicacion);

                    return venta;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizar(Venta venta) {
        String sql = "UPDATE venta SET fecha = ?, id_cliente = ?, id_aplicacion = ? WHERE id_venta = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, (Date) venta.getFechaVenta());
            stmt.setInt(2, venta.getCliente().getIdCliente());
            stmt.setInt(3, venta.getAplicacion().getIdAplicacion());
            stmt.setInt(4, venta.getIdVenta());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Venta> listarVentasPorCliente(int idCliente) {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM venta WHERE id_cliente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Venta venta = new Venta();
                    venta.setIdVenta(rs.getInt("id_venta"));
                    venta.setFechaVenta(rs.getDate("fecha"));

                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("id_cliente"));
                    venta.setCliente(cliente);

                    Aplicacion aplicacion = new Aplicacion();
                    aplicacion.setIdAplicacion(rs.getInt("id_aplicacion"));
                    venta.setAplicacion(aplicacion);

                    lista.add(venta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Venta> listarVentasPorAplicacion(int idAplicacion) {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM venta WHERE id_aplicacion = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAplicacion);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Venta venta = new Venta();
                    venta.setIdVenta(rs.getInt("id_venta"));
                    venta.setFechaVenta(rs.getDate("fecha"));

                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("id_cliente"));
                    venta.setCliente(cliente);

                    Aplicacion aplicacion = new Aplicacion();
                    aplicacion.setIdAplicacion(rs.getInt("id_aplicacion"));
                    venta.setAplicacion(aplicacion);

                    lista.add(venta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
