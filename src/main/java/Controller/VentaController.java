package Controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dto.VentaDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import model.Aplicacion;
import model.Cliente;
import service.VentaService;
import serviceImpl.VentaServiceImpl;

@WebServlet("/venta")
public class VentaController extends HttpServlet {

    private VentaService ventaService;

    @Override
    public void init() throws ServletException {
        this.ventaService = new VentaServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        try {
            switch (accion != null ? accion : "") {
                /*case "listar":
                    listarAplicaciones(req, resp);
                    break;
                case "listarPorCliente":
                    listarAplicacionesPorCliente(req, resp);
                    break;

                case "listarNoCompradas":
                    listarAplicacionesNoCompradasPorCliente(req, resp);
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
                    registrarVenta(req, resp);
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

    private void registrarVenta(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // Leer JSON del body
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String linea;

        while ((linea = reader.readLine()) != null) {
            sb.append(linea);
        }

        String json = sb.toString();
        System.out.println("JSON recibido: " + json);

        Gson gson = new Gson();
        JsonObject data = gson.fromJson(json, JsonObject.class);

        int idCliente = data.get("idCliente").getAsInt();
        int idAplicacion = data.get("idAplicacion").getAsInt();
        
        java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());

        VentaDTO ventaDTO = new VentaDTO(fechaActual, new Cliente(idCliente), new Aplicacion(idAplicacion));

        boolean exito = ventaService.insertarVenta(ventaDTO);

        // Respuesta
        resp.setContentType("application/json");
        if (exito) {
            resp.getWriter().write("{\"mensaje\":\"Atención registrada exitosamente\"}");
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"mensaje\":\"No se pudo registrar la atención\"}");
        }
    }
}
