package Controller;

import com.google.gson.Gson;
import dto.ColaboradorDTO;
import dto.UsuarioDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import service.ColaboradorService;
import service.UsuarioService;
import serviceImpl.ColaboradorServiceImpl;
import serviceImpl.UsuarioServiceImpl;

@WebServlet("/colaborador")
public class ColaboradorController extends HttpServlet {

    private ColaboradorService colaboradorService;
    private UsuarioService usuarioService;

    @Override
    public void init() throws ServletException {
        this.colaboradorService = new ColaboradorServiceImpl();
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
                case "listarTodos":
                    listarTodosColaboradoresJSP(req, resp);
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
                    registrarColaborador(req, resp);
                    break;
                case "editar":
                    editarColaborador(req, resp);
                    break;

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

    // Listar todos los colaboradores de la aplicación
    private void listarTodosColaboradoresJSP(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        List<ColaboradorDTO> colaboradores = colaboradorService.listColaborador();

        // Aquí puedes retornar JSON, JSP, etc.
        req.setAttribute("colaboradores", colaboradores);
        try {
            //Redirige a la ventana de listado de clientes
            req.getRequestDispatcher("/pages/colaborador/listaColaboradores.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private void registrarColaborador(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            // Obtener parámetros
            String nombre = req.getParameter("nombre");
            String apellidoPaterno = req.getParameter("apellidoPaterno");
            String apellidoMaterno = req.getParameter("apellidoMaterno");
            String tipoDocumento = req.getParameter("tipoDocumento");
            String numeroDocumento = req.getParameter("numeroDocumento");
            String telefono = req.getParameter("telefono");
            String correo = req.getParameter("correo");
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String tipoColaborador = req.getParameter("tipoColaborador");

            java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());

            // Construir DTO
            ColaboradorDTO colaboradorDTO = new ColaboradorDTO();
            colaboradorDTO.setEstado(true);
            colaboradorDTO.setTipoDocumento(tipoDocumento);
            colaboradorDTO.setNumeroDocumento(numeroDocumento);
            colaboradorDTO.setNombres(nombre);
            colaboradorDTO.setApellidoPaterno(apellidoPaterno);
            colaboradorDTO.setApellidoMaterno(apellidoMaterno);
            colaboradorDTO.setTelefono(telefono);
            colaboradorDTO.setCorreo(correo);
            colaboradorDTO.setFechaRegistro(fechaActual);
            colaboradorDTO.setUsename(username);  // corregido el typo
            colaboradorDTO.setPassword(password);
            colaboradorDTO.setTipoColaborador(tipoColaborador);

            // Insertar colaborador
            boolean rpta = colaboradorService.insertarColaborador(colaboradorDTO);

            if (!rpta) {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo registrar el colaborador");
                return;
            }

            // Todo bien
            resp.sendRedirect("colaborador?accion=listarTodos");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error inesperado: " + e.getMessage());
        }
    }

    private void editarColaborador(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("si llega al controlador colaborador para editar un colaborador");
        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        String nombre = req.getParameter("nombre");
        String apellidoPaterno = req.getParameter("apellidoPaterno");
        String apellidoMaterno = req.getParameter("apellidoMaterno");
        String tipoDocumento = req.getParameter("tipoDocumento");
        String numeroDocumento = req.getParameter("numeroDocumento");
        String telefono = req.getParameter("telefono");
        String correo = req.getParameter("correo");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String tipoColaborador = req.getParameter("tipoColaborador");
        java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());

        ColaboradorDTO colaboradorDTO = new ColaboradorDTO();
        colaboradorDTO.setIdUsuario(idUsuario);
        colaboradorDTO.setNombres(nombre);
        colaboradorDTO.setApellidoPaterno(apellidoPaterno);
        colaboradorDTO.setApellidoMaterno(apellidoMaterno);
        colaboradorDTO.setTipoDocumento(tipoDocumento);
        colaboradorDTO.setNumeroDocumento(numeroDocumento);
        colaboradorDTO.setTelefono(telefono);
        colaboradorDTO.setCorreo(correo);
        colaboradorDTO.setUsename(username);
        colaboradorDTO.setFechaRegistro(fechaActual);
        colaboradorDTO.setTipoColaborador(tipoColaborador);

        if (password != null && !password.isEmpty()) {
            colaboradorDTO.setPassword(password);
        }

        // Insertar colaborador
        boolean rpta = colaboradorService.actualizarColaborador(colaboradorDTO);

        if (!rpta) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo registrar el colaborador");
            return;
        }

        // Todo bien
        resp.sendRedirect("colaborador?accion=listarTodos");

    }

}
