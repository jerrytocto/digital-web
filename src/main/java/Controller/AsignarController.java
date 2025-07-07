package Controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dto.AsignacionDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import service.AsignacionService;
import serviceImpl.AsignacionServiceImpl;

@WebServlet("/asignar")
public class AsignarController extends HttpServlet {

    private AsignacionService asignacionService;

    @Override
    public void init() throws ServletException {
        this.asignacionService = new AsignacionServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        try {
            switch (accion != null ? accion : "") {
                case "listarColaboradoresPorSolicitud":
                    listarAsignacionesPorSolicitud(req, resp);
                    break;

                /*
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
                case "insertar":
                    agregarAsignacion(req, resp);
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

    private void agregarAsignacion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

        int idSolicitud = data.get("idSolicitud").getAsInt();
        int idCoordinador = data.get("coordinador").getAsInt();

        JsonArray colaboradoresJson = data.getAsJsonArray("colaboradores");
        List<Integer> idsColaboradores = new ArrayList<>();
        for (JsonElement e : colaboradoresJson) {
            idsColaboradores.add(e.getAsInt());
        }

        // Lógica de negocio: guardar la asignación en la BD
        asignacionService.verificarAsignaciones(idSolicitud, idsColaboradores, idCoordinador);

        // Respuesta al frontend
        resp.setContentType("application/json");
        resp.getWriter().write("{\"mensaje\":\"Asignación exitosa\"}");
    }

    public void listarAsignacionesPorSolicitud(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int idSolicitud;

        try {
            idSolicitud = Integer.parseInt(req.getParameter("idSolicitud"));
            System.out.println("Id de la solicitud a editar es: " + idSolicitud);
        } catch (NumberFormatException | NullPointerException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
            return;
        }
        

        List<AsignacionDTO> listAsignaciones = asignacionService.listarTodasAsignacionesPorSolicitud(idSolicitud);
        

        List<Integer> asignados = new ArrayList<>();
        Integer coordinador = null;

        for (AsignacionDTO asignacion : listAsignaciones) {
            System.out.println("Nombre persona asignada: "+ asignacion.getColaborador().getIdUsuario());
            int idUsuario = asignacion.getColaborador().getIdUsuario();
            asignados.add(idUsuario);

            if (asignacion.isIsCoordinador()) {
                coordinador = idUsuario;
            }
        }

        // Armar JSON manualmente
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"asignados\":").append(asignados.toString()).append(",");
        json.append("\"coordinador\":").append(coordinador != null ? coordinador : "null");
        json.append("}");
        System.out.println("JSON: "+ json);

        // Enviar respuesta
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json.toString());
    }
}
