
package Controller;

import dto.UsuarioDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import service.UsuarioService;
import serviceImpl.UsuarioServiceImpl;

@WebServlet("/usuario")
public class UsuarioController extends HttpServlet{
        private UsuarioService usuarioService;

    @Override
    public void init() throws ServletException {
        this.usuarioService = new UsuarioServiceImpl();
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
        List<UsuarioDTO> usauriosColaboradores = new ArrayList();
        // Aquí puedes retornar JSON, JSP, etc.
        req.setAttribute("usauriosColaboradores", usauriosColaboradores);
        try {
            //Redirige a la ventana de listado de clientes
            req.getRequestDispatcher("/pages/cliente/listarTodos.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
