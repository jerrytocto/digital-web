package Controller;

import com.google.gson.Gson;
import dto.ColaboradorDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import service.ColaboradorService;
import serviceImpl.ColaboradorServiceImpl;

@WebServlet("/colaborador")
public class ColaboradorController extends HttpServlet {

    private ColaboradorService colaboradorService;

    @Override
    public void init() throws ServletException {
        this.colaboradorService = new ColaboradorServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        try {
            switch (accion != null ? accion : "") {
                case "listarColaboradores":
                    listarUsuariosColaboradores(req, resp);
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
                    registrarAplicacion(req, resp);
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

    //Listar todos los usaurios que son colaboradores
    private void listarUsuariosColaboradores(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        List<ColaboradorDTO> colaboradores = colaboradorService.listColaborador();
        // Se trasforma la lista de colaboradores en un objto Json 

        // Se transforma la lista de colaboradores en un objeto Json 
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        String json = gson.toJson(colaboradores);
        System.out.println("Lista de colaboradores: " + json);
        resp.getWriter().write(json);

    }
}
