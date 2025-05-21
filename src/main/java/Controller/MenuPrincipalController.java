package Controller;

import dto.AplicacionDTO;
import dto.ClienteDTO;
import dto.SolicitudDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import service.AplicacionService;
import service.ClienteService;
import service.SolicitudService;
import serviceImpl.AplicacionServiceImpl;
import serviceImpl.ClienteServiceImpl;
import serviceImpl.SolicitudServiceImpl;

@WebServlet("/Digital")
public class MenuPrincipalController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ClienteService clienteService;
    private AplicacionService aplicacionService;
    private SolicitudService solicitudService;

    @Override
    public void init() throws ServletException {
        this.clienteService = new ClienteServiceImpl();
        this.aplicacionService = new AplicacionServiceImpl();
        this.solicitudService = new SolicitudServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Si llega al doGet");

        // Cargar todos los datos para el dashboard
        cargarDatosDashboard(request);

        // Redirigir al index.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    private void cargarDatosDashboard(HttpServletRequest request) {
        try {
            // Obtener estadísticas de clientes
            System.out.println("Si llega al método cargarDatosDashboards");
            obtenerEstadisticasClientes(request);

            // Obtener estadísticas de aplicaciones
            obtenerEstadisticasAplicaciones(request);

            // Obtener estadísticas de solicitudes
            obtenerEstadisticasSolicitudes(request);

            // Obtener solicitudes pendientes
            obtenerSolicitudesPendientes(request);

            // Obtener últimas solicitudes para la tabla
            /*obtenerUltimasSolicitudes(request); */
        } catch (Exception e) {
            // Manejo de errores
            request.setAttribute("error", "Error al cargar datos del dashboard: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void obtenerEstadisticasClientes(HttpServletRequest request) {
        try {
            System.out.println("si carga el método obtenerEstadisticasClientes");

            // Obtener número total de clientes
            List<ClienteDTO> clientes = clienteService.listar();
            int numeroClientes = clientes.size();
            System.out.println("La cantidad de clientes es: " + numeroClientes);
            request.setAttribute("numeroClientes", numeroClientes);
        } catch (Exception e) {
            request.setAttribute("error", "Error al obtener estadísticas de clientes: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private void obtenerEstadisticasAplicaciones(HttpServletRequest request) {
        try {
            System.out.println("si carga el método obtenerEstadisticasClientes");

            // Obtener número total de clientes
            List<AplicacionDTO> listAplicaciones = aplicacionService.listar();
            int numeroAplicaciones = listAplicaciones.size();
            System.out.println("La cantidad de clientes es: " + numeroAplicaciones);
            request.setAttribute("numeroAplicaciones", numeroAplicaciones);
        } catch (Exception e) {
            request.setAttribute("error", "Error al obtener estadísticas de clientes: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private void obtenerEstadisticasSolicitudes(HttpServletRequest request) {
        try {
            System.out.println("si carga el método obtenerEstadisticasClientes");

            // Obtener número total de clientes
            List<SolicitudDTO> listaSolicitudes = solicitudService.listSolicitud();
            int totalSolicitudes = listaSolicitudes.size();
            System.out.println("La cantidad de clientes es: " + totalSolicitudes);
            request.setAttribute("totalSolicitudes", totalSolicitudes);
        } catch (Exception e) {
            request.setAttribute("error", "Error al obtener estadísticas de clientes: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private void obtenerSolicitudesPendientes(HttpServletRequest request) {
        try {
            System.out.println("si carga el método obtenerEstadisticasClientes");

            // Obtener número total de clientes
            List<SolicitudDTO> listaSolicitudesPendientes = solicitudService.listSolicitudesPorEstado("Pendiente");
            int totalSolicitudesPendientes = listaSolicitudesPendientes.size();
            System.out.println("La cantidad de clientes es: " + totalSolicitudesPendientes);
            request.setAttribute("totalSolicitudesPendientes", totalSolicitudesPendientes);
        } catch (Exception e) {
            request.setAttribute("error", "Error al obtener estadísticas de clientes: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
