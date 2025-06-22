package Controller;

import com.google.gson.Gson;
import dto.TipoSolicitudDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import service.TipoSolicitudService;
import serviceImpl.TipoSolicitudServiceImpl;

@WebServlet("/tipo-solicitudes")
public class TipoSolicitudController extends HttpServlet {

    private TipoSolicitudService tipoSolicitudService;

    @Override
    public void init() throws ServletException {
        this.tipoSolicitudService = new TipoSolicitudServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        try {
            switch (accion != null ? accion : "") {
                case "listarTodos":
                    listarTipoSolicitudes(req, resp);
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
                /*case "registrar":
                    registrarCliente(req, resp);
                    break;
                case "actualizar":
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

    private void listarTipoSolicitudes(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        List<TipoSolicitudDTO> tipoSolicitudes = tipoSolicitudService.listTipoSolicitud();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        String json = gson.toJson(tipoSolicitudes);
        resp.getWriter().write(json);

    }

}
