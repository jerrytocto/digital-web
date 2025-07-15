package Controller;

import dto.ClienteDTO;
import dto.TrabajadorDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import service.ClienteService;
import service.TrabajadorService;
import serviceImpl.ClienteServiceImpl;
import serviceImpl.TrabajadorServiceImpl;

@WebServlet("/trabajador")
public class TrabajadorController extends HttpServlet {

    private TrabajadorService trabajadorService;
    private ClienteService clienteService;

    @Override
    public void init() throws ServletException {
        this.trabajadorService = new TrabajadorServiceImpl();
        this.clienteService = new ClienteServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        try {
            switch (accion != null ? accion : "") {
                case "listarTodos":
                    listarTrabajadores(req, resp);
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
                    registrarTrabajador(req, resp);
                    break;

                /*case "insertar":
                    insertarAplicacion(req, resp);
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

    //Listar todos los clientes
    private void listarTrabajadores(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("si llega al backend cuando quiero ver la lista de trabajadores");
        List<TrabajadorDTO> trabajadores = trabajadorService.listTrabajador();
        List<ClienteDTO> clientes = clienteService.listar();

        for (TrabajadorDTO trabajador : trabajadores) {
            System.out.println("Razon social: " + trabajador.getCliente().getRazonSocial());
        }
        // Aquí puedes retornar JSON, JSP, etc.
        req.setAttribute("trabajadores", trabajadores);
        req.setAttribute("clientes", clientes);

        try {
            //Redirige a la ventana de listado de trabajadores
            req.getRequestDispatcher("/pages/trabajador/listaTrabajadores.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private void registrarTrabajador(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            // Obtener parámetros
            String nombre = req.getParameter("nombreTrabajador");
            String apellidoPaterno = req.getParameter("apellidoPaternoTrabajador");
            String apellidoMaterno = req.getParameter("apellidoMaternoTrabajador");
            String tipoDocumento = req.getParameter("tipoDocumentoTrabajador");
            String numeroDocumento = req.getParameter("numeroDocumentoTrabajador");
            String telefono = req.getParameter("telefonoTrabajador");
            String correo = req.getParameter("correoTrabajador");
            String username = req.getParameter("usernameTrabajador");
            String password = req.getParameter("passwordTrabajador");
            int idCliente = Integer.parseInt(req.getParameter("idClienteTrabajdor"));

            java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());

            // Construir DTO
            TrabajadorDTO trabajadorDTO = new TrabajadorDTO();
            trabajadorDTO.setEstado(true);
            trabajadorDTO.setTipoDocumento(tipoDocumento);
            trabajadorDTO.setNumeroDocumento(numeroDocumento);
            trabajadorDTO.setNombres(nombre);
            trabajadorDTO.setApellidoPaterno(apellidoPaterno);
            trabajadorDTO.setApellidoMaterno(apellidoMaterno);
            trabajadorDTO.setTelefono(telefono);
            trabajadorDTO.setCorreo(correo);
            trabajadorDTO.setFechaRegistro(fechaActual);
            trabajadorDTO.setUsename(username);  // corregido el typo
            trabajadorDTO.setPassword(password);
            trabajadorDTO.setCliente(new ClienteDTO(idCliente));

            // Insertar colaborador
            boolean rpta = trabajadorService.insertarTrabajador(trabajadorDTO);

            if (!rpta) {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo registrar el colaborador");
                return;
            }

            // Todo bien
            resp.sendRedirect("trabajador?accion=listarTodos");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error inesperado: " + e.getMessage());
        }
    }

}
