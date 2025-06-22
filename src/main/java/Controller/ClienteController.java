package Controller;

import com.google.gson.Gson;
import dto.AplicacionDTO;
import dto.ClienteDTO;
import dto.TrabajadorDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import service.ClienteService;
import serviceImpl.ClienteServiceImpl;

@WebServlet("/cliente")
public class ClienteController extends HttpServlet {

    private ClienteService clienteService;

    @Override
    public void init() throws ServletException {
        this.clienteService = new ClienteServiceImpl(); 
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        try {
            switch (accion != null ? accion : "") {
                case "listar":
                    listarClientes(req, resp);
                    break;
                case "listarJson":
                    listarClientesJson(req, resp);
                    break;
                case "obtener":
                    obtenerCliente(req, resp);
                    break;
                case "trabajadores":
                    listarTrabajadores(req, resp);
                    break;
                case "aplicaciones":
                    listarAplicaciones(req, resp);
                    break;
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
                    registrarCliente(req, resp);
                    break;
                case "actualizar":
                    actualizarCliente(req, resp);
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno del servidor");
        }
    }

    private void listarClientes(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<ClienteDTO> clientes = clienteService.listar();
        // Aquí puedes retornar JSON, JSP, etc.
        req.setAttribute("clientes", clientes);
        try {
            //Redirige a la ventana de listado de clientes
            req.getRequestDispatcher("/pages/cliente/listarTodos.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
    
    private void listarClientesJson(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<ClienteDTO> clientes = clienteService.listar();
        // Aquí puedes retornar JSON, JSP, etc.
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        String json = gson.toJson(clientes);
        resp.getWriter().write(json);
    }
    
    private void obtenerCliente(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = parseInt(req.getParameter("id"), -1);
        if (id == -1) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
            return;
        }

        ClienteDTO cliente = clienteService.obtenerPorId(id);
        if (cliente == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Cliente no encontrado");
        } else {
            req.setAttribute("cliente", cliente);
            try {
                //Dirige a la ventana para ver el detalle del cliente
                req.getRequestDispatcher("/views/cliente/detalle.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }

    private void registrarCliente(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String tipoCliente = req.getParameter("tipoCliente");
        String razonSocial = req.getParameter("razonSocial");
        String tipoDocumento = req.getParameter("tipoDocumento");
        String numeroDocumento = req.getParameter("numeroDocumento");
        String telefono = req.getParameter("telefono");
        String correo = req.getParameter("correo");
        String direccion = req.getParameter("direccion");
        Date fechaRegistro = new Date(System.currentTimeMillis());

        ClienteDTO cliente = new ClienteDTO(tipoCliente, razonSocial, tipoDocumento, numeroDocumento, telefono, correo, direccion, fechaRegistro);

        boolean exito = clienteService.registrar(cliente);
        if (exito) {
            resp.sendRedirect("cliente?accion=listar");
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo registrar el cliente");
        }
    }

    private void actualizarCliente(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int idCliente = parseInt(req.getParameter("idCliente"), -1);
        if (idCliente == -1) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
            return;
        }
        String tipoCliente = req.getParameter("tipoCliente");
        String razonSocial = req.getParameter("razonSocial");
        String tipoDocumento = req.getParameter("tipoDocumento");
        String numeroDocumento = req.getParameter("numeroDocumento");
        String telefono = req.getParameter("telefono");
        String correo = req.getParameter("correo");
        String direccion = req.getParameter("direccion");

        String fechaTexto = req.getParameter("fechaRegistro");

        java.sql.Date fechaRegistro = null;
        if (fechaTexto != null && !fechaTexto.isEmpty()) {
            fechaRegistro = java.sql.Date.valueOf(fechaTexto);  // Esto requiere formato yyyy-MM-dd
        }

        ClienteDTO cliente = new ClienteDTO(idCliente, tipoCliente, razonSocial, tipoDocumento, numeroDocumento, telefono, correo, direccion, fechaRegistro);

        boolean exito = clienteService.actualizar(cliente);
        if (exito) {
            resp.sendRedirect("cliente?accion=listar");
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo registrar el cliente");
        }
    }

    private void listarTrabajadores(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = parseInt(req.getParameter("idCliente"), -1);
        if (id == -1) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
            return;
        }

        List<TrabajadorDTO> trabajadores = clienteService.listarTrabajadoresPorCliente(id);
        req.setAttribute("trabajadores", trabajadores);
        try {
            req.getRequestDispatcher("/views/cliente/trabajadores.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private void listarAplicaciones(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = parseInt(req.getParameter("idCliente"), -1);
        if (id == -1) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
            return;
        }

        List<AplicacionDTO> aplicaciones = clienteService.listarAplicacionesPorCliente(id);
        req.setAttribute("aplicaciones", aplicaciones);
        try {
            req.getRequestDispatcher("/views/cliente/aplicaciones.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private int parseInt(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

}
