package Controller;

import dto.AplicacionDTO;
import dto.ClienteDTO;
import dto.SolicitudDTO;
import dto.TipoSolicitudDTO;
import dto.TrabajadorDTO;
import dto.UsuarioDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import service.SolicitudService;
import serviceImpl.SolicitudServiceImpl;

@WebServlet("/solicitudes")
public class SolicitudController extends HttpServlet {

    private SolicitudService solicitudService;

    @Override
    public void init() throws ServletException {
        this.solicitudService = new SolicitudServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        try {
            switch (accion != null ? accion : "") {
                case "listarTodas":
                    listarSolicitudes(req, resp);
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
                    registrarSolicitud(req, resp);
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

    private void listarSolicitudes(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
        Integer idCliente = (Integer) session.getAttribute("empresaId");
        String rol = (String) session.getAttribute("rol");
        Integer usuarioId = (Integer) session.getAttribute("usuarioId");
        System.out.println("=========== Tipo de usuario en ListarSolicitudes " + tipoUsuario);
        System.out.println("=========== El id del cliente es  " + idCliente);
        System.out.println("=========== El rol del usuario es  " + rol);
        System.out.println("=========== El id del usuario es  " + usuarioId);

        List<SolicitudDTO> solicitudes = new ArrayList<>();

        if ("COLABORADOR".equals(tipoUsuario) && "admin".equals(rol)) {
            //Si es de tipo colaborador entonces, debe extraerme todas las solicitudes de todas los clientes
            solicitudes = solicitudService.listSolicitud();
        } else if ("COLABORADOR".equals(tipoUsuario) && !"admin".equals(rol)) {
            solicitudes = solicitudService.listarSolicitudesAsignadasPorColaborador(usuarioId);
        } else {
            // Debe traer la solicitud pero solo del cliente al que el usuario pertenece
            System.out.println("Usuario trabajador, ID cliente: " + idCliente);
            solicitudes = solicitudService.listaSolicitudesPorCliente(idCliente);
        }

        // Aquí puedes retornar JSON, JSP, etc.
        req.setAttribute("solicitudes", solicitudes);

        //Devolver la lista de aplicaciones 
        Set<Integer> idsSolicitudes = new HashSet<>();
        List<AplicacionDTO> aplicacionesUnicas = new ArrayList<>();
        for (SolicitudDTO solicitud : solicitudes) {
            AplicacionDTO app = solicitud.getAplicacion();
            if (app != null && !idsSolicitudes.contains(app.getIdAplicacion())) {
                idsSolicitudes.add(app.getIdAplicacion());
                aplicacionesUnicas.add(app);
            }
        }

        //Devolver la lista de clientes
        Set<Integer> idsClientes = new HashSet<>();
        List<ClienteDTO> clientesUnicos = new ArrayList<>();
        for (SolicitudDTO solicitud : solicitudes) {
            ClienteDTO cliente = solicitud.getTrabajador().getCliente();
            if (cliente != null && !idsClientes.contains(cliente.getIdCliente())) {
                idsClientes.add(cliente.getIdCliente());
                clientesUnicos.add(cliente);
            }
        }

        //Devuelve lista de aplicaciones únicas 
        req.setAttribute("aplicaciones", aplicacionesUnicas);

        //Devuelve lista de clientes únicos
        req.setAttribute("clientes", clientesUnicos);

        try {
            //Redirige a la ventana de listado de solicitudes
            req.getRequestDispatcher("/pages/solicitud/listaSolicitudes.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private void registrarSolicitud(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            // Obtener datos del formulario enviados desde el frontend
            String descripcion = req.getParameter("descripcion");
            int idTipoSolicitud = Integer.parseInt(req.getParameter("tipoSolicitud"));
            int idAplicacion = Integer.parseInt(req.getParameter("aplicacion"));
            Date fechaRegistro = new Date(System.currentTimeMillis());

            // Obtener usuario y empresa desde la sesión
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("usuarioId") == null || session.getAttribute("empresaId") == null) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().write("{\"error\": \"Sesión expirada o inválida\"}");
                return;
            }

            int usuarioId = (int) session.getAttribute("usuarioId");

            SolicitudDTO nuevaSolicitud = new SolicitudDTO();
            nuevaSolicitud.setMotivo(descripcion);
            nuevaSolicitud.setTipoSolicitud(new TipoSolicitudDTO(idTipoSolicitud));
            nuevaSolicitud.setAplicacion(new AplicacionDTO(idAplicacion));
            nuevaSolicitud.setFechaRegistro(fechaRegistro);

            TrabajadorDTO trabajador = new TrabajadorDTO();
            trabajador.setIdUsuario(usuarioId);

            nuevaSolicitud.setTrabajador(trabajador);

            boolean exito = solicitudService.insertarSolicitud(nuevaSolicitud);

            if (exito) {
                resp.sendRedirect("solicitudes?accion=listarTodas");
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo registrar el cliente");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"No se pudo registrar la solicitud\"}");
        }
    }
}
