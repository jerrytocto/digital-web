
import dao.AplicacionDao;
import java.sql.SQLException;
import model.Aplicacion;

public class PruebaApp {

    public static void main(String[] args) {
        /*try {
            ClienteDao clienteDao = new ClienteDao();

            Cliente nuevo = new Cliente();
            nuevo.setTipoCliente("Empresa");
            nuevo.setRazonSocial("Tech Solutions SAC");
            nuevo.setTipoDocumento("RUC");
            nuevo.setNumeroDocumento("20567891234");
            nuevo.setTelefono("987654321");
            nuevo.setCorreo("contacto@techsac.com");
            nuevo.setDireccion("Av. Principal 123");
            nuevo.setFechaRegistro(new java.sql.Date(System.currentTimeMillis()));

            boolean registrado = clienteDao.insertar(nuevo);
            System.out.println("Cliente registrado: " + registrado);

        } catch (SQLException e) {
            e.printStackTrace();
        } */

        try {

            AplicacionDao aplicacionDao = new AplicacionDao();
            Aplicacion aplicacion = new Aplicacion();
            aplicacion.setNombre("Aplicacion de prueba 1");

            boolean aplicacionRegistrada = aplicacionDao.insertar(aplicacion);

            if (aplicacionRegistrada) {
                System.out.println("Cliente registrado correctamente ");
            } else {
                System.out.println("No se pudo registrar la aplicacion ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
