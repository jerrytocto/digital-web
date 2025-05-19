package com.last.digital.resources.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static final String URL = "jdbc:postgresql://localhost:5432/PROYECTO_LAST";
    private static final String USUARIO = "postgres";
    private static final String CONTRASENA = "admin";

    private static Connection conexion;

    // Método para obtener la conexión
    public static Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");
                conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                System.out.println("Conexión establecida exitosamente.");
            } catch (ClassNotFoundException e) {
                System.err.println("No se encontró el driver de PostgreSQL.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Error al conectar con la base de datos.");
                e.printStackTrace();
                throw e;
            }
        }
        return conexion;
    }

    // Método para cerrar la conexión si es necesario
    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
