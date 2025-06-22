package Controller;

import com.google.gson.Gson;
import dto.AplicacionDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.Date;
import java.util.List;
import service.AplicacionService;
import serviceImpl.AplicacionServiceImpl;

@WebServlet("/aplicacion")
public class AplicacionController extends HttpServlet {

    private AplicacionService aplicacionService;

    @Override
    public void init() throws ServletException {
        this.aplicacionService = new AplicacionServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        try {
            switch (accion != null ? accion : "") {
                case "listar":
                    listarAplicaciones(req, resp);
                    break;
                case "listarPorCliente":
                    listarAplicacionesPorCliente(req, resp);
                    break;
                /*case "obtener":
                    obtenerCliente(req, resp);
                    break;
                case "trabajadores":
                    listarTrabajadores(req, resp);
                    break;
                case "aplicaciones":
                    listarAplicaciones(req, resp);
                    break;
                 */
                default:
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida");
            }
        } catch (Exception e) {
            e.printStackTrace();

            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno del servidor");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        try {
            switch (accion != null ? accion : "") {
                case "registrar":
                    registrarAplicacion(req, resp);
                    break;
                /*case "actualizar":
                    actualizarCliente(req, resp);
                    break;
                 */
                default:
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno del servidor");
        }
    }

    //Listar todos los clientes
    private void listarAplicaciones(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<AplicacionDTO> aplicaciones = aplicacionService.listar();
        // Aquí puedes retornar JSON, JSP, etc.
        req.setAttribute("aplicaciones", aplicaciones);
        try {
            //Redirige a la ventana de listado de clientes
            req.getRequestDispatcher("/pages/cliente/listarTodos.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    //Listar aplicaciones por cliente
    private void listarAplicacionesPorCliente(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idClienteParam = req.getParameter("idCliente");
        int idCliente;
        try {
            idCliente = Integer.parseInt(idClienteParam);
        } catch (NumberFormatException | NullPointerException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
            return;
        }

        List<AplicacionDTO> aplicacionesPorCliente = aplicacionService.listarAplicacionesPorCliente(idCliente);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        String json = gson.toJson(aplicacionesPorCliente);

        System.out.println("Lista de aplicaciones por cliente: " + json);
        resp.getWriter().write(json);
    }

    private void registrarAplicacion(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String nombreAplicacion = req.getParameter("nombreAplicacion");

        AplicacionDTO aplicacion = new AplicacionDTO(nombreAplicacion);

        boolean exito = aplicacionService.registrar(aplicacion);
        if (exito) {
            resp.sendRedirect("aplicacion?accion=listar");
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo registrar el cliente");
        }
    }

}
